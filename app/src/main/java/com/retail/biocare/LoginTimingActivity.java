package com.retail.biocare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.retail.biocare.Models.LoginTimingsModels;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.LoginTimingsAdapter;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoginTimingActivity extends AppCompatActivity {

    private static final String TAG = "LoginTimingActivity";

    private ArrayList<LoginTimingsModels> loginTimingDetails = new ArrayList<>();
    private LoginTimingsAdapter loginTimingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_timing);

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerLoginTimings);
        loginTimingsAdapter = new LoginTimingsAdapter(this, loginTimingDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(loginTimingsAdapter);


        new GetLoginTimings().execute();
    }

    private class GetLoginTimings extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(LoginTimingActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loginTimingDetails.clear();
            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            Log.d(TAG, "onPostExecute: "+s);

            if(!s.equalsIgnoreCase("NODATA")){


                try{
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject c = jsonArray.getJSONObject(i);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
                        Date date = sdf.parse( c.getString("Datecreated"));

                        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

                        String dateString = sdf1.format(date);

                        loginTimingDetails.add(new LoginTimingsModels(c.getString("Username"), c.getString("IPAddress"), dateString));
                    }

                    loginTimingsAdapter.notifyDataSetChanged();
                }
                catch (Exception e){
                    Log.e(TAG, "onPostExecute: ",e );
                    Toast.makeText(LoginTimingActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }



            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","MyLogintimeData","MemberId="+ StaticDatas.userBasicData.get("UserID"));
        }
    }

}

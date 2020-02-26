package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.UnusedEpinsAdapter;
import com.retail.biocare.adapter.UsedEpinsAdapter;
import com.retail.biocare.model.UnusedEpinModel;
import com.retail.biocare.model.UsedEpinModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class UnusedPinActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<UnusedEpinModel> usedEpinModels;
    UnusedEpinsAdapter usedEpinsAdapter;
    TextView txtNotFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unusedepin);
        layoutBack = (RelativeLayout) findViewById(R.id.layout_back);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        txtNotFound=(TextView)findViewById(R.id.txt_notfound);


        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
        {
            new GetUnUsedEpin().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
        }


    }

    private class GetUnUsedEpin extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(UnusedPinActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            usedEpinModels=new ArrayList<>();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (s.equals("NODATA")){


            }
            else if(s.equals("[]"))
            {
                txtNotFound.setVisibility(View.VISIBLE);
            }

            else{

                try {
txtNotFound.setVisibility(View.GONE);
                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject c=jsonArray.getJSONObject(i);

                        usedEpinModels.add(new UnusedEpinModel(c.getString("PinID"),c.getString("PinNumber"),c.getString("Amount"),c.getString("PackageName"),c.getString("CreatedDate"),c.getString("PaidStatus")));
                        if(usedEpinModels.size()>0)
                        {
                            txtNotFound.setVisibility(View.GONE);
                            usedEpinsAdapter=new UnusedEpinsAdapter(UnusedPinActivity.this,usedEpinModels);
                            recyclerView.setAdapter(usedEpinsAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(UnusedPinActivity.this));
                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);

                                    if (!recyclerView.canScrollVertically(1)) {

                                        //TODO do page index calulation
                                    //    Toast.makeText(UnusedPinActivity.this, "Last", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        }
                        else
                        {
                            //Log.e("TAG","ELSE");
                            txtNotFound.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }


                    }


                } catch (Exception e) {
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetUnusedpin","MemberId="+userBasicData.get("UserID")+"&PageIndex="+"1");


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

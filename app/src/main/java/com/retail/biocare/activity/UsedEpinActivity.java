package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.google.gson.JsonObject;
import com.retail.biocare.MainActivity;
import com.retail.biocare.R;
import com.retail.biocare.adapter.UsedEpinsAdapter;
import com.retail.biocare.model.UsedEpinModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class UsedEpinActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<UsedEpinModel> usedEpinModels;
    UsedEpinsAdapter usedEpinsAdapter;
    String pageindex;
    TextView txtNotFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usedepin);

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
new GetUsedEpin().execute();
}
else
{
    Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
}

    }
    private class GetUsedEpin extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(UsedEpinActivity.this);

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

            else{

                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject c=jsonArray.getJSONObject(i);
                        userBasicData.put("PinID",c.getString("PinID"));
                        userBasicData.put("PinNumber",c.getString("PinNumber"));
                        userBasicData.put("Amount",c.getString("Amount"));
                        userBasicData.put("UsedDate",c.getString("UsedDate"));
                        userBasicData.put("UsedID",c.getString("UsedID"));
                        userBasicData.put("Name",c.getString("Name"));
                        userBasicData.put("PackageName",c.getString("PackageName"));
                        userBasicData.put("PaidStatus",c.getString("PaidStatus"));
usedEpinModels.add(new UsedEpinModel(c.getString("Username"),c.getString("PinNumber"),c.getString("Amount"),c.getString("Name"),c.getString("PackageName"),c.getString("UsedDate"),c.getString("PaidStatus")));

if(usedEpinModels.size()>0)
{
    txtNotFound.setVisibility(View.GONE);
    usedEpinsAdapter=new UsedEpinsAdapter(UsedEpinActivity.this,usedEpinModels);
    recyclerView.setAdapter(usedEpinsAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(UsedEpinActivity.this));


}
else
{
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
            return new ExtractfromReply().performPost("WSMember","GetUsedpin","MemberId="+userBasicData.get("UserID")+"&PageIndex="+"1");


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
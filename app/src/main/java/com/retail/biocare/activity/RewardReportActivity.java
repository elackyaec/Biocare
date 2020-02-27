package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.IncomeAdapter;
import com.retail.biocare.adapter.RewardReportAdapter;
import com.retail.biocare.model.IncomeModel;
import com.retail.biocare.model.RewardReportModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class RewardReportActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<RewardReportModel> rewardReportModels=new ArrayList<>();
    RewardReportAdapter rewardReportAdapter;
TextView txtNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewardreport);
        layoutBack=(RelativeLayout)findViewById(R.id.layout_back);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        txtNotFound=(TextView)findViewById(R.id.txt_notfound) ;

        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
        {
            new GetReports().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class GetReports extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(RewardReportActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            //incomeModels=new ArrayList<>();
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

                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject c=jsonArray.getJSONObject(i);

                        String level=c.getString("DirectJoin");
                        String amt=c.getString("AchieveReward");
                        String rname=c.getString("RewardName");


                        rewardReportModels.add(new RewardReportModel(userBasicData.get("Username"),userBasicData.get("Name"),rname,amt,level,""));
                        if(rewardReportModels.size()>0)
                        {
                            rewardReportAdapter=new RewardReportAdapter(RewardReportActivity.this,rewardReportModels);
                            recyclerView.setAdapter(rewardReportAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(RewardReportActivity.this));
                            txtNotFound.setVisibility(View.GONE);
                        }
                        else
                        {
                            txtNotFound.setVisibility(View.VISIBLE);


                        }

                    }


                } catch (Exception e) {
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetRewardReport","MemberId="+userBasicData.get("UserID")+"&PageIndex="+"1");


        }
    }

}

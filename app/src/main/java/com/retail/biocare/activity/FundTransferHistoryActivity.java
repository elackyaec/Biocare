package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.FundTransferHistoryAdapter;
import com.retail.biocare.adapter.PaymentHistoryAdapter;
import com.retail.biocare.model.FundTransferHistoryModel;
import com.retail.biocare.model.PaymentHistoryModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;
import com.testfairy.h.b.G;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class FundTransferHistoryActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<FundTransferHistoryModel> fundTransferHistoryModels;
    FundTransferHistoryAdapter fundTransferHistoryAdapter;
    TextView txtNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundtransferhistory);
        layoutBack=(RelativeLayout)findViewById(R.id.layout_back);
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
            new GetFundHistory().execute();
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

    private class GetFundHistory extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(FundTransferHistoryActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            fundTransferHistoryModels=new ArrayList<>();
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

String username=c.getString("username");
                        String fullnmae=c.getString("CustomerName");
                        String date=c.getString("Datecreated");
                        String debit=c.getString("Debit");
                        String message=c.getString("Message");
                        String credit=c.getString("Credit");

                        fundTransferHistoryModels.add(new FundTransferHistoryModel(username,fullnmae,credit,debit,message,date));
                        if(fundTransferHistoryModels.size()>0)
                        {
                            fundTransferHistoryAdapter=new FundTransferHistoryAdapter(FundTransferHistoryActivity.this,fundTransferHistoryModels);
                            recyclerView.setAdapter(fundTransferHistoryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(FundTransferHistoryActivity.this));
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
            return new ExtractfromReply().performPost("WSMember","GetFundTransferHistory","MemberId="+userBasicData.get("UserID")+"&PageIndex="+"1");


        }
    }

}

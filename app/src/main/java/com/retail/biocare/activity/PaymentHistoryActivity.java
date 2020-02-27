package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.PaymentHistoryAdapter;
import com.retail.biocare.adapter.PendingWFundAdapter;
import com.retail.biocare.model.PaymentHistoryModel;
import com.retail.biocare.model.PendingWithdrawModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class PaymentHistoryActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<PaymentHistoryModel> paymentHistoryModels;
    PaymentHistoryAdapter paymentHistoryAdapter;
    TextView txtNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymenthistory);
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
            new GetPaymentHistory().execute();
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

    private class GetPaymentHistory extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(PaymentHistoryActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            paymentHistoryModels=new ArrayList<>();
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

String username=c.getString("username");
                        String fullnmae=c.getString("CustomerName");
                        String date=c.getString("Datecreated");
                        String amount=c.getString("Debit");
                        String message=c.getString("Message");
                        paymentHistoryModels.add(new PaymentHistoryModel(username,fullnmae,amount,date,message));
                        if(paymentHistoryModels.size()>0)
                        {
                            paymentHistoryAdapter=new PaymentHistoryAdapter(PaymentHistoryActivity.this,paymentHistoryModels,userBasicData.get("Username"));
                            recyclerView.setAdapter(paymentHistoryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(PaymentHistoryActivity.this));
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
            return new ExtractfromReply().performPost("WSMember","GetPaymentHistory","MemberId="+userBasicData.get("UserID")+"&PageIndex="+"1");


        }
    }

}

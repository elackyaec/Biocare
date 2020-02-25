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
import com.retail.biocare.adapter.FundTransferHistoryAdapter;
import com.retail.biocare.adapter.PayoutHistoryAdapter;
import com.retail.biocare.model.FundTransferHistoryModel;
import com.retail.biocare.model.PackageModel;
import com.retail.biocare.model.PayoutHistoryModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class PayoutHistoryActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<PayoutHistoryModel> payoutHistoryModels;
    PayoutHistoryAdapter payoutHistoryAdapter;
    TextView txtNotFound;
    Spinner spinner;
    ArrayList<String> options;
    String dateselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payouthistory);
        layoutBack=(RelativeLayout)findViewById(R.id.layout_back);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
txtNotFound=(TextView)findViewById(R.id.txt_notfound);
        spinner = (Spinner) findViewById(R.id.spinner);

        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
        {
            new GetDates().execute();



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

    private class GetPayout extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(PayoutHistoryActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            payoutHistoryModels=new ArrayList<>();
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
                        String date=c.getString("PayoutDate");
                        String debit=c.getString("Amount");
                       // String message=c.getString("Message");
                        //String credit=c.getString("Credit");
                        String fin1 = c.getString("Bank").replace("&lt;","");
                        String fin2 = fin1.replace("&gt;","");
                        String fin3=fin2.replace("&amp;","");
                        String fin4=fin3.replace("nbsp;","");
                        String fin5=fin4.replace("br"," ");
                        Log.e("FI",fin5);
                        String token[]=fin5.split(" ");
                        String one=token[0];
                        String two=token[1];
                        String three=token[2];
                        String four=token[3];
                        String five=token[4];

                        String six[]=one.split(":");
                        String accname=six[1];
                        String seven[]=two.split(":");
                        String accn0=seven[1];
                        String eight[]=three.split(":");
                        String bankname=eight[1];
                        String nine[]=four.split(":");
                        String branch=nine[1];
                        String ten[]=five.split(":");
                        String bankcode=ten[1];
                        payoutHistoryModels.add(new PayoutHistoryModel(username,fullnmae,"",date,debit,accname,accn0,bankname,branch,bankcode));

                        if(payoutHistoryModels.size()>0)
                        {
                            payoutHistoryAdapter=new PayoutHistoryAdapter(PayoutHistoryActivity.this,payoutHistoryModels);
                            recyclerView.setAdapter(payoutHistoryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(PayoutHistoryActivity.this));
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
            return new ExtractfromReply().performPost("WSMember","GetPayoutreport","MemberId="+userBasicData.get("UserID")+"&PayoutDate="+strings[0]+"&PageIndex="+"1");


        }
    }
    private class GetDates extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            options = new ArrayList<>();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            if (s.equals("NODATA")) {


            } else {

                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject c = jsonArray.getJSONObject(i);


                        options.add(c.getString("payoutdate"));

                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_spinner_dropdown_three, options);
                        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
dateselect=options.get(position);
Log.e("date",dateselect);
                              new GetPayout().execute(dateselect);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }


                } catch (Exception e) {
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "GetPayoutddl", "");


        }
    }

}

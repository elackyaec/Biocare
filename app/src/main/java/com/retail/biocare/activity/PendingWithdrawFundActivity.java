package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.PendingWFundAdapter;
import com.retail.biocare.adapter.UnusedEpinsAdapter;
import com.retail.biocare.model.PendingWithdrawModel;
import com.retail.biocare.model.UnusedEpinModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;
import com.testfairy.h.b.G;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class PendingWithdrawFundActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<PendingWithdrawModel> pendingWithdrawModels;
    PendingWFundAdapter pendingWFundAdapter;
    TextView txtNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingwithdraw);
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
            new GetPendingFunds().execute();
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

    private class GetPendingFunds extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(PendingWithdrawFundActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            pendingWithdrawModels=new ArrayList<>();
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
String bank=c.getString("Bank");

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
                        pendingWithdrawModels.add(new PendingWithdrawModel(username,fullnmae,"",date,amount,accname,accn0,bankname,branch,bankcode));

                        if(pendingWithdrawModels.size()>0)
                        {
                            pendingWFundAdapter=new PendingWFundAdapter(PendingWithdrawFundActivity.this,pendingWithdrawModels);
                            recyclerView.setAdapter(pendingWFundAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(PendingWithdrawFundActivity.this));
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
            return new ExtractfromReply().performPost("WSMember","GetPendingWithdrawfund","MemberId="+userBasicData.get("UserID")+"&PayoutDate="+"0"+"&PageIndex="+"1");


        }
    }

}

package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.retail.biocare.MainActivity;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.retail.biocare.StaticData.StaticDatas.bankDetailsMap;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class ManageBankActivity extends AppCompatActivity {

    private static final String TAG = "ManageBankActivity";
    private TextView txtAccountName;
    private TextView txtAccountNumber;
    private TextView txtBankName;
    private TextView txtBranchName;
    private TextView txtAccountType;
    private TextView txtIFSC;
    private TextView txtEmail;
    float floatmat,floatbal;
    TextView txtTotal;
    String balance,pwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        txtTotal=(TextView)findViewById(R.id.txt_total);

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btnupdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViews();


        if (bankDetailsMap.isEmpty())
            MainActivity.bankDetailsListener.onBankDataChange();

        else
            setData();

        new GetBalance().execute();

    }

    private void findViews() {

            txtAccountName = (TextView)findViewById( R.id.txtAccountName );
            txtAccountNumber = (TextView)findViewById( R.id.txtAccountNumber );
            txtBankName = (TextView)findViewById( R.id.txtBankName );
            txtBranchName = (TextView)findViewById( R.id.txtBranchName );
            txtAccountType = (TextView)findViewById( R.id.txtAccountType );
            txtIFSC = (TextView)findViewById( R.id.txtIFSC );
            txtEmail = (TextView)findViewById( R.id.txtEmail );
    }

    private void setData() {

        txtAccountName.setText(bankDetailsMap.get("Customername"));
        txtAccountNumber.setText(bankDetailsMap.get("AccountNumber"));
        txtBankName.setText(bankDetailsMap.get("BankName"));
        txtBranchName.setText(bankDetailsMap.get("Branch"));
        txtAccountType.setText(bankDetailsMap.get("AccountType"));
        txtIFSC.setText(bankDetailsMap.get("BankCode"));
        txtEmail.setText(bankDetailsMap.get("Email"));

    }
    private class GetBalance extends AsyncTask<String, String, String> {

        //ProgressDialog progressDialog = new ProgressDialog(ProfileDetailsActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("NODATA")){


            }
            else
            {
                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject c=jsonArray.getJSONObject(i);

                        balance=c.getString("Balance");
                        pwd=c.getString("TransactionPassword");
                        float value=Float.parseFloat(balance);
                        String s1 = String.format("%.2f", value);
                        floatbal=Float.parseFloat(balance);

                        txtTotal.setText("$"+s1);

                        Log.e("PWD",pwd);
                    }


                } catch (Exception e) {
                }

            }


        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetEwalletbalanceTranspwd","MemberId="+userBasicData.get("UserID"));
        }
    }


}

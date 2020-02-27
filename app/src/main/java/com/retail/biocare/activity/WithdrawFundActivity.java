package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.MainActivity;
import com.retail.biocare.R;
import com.retail.biocare.adapter.UnusedEpinsAdapter;
import com.retail.biocare.model.UnusedEpinModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class WithdrawFundActivity extends AppCompatActivity {
    String balance, pwd, amount, entered_pwd;
    TextView txtBalance, txtTotal;
    Button btnWihdraw, btnCancel;
    EditText edtAmt, edtPwd;
    float floatmat, floatbal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawfund);
        txtBalance = (TextView) findViewById(R.id.txt_balance);
        btnWihdraw = (Button) findViewById(R.id.btn_withdraw);
        edtAmt = (EditText) findViewById(R.id.edt_amount);
        edtPwd = (EditText) findViewById(R.id.edt_pwd);
        txtTotal = (TextView) findViewById(R.id.txt_total);

        btnCancel = (Button) findViewById(R.id.btn_cancel);

        if (GlobalMethods.isNetworkAvailable(getApplicationContext())) {
            new GetBalance().execute();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnWihdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
    }

    private void validation() {
        boolean cancel = false;
        amount = edtAmt.getText().toString();
        entered_pwd = edtPwd.getText().toString();
        try {
            floatmat = Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(edtAmt.getText().toString())) {
            cancel = true;
            Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(edtPwd.getText().toString())) {
            cancel = true;
            Toast.makeText(getApplicationContext(), "Enter Transaction Password", Toast.LENGTH_SHORT).show();

        } else if (floatmat > floatbal) {
            cancel = true;
            Toast.makeText(getApplicationContext(), "Available balance is low", Toast.LENGTH_SHORT).show();
        } else if (!entered_pwd.equalsIgnoreCase(pwd)) {
            cancel = true;
            Toast.makeText(getApplicationContext(), "Passwords mismatch", Toast.LENGTH_SHORT).show();
        }


        if (!cancel) {
            if (GlobalMethods.isNetworkAvailable(getApplicationContext())) {
                new WithdrawFund().execute();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

            if (s.equals("NODATA")) {


            } else {
                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);

                        balance = c.getString("Balance");
                        pwd = c.getString("TransactionPassword");
                        float value = Float.parseFloat(balance);
                        String s1 = String.format("%.2f", value);
                        floatbal = Float.parseFloat(balance);

                        txtBalance.setText("$" + s1);
                        txtTotal.setText("$" + s1);

                        Log.e("PWD", pwd);
                    }


                } catch (Exception e) {
                }

            }


        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "GetEwalletbalanceTranspwd", "MemberId=" + userBasicData.get("UserID"));
        }
    }

    private class WithdrawFund extends AsyncTask<String, String, String> {

        //ProgressDialog progressDialog = new ProgressDialog(ProfileDetailsActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("NODATA")) {


            } else  {

                Intent intent=new Intent(WithdrawFundActivity.this,MainActivity.class);
                intent.putExtra("page","2");
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Fund Withdrawn Successfully", Toast.LENGTH_SHORT).show();

            }


        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "WithdrawfundSave", "MemberId=" + userBasicData.get("UserID") + "&Debit=" + amount);
        }
    }

}

package com.retail.biocare.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.retail.biocare.MainActivity;
import com.retail.biocare.R;
import com.retail.biocare.model.Usermodel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class FundTransferActivity extends AppCompatActivity {

    TextView txtUsername,txtTotal;
    EditText edtOtherMemberID,edtAmount,edtPassword;
    Button btnTransfer,btnCancel;
    String touserid,amount,password;
    String balance,pwd,userselect;
    float floatmat,floatbal;
    Spinner spinner;
    List<Usermodel> usermodels;
    ArrayList<String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundtransfer);
        txtUsername=(TextView)findViewById(R.id.txt_username);
        edtOtherMemberID=(EditText) findViewById(R.id.edt_memberid);
        edtAmount=(EditText)findViewById(R.id.edt_amount);
        edtPassword=(EditText)findViewById(R.id.edt_pwd);
        btnTransfer=(Button)findViewById(R.id.btn_withdraw);
        btnCancel=(Button)findViewById(R.id.btn_cancel);
        txtTotal = (TextView) findViewById(R.id.txt_total);
        spinner = (Spinner) findViewById(R.id.spinner);

        btnTransfer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        validation();
    }
});
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

        if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
        {
          new GetBalance().execute();
           // new GetUsers().execute();

        }
        else
        {
            Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
        }

        txtUsername.setText(userBasicData.get("Username"));
    }

    private void validation() {
        boolean cancel=false;
        amount=edtAmount.getText().toString() ;
        touserid=edtOtherMemberID.getText().toString();
        password=edtPassword.getText().toString();
        try {
            floatmat=Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if(TextUtils.isEmpty(edtOtherMemberID.getText().toString()))
        {
            cancel=true;
            Toast.makeText(getApplicationContext(),"Enter To User ID",Toast.LENGTH_SHORT).show();

        }
        else  if(TextUtils.isEmpty(edtOtherMemberID.getText().toString()))
        {
            cancel=true;
            Toast.makeText(getApplicationContext(),"Enter To User ID",Toast.LENGTH_SHORT).show();

        }
        else  if(TextUtils.isEmpty(edtPassword.getText().toString()))
        {
            cancel=true;
            Toast.makeText(getApplicationContext(),"Enter Transaction Password",Toast.LENGTH_SHORT).show();

        }

        else if(floatmat>floatbal)
        {
            cancel=true;
            Toast.makeText(getApplicationContext(),"Available balance is low",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equalsIgnoreCase(pwd))
        {
            cancel=true;
            Toast.makeText(getApplicationContext(),"Passwords mismatch",Toast.LENGTH_SHORT).show();
        }



        if(!cancel)
        {
            if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
            {
               new TransferFund().execute();
            }
            else
            {
                Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
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

                        float value = Float.parseFloat(balance);
                        String s1 = String.format("%.2f", value);
                        floatbal = Float.parseFloat(balance);

                        txtTotal.setText("$"+s1);



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
    private class TransferFund extends AsyncTask<String, String, String> {

        //ProgressDialog progressDialog = new ProgressDialog(ProfileDetailsActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  float value = Float.parseFloat(s);
          //  String s1 = String.format("%.2f", value);
            if (s.equals("NODATA")){


            }

            else
            {

                Intent intent=new Intent(FundTransferActivity.this, MainActivity.class);
                intent.putExtra("page","2");
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(),"Fund Transferred Successfully",Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","FundTransferSave","FromUserId="+userBasicData.get("UserID")+"&ToUserId="+touserid+"&Transamount="+amount);
        }
    }
    private class GetUsers extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            usermodels = new ArrayList<>();
            options=new ArrayList<>();
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

options.add(c.getString("Customername"));
                        usermodels.add(new Usermodel(c.getString("Customername"),c.getString("Name"),c.getString("Id")));

                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_spinner_dropdown_three, options);
                        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                userselect=options.get(position);
                                Log.e("user",userselect);
                                edtOtherMemberID.setText(usermodels.get(position).getId());
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
            return new ExtractfromReply().performPost("WSMember", "GetUserddl", "");


        }
    }

}

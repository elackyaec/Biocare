package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.retail.biocare.Models.TransactionModels;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.utils.ExtractfromReply;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class ChangeTransactionPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ChangeTransactionPasswo";

    private EditText txtNewPassword, txtConfirmNewPassword;
    private TextView txtName, txtuserCode;

    TransactionModels transactionModels;

    private String confirmNewPassword;
    private String CustomerID, TransactionPassword, MemberId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_transaction_password);


        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPassword);
        txtName = findViewById(R.id.txtName);
        txtuserCode =findViewById(R.id.txtuserCode);
        txtName.setText(userProfileData.get("Firstname"));
        txtuserCode.setText(StaticDatas.userBasicData.get("Username"));

        findViewById(R.id.btncancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerID = userBasicData.get("UserID");
                TransactionPassword = String.valueOf(txtNewPassword.getText());
                confirmNewPassword = String.valueOf(txtConfirmNewPassword.getText());

                if (TransactionPassword.isEmpty()){
                    txtNewPassword.requestFocus();
                    txtNewPassword.setError("Required");
                }

                else if (confirmNewPassword.isEmpty()){
                    txtConfirmNewPassword.requestFocus();
                    txtConfirmNewPassword.setError("Required");
                }

                else if(!TransactionPassword.equals(confirmNewPassword))
                {
                    txtConfirmNewPassword.requestFocus();
                    txtConfirmNewPassword.setError("Passwords do not match");
                }

                else {

                    transactionModels = new TransactionModels(CustomerID, TransactionPassword, "0");

                    String jsonData = "["+new Gson().toJson(transactionModels)+"]";
                    Log.d(TAG, "onClick: "+jsonData);

                    new UpdateTransactionPassword().execute(jsonData);

                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class UpdateTransactionPassword extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(ChangeTransactionPasswordActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();


        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            Log.d(TAG, "onPostExecute: "+s);

            if (!s.equalsIgnoreCase("NODATA"))
            {
                Toast.makeText(ChangeTransactionPasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","ChangeTranpwd","jsonString="+strings[0]+"&KeyValue=0");
        }
    }
}

package com.retail.biocare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.retail.biocare.Models.PasswordModel;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.activity.ChangeTransactionPasswordActivity;
import com.retail.biocare.utils.ExtractfromReply;

import static com.retail.biocare.StaticData.StaticDatas.loginPassword;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG = "ChangePasswordActivity";

    private PasswordModel passwordModel;

    private EditText txtOldPassword, txtNewPassword, txtConfirmNewPassword;

    private String  ConfirmNewPassword, oldpassword;

    private String CustomerID, Password, TransactionPassword, MemberId;
    private TextView txtName, txtuserCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtOldPassword = findViewById(R.id.textOldPassword);
        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPassword);
        txtName = findViewById(R.id.txtName);
        txtuserCode =findViewById(R.id.txtuserCode);
        txtName.setText(userProfileData.get("Firstname"));
        txtuserCode.setText(StaticDatas.userBasicData.get("Username"));
        findViewById(R.id.btUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        findViewById(R.id.btcancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void validate() {

        CustomerID = userBasicData.get("UserID");
        oldpassword = String.valueOf(txtOldPassword.getText());
        Password = String.valueOf(txtNewPassword.getText());
        ConfirmNewPassword = String.valueOf(txtConfirmNewPassword.getText());

        if (oldpassword.isEmpty()) {
            txtOldPassword.requestFocus();
            txtOldPassword.setError("Required");
        }

        else if (Password.isEmpty()) {
            txtNewPassword.requestFocus();
            txtNewPassword.setError("Required");
        }

        else if (ConfirmNewPassword.isEmpty()) {
            txtConfirmNewPassword.requestFocus();
            txtConfirmNewPassword.setError("Required");
        }

        else if (!Password.equals(ConfirmNewPassword)){
            txtConfirmNewPassword.requestFocus();
            txtConfirmNewPassword.setError("Passwords donot match");
        }

        else if (!oldpassword.equals(StaticDatas.loginPassword)){
            txtOldPassword.requestFocus();
            txtOldPassword.setError("Password Error");
        }

        else{

            passwordModel = new PasswordModel( CustomerID, Password, "", "0");
            String jsonPassword = "["+new Gson().toJson(passwordModel)+"]";

            Log.d(TAG, "validate: "+jsonPassword);

            new UpdatePassword().execute(jsonPassword);



        }


    }


    private class UpdatePassword extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(ChangePasswordActivity.this);

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
            if(!s.equalsIgnoreCase("NODATA")){
                loginPassword = Password;
                Toast.makeText(ChangePasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","Changepwd","jsonString="+strings[0]+"&KeyValue=0");
        }
    }

}

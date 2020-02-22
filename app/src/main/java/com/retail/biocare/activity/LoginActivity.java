package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.retail.biocare.MainActivity;
import com.retail.biocare.R;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.retail.biocare.StaticData.StaticDatas.hostURL;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername= findViewById(R.id.txtUsername);
        txtPassword= findViewById(R.id.txtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void validate() {

        String userName = String.valueOf(txtUsername.getText());
        String passWord = String.valueOf(txtPassword.getText());
        
        if (userName.isEmpty()){
            
            try {
                txtUsername.requestFocus();
                txtUsername.setError("Enter UserName");
            }
            catch (Exception e){

                Log.d(TAG, "validate: Exception: "+e);
                Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
            }
            
        }

        else if (passWord.isEmpty()){

            try {
                txtPassword.requestFocus();
                txtPassword.setError("Enter Password");
            }
            catch (Exception e){

                Log.d(TAG, "validate: Exception: "+e);
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            }

        }

        else {
            login(userName, passWord);
        }

    }

    private void login(String userName, String passWord) {
        new LoginAsync().execute(userName, passWord);
    }





    private class LoginAsync extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);

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
            Log.d(TAG, "onPostExecute: "+ s);

            if (s.equals("NODATA")){
                Log.d(TAG, "onPostExecute: NODATA");
            }
            else if(s.equalsIgnoreCase("Invalid call")) {
                Toast.makeText(LoginActivity.this, "Signature mismatch", Toast.LENGTH_SHORT).show();
            }
            else if (s.equals("[]")){
                Toast.makeText(LoginActivity.this, "Invalid Login!!!", Toast.LENGTH_SHORT).show();
            }
            else{

                try {

                    JSONArray jsonArray = new JSONArray(s);
                    JSONObject c = jsonArray.getJSONObject(0);

                    //Log.d(TAG, "onPostExecute: UserType: "+c.getString("UserType"));

                    userBasicData.put("UserType",c.getString("UserType"));
                    userBasicData.put("UserTypeId",c.getString("UserTypeId"));
                    userBasicData.put("UserID",c.getString("UserID"));
                    userBasicData.put("Username",c.getString("Username"));
                    userBasicData.put("Name",c.getString("Name"));
                    userBasicData.put("Password",c.getString("Password"));
                    userBasicData.put("TransactionPassword",c.getString("TransactionPassword"));
                    userBasicData.put("Email",c.getString("Email"));
                    userBasicData.put("Mobile",c.getString("Mobile"));
                    userBasicData.put("Timein",c.getString("Timein"));
                    userBasicData.put("Timeout",c.getString("Timeout"));
                    userBasicData.put("Status",c.getString("Status"));
                    userBasicData.put("Two",c.getString("Two"));
                    userBasicData.put("Photo",c.getString("Photo"));
                    userBasicData.put("Paypal",c.getString("Paypal"));
                    userBasicData.put("Payza",c.getString("Payza"));
                    userBasicData.put("Datecreated",c.getString("Datecreated"));
                    userBasicData.put("Currency",c.getString("Currency"));

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                } catch (Exception e) {
                    Log.e(TAG, "onPostExecute: Exception : "+e);
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","Login","username="+strings[0]+"&password="+strings[1]);
        }
    }

}

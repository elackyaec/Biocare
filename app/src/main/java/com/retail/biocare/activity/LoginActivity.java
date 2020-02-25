package com.retail.biocare.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.retail.biocare.Login_test;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.retail.biocare.StaticData.StaticDatas.hostURL;
import static com.retail.biocare.StaticData.StaticDatas.loginPassword;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText txtUsername, txtPassword;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

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

        findViewById(R.id.lblSignUP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Login_test.class));
            }
        });

        callMultiplePermissions();
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


    private void callMultiplePermissions() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
       /* if (!addPermission(permissionsList, Manifest.permission.ACCESS_NETWORK_STATE))
            permissionsNeeded.add("NETWORK STATE");*/
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("WRITE EXTERNAL STORAGE");
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("READ EXTERNAL STORAGE");
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("CAMERA");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsNeeded.add("ACCESS COARSE LOCATION");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("ACCESS FINE LOCATION");


        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);

                if (Build.VERSION.SDK_INT >= 23) {
                    // Marshmallow+
                    requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                            REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                } else {
                    // Pre-Marshmallow
                }

                return;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                // Marshmallow+
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            } else {
                // Pre-Marshmallow
            }

            return;
        }

    }

    /**
     * add Permissions
     *
     * @param permissionsList
     * @param permission
     * @return
     */
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        } else {
            // Pre-Marshmallow
        }

        return true;
    }

    /**
     * Permissions results
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION and others

              /*  perms.get(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        &&*/

                if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted

                } else {
                    // Permission Denied
                    Toast.makeText(LoginActivity.this, "Permission is Denied", Toast.LENGTH_SHORT)
                            .show();

                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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

                    loginPassword = String.valueOf(txtPassword.getText());

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

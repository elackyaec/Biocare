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

public class ManageBankActivity extends AppCompatActivity {

    private static final String TAG = "ManageBankActivity";
    private TextView txtAccountName;
    private TextView txtAccountNumber;
    private TextView txtBankName;
    private TextView txtBranchName;
    private TextView txtAccountType;
    private TextView txtIFSC;
    private TextView txtEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

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

        findViews();


        if (bankDetailsMap.isEmpty())
            MainActivity.bankDetailsListener.onBankDataChange();

        else
            setData();

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


}

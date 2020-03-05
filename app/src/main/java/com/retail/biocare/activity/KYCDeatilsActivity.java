package com.retail.biocare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;

public class KYCDeatilsActivity extends AppCompatActivity {
    TextView txtSubmittedBy,txtSubmittedOn,txtCheckedby,txtCheckedOn,txtAdminNote,txtApproved;
    TextView txtFname,txtLname,txtPhone,txtDob,txtGender,txtUsernaame,txtWalletType,txtWalletAddress;
    TextView txtAddressline1,txtAddressline2,txtState,txtCity,txtZip,txtCountry;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kyclist);

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtSubmittedBy=(TextView)findViewById(R.id.txtsubmittedby);
        txtSubmittedOn=(TextView)findViewById(R.id.txtsubmittedon);
        txtCheckedby=(TextView)findViewById(R.id.txtcheckedby);
        txtCheckedOn=(TextView)findViewById(R.id.txtcheckedon);
        txtAdminNote=(TextView)findViewById(R.id.txt_adminnote);
        txtApproved=(TextView)findViewById(R.id.txt_approvedstatus);
        txtFname=(TextView)findViewById(R.id.txt_firstname);
        txtLname=(TextView)findViewById(R.id.txt_lastname);
        txtPhone=(TextView)findViewById(R.id.txt_phone);
        txtDob=(TextView)findViewById(R.id.txt_dob);
        txtGender=(TextView)findViewById(R.id.txt_gender);
        txtUsernaame=(TextView)findViewById(R.id.txt_telegram);
        txtAddressline1=(TextView)findViewById(R.id.txtAddress1);
        txtAddressline2=(TextView)findViewById(R.id.txtAddress2);
        txtState=(TextView)findViewById(R.id.txtstate);
        txtCity=(TextView)findViewById(R.id.txtCity);
        txtZip=(TextView)findViewById(R.id.txtPincode);
        txtCountry=(TextView)findViewById(R.id.txtCountry);
        txtWalletType=(TextView)findViewById(R.id.txtwallettype);
        txtWalletAddress=(TextView)findViewById(R.id.txtwalletaddress);
      //  txtSubmittedBy.setText(submittedby);
      //  txtSubmittedOn.setText(submittedon);
     //   txtCheckedby.setText(checkedby);
      //  txtCheckedOn.setText(checckedon);
      //  txtAdminNote.setText(adminnote);
        txtFname.setText(StaticDatas.userProfileData.get("Firstname"));
        txtLname.setText(StaticDatas.userProfileData.get("Lastname"));
        txtPhone.setText(StaticDatas.userProfileData.get("Phone"));
        txtDob.setText(StaticDatas.userProfileData.get("Dateofbirth"));
        txtGender.setText(StaticDatas.userProfileData.get("Gender"));
        txtUsernaame.setText(StaticDatas.userProfileData.get("Username"));
        txtAddressline1.setText(StaticDatas.userProfileData.get("AddressLine1"));
        txtAddressline2.setText(StaticDatas.userProfileData.get("AddressLine2"));
        txtState.setText(StaticDatas.userProfileData.get("State"));
        txtCity.setText(StaticDatas.userProfileData.get("City"));
        txtZip.setText(StaticDatas.userProfileData.get("Zipcode"));
        txtCountry.setText(StaticDatas.userProfileData.get("Country"));
      //  txtWalletType.setText(wallettype);
        //txtWalletAddress.setText(walletaddress);
    }
}

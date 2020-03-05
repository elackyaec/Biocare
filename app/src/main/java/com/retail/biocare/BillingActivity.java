package com.retail.biocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.retail.biocare.activity.CheckoutActivity;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class BillingActivity extends AppCompatActivity {

    private TextView txtNameMobile, txtAddressLine1, txtAddressLine2, txtAddress3, lblEdit;

    private String name, mobile, address1, address2, street, city, zip, email;

    private LinearLayout layoutBillingAddress;

    private CheckBox chkSameCheckBox;
    private EditText txtFullName, txtBillingEmail, txtBillingPhone, txtBillingAddress1, txtBillingCity, txtBillingState, txtBillingZipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        txtNameMobile = findViewById(R.id.txtNameMobile);
        txtAddressLine1 = findViewById(R.id.txtAddressLine1);
        txtAddressLine2 = findViewById(R.id.txtAddressLine2);
        txtAddress3 = findViewById(R.id.txtAddress3);
        chkSameCheckBox = findViewById(R.id.chkSameCheckBox);
        layoutBillingAddress = findViewById(R.id.layoutBillingAddress);
        lblEdit = findViewById(R.id.lblEdit);


        txtFullName = findViewById(R.id.txtFullName);
        txtBillingEmail = findViewById(R.id.txtBillingEmail);
        txtBillingPhone = findViewById(R.id.txtBillingPhone);
        txtBillingAddress1 = findViewById(R.id.txtBillingAddress1);
        txtBillingCity = findViewById(R.id.txtBillingCity);
        txtBillingCity = findViewById(R.id.txtBillingCity);
        txtBillingState = findViewById(R.id.txtBillingState);
        txtBillingZipcode = findViewById(R.id.txtBillingZipcode);


        txtAddress3.setVisibility(View.GONE);

        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        chkSameCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {




                }
                else {

                }

            }
        });

        setdata();

        lblEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                name = userBasicData.get("Name");
                mobile = userBasicData.get("Mobile");
                address1 = userProfileData.get("AddressLine1");
                address2 = userProfileData.get("AddressLine2");
                street = userProfileData.get("City");
                city = userProfileData.get("City");
                zip = userProfileData.get("Zipcode");
                email = userProfileData.get("Email");
*/
                txtFullName.setText(name);
                txtBillingEmail.setText(email);
                txtBillingPhone.setText(mobile);
                txtBillingAddress1.setText(address1);
                txtBillingCity.setText(city);
                txtBillingState.setText(userProfileData.get("State"));
                txtBillingZipcode.setText(zip);


            }
        });

    }

    private void validate() {

        String billingName, billingEmail, billingPhone, billingAddress, billingCity, billingState, billingZipCode;

        billingName = String.valueOf(txtFullName.getText());
        billingEmail = String.valueOf(txtBillingEmail.getText());
        billingPhone = String.valueOf(txtBillingPhone.getText());
        billingAddress = String.valueOf(txtBillingAddress1.getText());
        billingCity = String.valueOf(txtBillingCity.getText());
        billingState = String.valueOf(txtBillingState.getText());
        billingZipCode = String.valueOf(txtBillingZipcode.getText());


        if (billingName.isEmpty()){
            txtFullName.requestFocus();
            txtFullName.setError("Required");
        }
        else if (billingEmail.isEmpty()){
            txtBillingEmail.requestFocus();
            txtBillingEmail.setError("Required");
        }
        else if (billingPhone.isEmpty()){
            txtBillingPhone.requestFocus();
            txtBillingPhone.setError("Required");
        }
        else if (billingAddress.isEmpty()){
            txtBillingAddress1.requestFocus();
            txtBillingAddress1.setError("Required");
        }
        else if (billingCity.isEmpty()){
            txtBillingCity.requestFocus();
            txtBillingCity.setError("Required");
        }
        else if (billingState.isEmpty()){
            txtBillingState.requestFocus();
            txtBillingState.setError("Required");
        }
        else if (billingZipCode.isEmpty()){
            txtBillingZipcode.requestFocus();
            txtBillingZipcode.setError("Required");
        }
        else {
            startActivity(new Intent(BillingActivity.this, CheckoutActivity.class));
        }

    }

    private void setdata() {

        name = userBasicData.get("Name");
        mobile = userBasicData.get("Mobile");
        address1 = userProfileData.get("AddressLine1");
        address2 = userProfileData.get("AddressLine2");
        street = userProfileData.get("City");
        city = userProfileData.get("City");
        zip = userProfileData.get("Zipcode");
        email = userProfileData.get("Email");

        txtNameMobile.setText(name+" - "+mobile);
        txtAddressLine1.setText(address1+", "+address2+",");
        txtAddressLine2.setText(city+", "+zip);

    }
}

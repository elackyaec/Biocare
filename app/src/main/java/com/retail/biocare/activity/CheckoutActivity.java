package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.retail.biocare.Models.CheckoutCartModel;
import com.retail.biocare.Models.CheckoutOrderModel;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.OrderSummaryAdapter;
import com.retail.biocare.utils.ExtractfromReply;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = "CheckoutActivity";

    private CheckoutCartModel checkoutCartModel;  //dt1
    private CheckoutOrderModel checkoutOrderModel; //dt2;

    private RadioGroup radPaymentMethod;
    private RadioButton radioWallet, radioOnline;

    private TextView txtSubtotal, txtSalesTax, txtDeliverycharge, txtDiscount, txtTotal, txtBalance;

    String billName, billphone, billEmail, billadd1, billadd2, billCity, billState, billCountry, billZipCode, comments, paymentMethods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        radPaymentMethod = findViewById(R.id.radPaymentMethod);
        radioOnline = findViewById(R.id.radioOnline);
        radioWallet = findViewById(R.id.radioWallet);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.layoutChooseAddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CheckoutActivity.this, "ToDo", Toast.LENGTH_SHORT).show();

            }
        });


        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preProcess();
            }
        });

        radPaymentMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioWallet.isChecked())
                    paymentMethods = "Wallet";
                else
                    paymentMethods = "Online";
            }
        });

        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtBalance = findViewById(R.id.txtBalance);
        txtSalesTax = findViewById(R.id.txtSalesTax);
        txtDeliverycharge = findViewById(R.id.txtDeliverycharge);
        txtTotal = findViewById(R.id.txtTotal);

        initRecycler();
        setData();

        Intent intent = getIntent();
        billName = intent.getStringExtra("billName");
        billphone = intent.getStringExtra("billphone");
        billadd1 = intent.getStringExtra("billadd1");
        billadd2 = intent.getStringExtra("billadd2");
        billCity = intent.getStringExtra("billCity");
        billState = intent.getStringExtra("billState");
        billCountry = intent.getStringExtra("billCountry");
        billZipCode = intent.getStringExtra("billZipCode");
        billEmail = intent.getStringExtra("billEmail");
        comments = StaticDatas.cartComments;
        paymentMethods= "Wallet";

    }

    private void preProcess() {

       // checkoutCartModel = new CheckoutCartModel()

        String jsonCustomerOrderDetails = "["+new Gson().toJson(new CheckoutOrderModel(StaticDatas.userProfileData.get("CustomerID"),
                "",
                billName,
                billphone,
                billEmail,
                billadd1,
                billadd2,
                billCity,
                billState,
                billCountry,
                billZipCode,
                billName,
                billphone,
                billEmail,
                billadd1,
                billadd1,
                billCity,
                billState,
                billCountry,
                billZipCode,
                comments,
                paymentMethods
                ))+"]";

        String jsonCartItems = new Gson().toJson(StaticDatas.cartDetailsNew);
        Log.d(TAG, "preProcess: jsonCartItems: "+ jsonCartItems);

        Log.d(TAG, "preProcess: jsonUserDetails: "+jsonCustomerOrderDetails);


        new Checkout().execute(jsonCustomerOrderDetails, jsonCartItems);


    }

    private void setData() {

        float itemTotal = 0, taxTotal = 0, shippingTotal = 0, discountTotal = 0, totalPrice = 0;

        for (int i = 0; i < StaticDatas.cartDetails.size(); i++) {

            itemTotal += StaticDatas.cartDetails.get(i).getItemPrice() * Float.parseFloat(StaticDatas.cartDetails.get(i).getItemQty());
            taxTotal += StaticDatas.cartDetails.get(i).getItemTax();
            shippingTotal += StaticDatas.cartDetails.get(i).getItemShipping();

        }

        totalPrice = itemTotal + taxTotal + shippingTotal;

        txtSubtotal.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f", itemTotal));
        txtSalesTax.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f", taxTotal));
        txtDeliverycharge.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f", shippingTotal));

        txtTotal.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f", totalPrice));

        txtBalance.setText(userBasicData.get("Currency") + StaticDatas.AvailableBalance);

    }

    private void initRecycler() {


        RecyclerView recyclerView = findViewById(R.id.recyclerOrderSummary);
        OrderSummaryAdapter orderSummaryAdapter = new OrderSummaryAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderSummaryAdapter);


    }

    private class Checkout extends AsyncTask<String, String, String>{

        private ProgressDialog progressDialog = new ProgressDialog(CheckoutActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            Log.d(TAG, "onPostExecute: Response: "+s);

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","OrderSave","jsonOrder="+strings[0]+"&jsonDetail="+strings[1]);
        }
    }
}

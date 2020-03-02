package com.retail.biocare.activity;

import android.os.Bundle;
import android.view.View;
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

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = "CheckoutActivity";

    private CheckoutCartModel checkoutCartModel;  //dt1
    private CheckoutOrderModel checkoutOrderModel; //dt2;

    private TextView txtSubtotal, txtSalesTax, txtDeliverycharge, txtDiscount, txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

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

        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtSalesTax = findViewById(R.id.txtSalesTax);
        txtDeliverycharge = findViewById(R.id.txtDeliverycharge);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtTotal = findViewById(R.id.txtTotal);

        initRecycler();
        setData();


    }

    private void preProcess() {

       // checkoutCartModel = new CheckoutCartModel()

        String jsonStrin1 = new Gson().toJson(checkoutCartModel);

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
        txtDiscount.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f", discountTotal));

        txtTotal.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f", totalPrice));


    }

    private void initRecycler() {


        RecyclerView recyclerView = findViewById(R.id.recyclerOrderSummary);
        OrderSummaryAdapter orderSummaryAdapter = new OrderSummaryAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderSummaryAdapter);


    }
}

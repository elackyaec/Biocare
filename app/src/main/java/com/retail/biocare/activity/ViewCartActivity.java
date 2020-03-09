package com.retail.biocare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.BillingActivity;
import com.retail.biocare.Interfaces.CartQuantityChanged;
import com.retail.biocare.Models.CartItemsModels;
import com.retail.biocare.Models.OrderSummaryModel;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.CartAdapter;

import java.util.ArrayList;

public class ViewCartActivity extends AppCompatActivity implements CartQuantityChanged {

    private CartAdapter cartAdapter;
    private ArrayList<CartItemsModels> cartItems = new ArrayList<>();
    private TextView txtTotalcost, txtSubtotal, txtSalesTax, txtDeliverycharge, txtTotal;

    private EditText txtComments;

    CartQuantityChanged cartQuantityChanged;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        cartQuantityChanged = this;

        txtTotalcost = findViewById(R.id.txtTotalcost);
        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtSalesTax = findViewById(R.id.txtSalesTax);
        txtDeliverycharge = findViewById(R.id.txtDeliverycharge);
        txtTotal = findViewById(R.id.txtTotal);
        txtComments = findViewById(R.id.txtComments);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnCheckout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StaticDatas.orderSummaryDetails.clear();
                for (int i=0; i<StaticDatas.cartDetails.size(); i++){

                    StaticDatas.orderSummaryDetails.add(new OrderSummaryModel(StaticDatas.cartDetails.get(i).getItemName(), StaticDatas.cartDetails.get(i).getItemQty(), (Float.parseFloat(StaticDatas.cartDetails.get(i).getItemQty()) * StaticDatas.cartDetails.get(i).getItemPrice())));

                }

                StaticDatas.cartComments = String.valueOf(txtComments.getText());
                //startActivity(new Intent(ViewCartActivity.this, CheckoutActivity.class));
                startActivityForResult(new Intent(ViewCartActivity.this, BillingActivity.class),2601);
            }
        });

        initRecycler();

        cartQuantityChanged.OncartQuantityChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2601)
            finish();

    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recyclerCartItems);
        cartAdapter = new CartAdapter(this, cartQuantityChanged);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void OncartQuantityChanged() {

        float tmpTotal = 0;
        float taxTotal=0, shippingTotal=0, totalPrice=0;

        for (int i=0; i<StaticDatas.cartDetails.size(); i++){
            tmpTotal+= Integer.parseInt(StaticDatas.cartDetails.get(i).getItemQty()) * StaticDatas.cartDetails.get(i).getItemPrice();
            taxTotal += StaticDatas.cartDetails.get(i).getItemTax();
            shippingTotal += StaticDatas.cartDetails.get(i).getItemShipping();
        }

        totalPrice = tmpTotal+taxTotal+shippingTotal;

        txtTotalcost.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",tmpTotal));
        txtSubtotal.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",tmpTotal));

        txtSalesTax.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f",taxTotal));
        txtDeliverycharge.setText(StaticDatas.userBasicData.get("Currency") + String.format("%.2f",shippingTotal));
        txtTotal.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",totalPrice));

    }
}

package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.OrderDetailModel;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.OrderDetailAdapter;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String TAG = "OrderDetailActivity";

    private OrderDetailAdapter orderDetailAdapter;
    private ArrayList<OrderDetailModel> orderDetails = new ArrayList<>();

    private TextView txtSubtotal, txtTax, txtDeliverycharge, txtTotal, txtOrderId;
    private ImageView imgSt1,imgSt2,imgSt3, imgSt4, imgSt5;
    ImageView imgOne,imgTwo,imgThree,imgFour;

    private String orderId="", orderStatus="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtTax = findViewById(R.id.txtTax);
        txtDeliverycharge = findViewById(R.id.txtDeliverycharge);
        txtTotal = findViewById(R.id.txtTotal);
        txtOrderId = findViewById(R.id.txtOrderId);

        imgOne = (ImageView) findViewById(R.id.img_1);
        imgTwo = (ImageView) findViewById(R.id.img_2);
        imgThree = (ImageView) findViewById(R.id.img_3);
        imgFour = (ImageView) findViewById(R.id.img_4);

        imgSt1 = findViewById(R.id.imgSt1);
        imgSt2 = findViewById(R.id.imgSt2);
        imgSt3 = findViewById(R.id.imgSt3);
        imgSt4 = findViewById(R.id.imgSt4);
        imgSt5 = findViewById(R.id.imgSt5);

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        orderStatus = intent.getStringExtra("orderStatus");

        txtOrderId.setText("Order ID: "+orderId);

        initRecycler();
        getData();

        Context context = this;

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(orderStatus.equalsIgnoreCase("Processing"))
        {
            imgOne.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            // imgSt1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));
            imgSt1.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt2.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));



        }
        else if(orderStatus.equalsIgnoreCase("Packed"))
        {
            imgOne.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgTwo.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));

            /*imgSt1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));
            imgSt2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));*/
            imgSt1.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt2.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt3.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));



        }
        else if(orderStatus.equalsIgnoreCase("Completed"))
        {
            imgOne.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgTwo.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgThree.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgFour.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));

            /*imgSt1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));
            imgSt2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));
            imgSt3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));
            imgSt4.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));*/
            imgSt1.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt2.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt3.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt4.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt5.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));



        }
        else if(orderStatus.equalsIgnoreCase("Delivered"))
        {
            imgOne.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgTwo.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgThree.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));

            /*imgSt1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));
            imgSt2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));
            imgSt3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(11,209,0)));*/
            imgSt1.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt2.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt3.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));
            imgSt4.setColorFilter(ContextCompat.getColor(context, R.color.siva_green));


        }
    }

    private void getData() {

        new GetOrderDetails().execute(orderId);

    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.orderdetailRecycler);
        orderDetailAdapter = new OrderDetailAdapter(this, orderDetails);
        recyclerView.setAdapter(orderDetailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class GetOrderDetails extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(OrderDetailActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            orderDetails.clear();
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            if (s.equalsIgnoreCase("NODATA")){
                Toast.makeText(OrderDetailActivity.this, "Nodata", Toast.LENGTH_SHORT).show();
            }
            else {

                try{

                    JSONObject jreader = new JSONObject(s);
                    JSONArray jsonArray = jreader.getJSONArray("Table2");

                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject c = jsonArray.getJSONObject(i);

                        String itenNane, itemQty, itemPrice, itemOption="";

                        itenNane = c.getString("ProductName");
                        itemPrice = c.getString("TotalAmount");
                        itemQty = c.getString("Quantity");

                        orderDetails.add(new OrderDetailModel(itenNane, itemQty, String.format("%.2f",Float.parseFloat(itemPrice)), itemOption));

                    }

                    orderDetailAdapter.notifyDataSetChanged();

                    jsonArray = jreader.getJSONArray("Table1");
                    JSONObject c = jsonArray.getJSONObject(0);

                    txtSubtotal.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",Float.parseFloat(c.getString("SumTotalAmount"))));
                    txtDeliverycharge.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",Float.parseFloat(c.getString("Shipping"))));
                    txtTax.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",Float.parseFloat(c.getString("Tax"))));
                    txtTotal.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",Float.parseFloat(c.getString("TotalOrderAmount"))));

                }
                catch (Exception e){
                    Toast.makeText(OrderDetailActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onPostExecute: ", e);
                }

            }
        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","MyOrderSearch","OrderId="+strings[0]);
        }
    }

}

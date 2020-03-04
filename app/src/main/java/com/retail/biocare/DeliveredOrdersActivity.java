package com.retail.biocare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.retail.biocare.Models.CompletedOrderModel;
import com.retail.biocare.Models.PendingOrdersModel;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.DeliveredRecyclerAdapter;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeliveredOrdersActivity extends AppCompatActivity {

    private static final String TAG = "DeliveredOrdersActivity";

    private DeliveredRecyclerAdapter  deliveredRecyclerAdapter;
    private ArrayList<CompletedOrderModel> deliveredOrderDetails = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivered_orders);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        intRecycler();
        getData();
    }

    private void getData() {
        new GetDeliveredOrders().execute("");
    }

    private void intRecycler() {
        RecyclerView recyclerDeliveredOrders = findViewById(R.id.recyclerDeliveredOrders);
        deliveredRecyclerAdapter = new DeliveredRecyclerAdapter(this, deliveredOrderDetails);
        recyclerDeliveredOrders.setAdapter(deliveredRecyclerAdapter);
        recyclerDeliveredOrders.setLayoutManager(new LinearLayoutManager(this));
    }

    private class GetDeliveredOrders extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(DeliveredOrdersActivity.this);

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

            if (s.equalsIgnoreCase("NODATA")){
                Log.d(TAG, "onPostExecute:  "+s);
            }
            else{

                try {

                    String orderId, userName, userId, userAddress, orderDate, orderStatus, itemCount, paymentType, orderAmount;

                    JSONArray jsonArray = new JSONArray(s);
                    for (int i=0; i< jsonArray.length(); i++){

                        JSONObject c = jsonArray.getJSONObject(i);

                        orderId = c.getString("OrderId");
                        userName = c.getString("Name");
                        userId = c.getString("Username");
                        userAddress = c.getString("shipadd1")+", "+c.getString("shipadd2")+", "+c.getString("shipcity");
                        orderDate = c.getString("Date");
                        orderStatus = c.getString("ostatus");
                        itemCount = c.getString("TotalQty");
                        paymentType = c.getString("PaymentMethod");
                        orderAmount = c.getString("Total");

                        if (orderStatus.equalsIgnoreCase("Delivered"))
                            deliveredOrderDetails.add(new CompletedOrderModel(orderId, userName, userId, userAddress, orderDate, orderStatus, itemCount, paymentType, orderAmount));

                    }

                    deliveredRecyclerAdapter.notifyDataSetChanged();
                }
                catch (Exception e){
                    Log.e(TAG, "onPostExecute: ",e );
                    Toast.makeText(DeliveredOrdersActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","MyOrderDetail", "MemberId="+ StaticDatas.userBasicData.get("UserID")+"&PageIndex=1");
        }
    }

}

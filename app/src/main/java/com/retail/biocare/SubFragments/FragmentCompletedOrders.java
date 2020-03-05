package com.retail.biocare.SubFragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.CompletedOrderModel;
import com.retail.biocare.Models.PendingOrdersModel;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.CompletedOrdersAdapter;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentCompletedOrders extends Fragment {

    private static final String TAG = "FragmentCompletedOrders";
    private View rootView;

    private ArrayList<CompletedOrderModel> completedOrderModel = new ArrayList<>();
    CompletedOrdersAdapter completedOrdersAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_completed_orders, container, false);

        initRecyclerView();
        setData();

        return rootView;
    }

    private void setData() {
        new GetCompletedOrders().execute("");

    }

    private void initRecyclerView() {

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerCompleted);
        completedOrdersAdapter = new CompletedOrdersAdapter(getContext(), completedOrderModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(completedOrdersAdapter);

    }

    private class GetCompletedOrders extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(getContext());

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

                        //if (orderStatus.equalsIgnoreCase("Completed") || orderStatus.equalsIgnoreCase("Delivered"))
                        if (orderStatus.equalsIgnoreCase("Delivered"))
                            completedOrderModel.add(new CompletedOrderModel(orderId, userName, userId, userAddress, orderDate, orderStatus, itemCount, paymentType, orderAmount));

                    }

                    completedOrdersAdapter.notifyDataSetChanged();
                }
                catch (Exception e){
                    Log.e(TAG, "onPostExecute: ",e );
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","MyOrderDetail", "MemberId="+ StaticDatas.userBasicData.get("UserID")+"&PageIndex=1");
        }
    }

}

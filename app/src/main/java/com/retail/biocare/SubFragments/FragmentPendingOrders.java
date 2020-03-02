package com.retail.biocare.SubFragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.PendingOrdersModel;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.PendingOrdersAdapter;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentPendingOrders extends Fragment {

    private static final String TAG = "FragmentPendingOrders";

    private View rootView;

    private ArrayList<PendingOrdersModel> pendingOrderDetails = new ArrayList<>();
    PendingOrdersAdapter pendingOrdersAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_pending_orders, container, false);

        initRecycler();

        setData();


        return rootView;
    }

    private void setData() {



    }

    private void initRecycler() {

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerPending);
        pendingOrdersAdapter = new PendingOrdersAdapter(getContext(), pendingOrderDetails);
        recyclerView.setAdapter(pendingOrdersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    private class GetPendingOrders extends AsyncTask<String, String, String>{

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

            if (!s.equalsIgnoreCase("NODATA")){
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
                            userAddress = "Address";  //ToDo
                            orderDate = c.getString("Date");
                            orderStatus = c.getString("ostatus");
                            itemCount = "0"; //ToDo
                            paymentType = c.getString("PaymentMethod");
                            orderAmount = c.getString("Total");

                            pendingOrderDetails.add(new PendingOrdersModel(orderId, userName, userId, userAddress, orderDate, orderStatus, itemCount, paymentType, orderAmount));

                        }

                        pendingOrdersAdapter.notifyDataSetChanged();
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

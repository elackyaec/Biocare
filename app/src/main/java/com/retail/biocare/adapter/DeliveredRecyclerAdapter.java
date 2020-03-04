package com.retail.biocare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.CompletedOrderModel;
import com.retail.biocare.Models.PendingOrdersModel;
import com.retail.biocare.R;
import com.retail.biocare.activity.OrderDetailActivity;

import java.util.ArrayList;

public class DeliveredRecyclerAdapter extends RecyclerView.Adapter<DeliveredRecyclerAdapter.DeliveredViewHolder> {

    private Context context;
    private ArrayList<CompletedOrderModel> deliveredOrderDetails;

    public DeliveredRecyclerAdapter(Context context, ArrayList<CompletedOrderModel> deliveredOrderDetails) {
        this.context = context;
        this.deliveredOrderDetails = deliveredOrderDetails;
    }

    @NonNull
    @Override
    public DeliveredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeliveredViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_pending_orders, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveredViewHolder holder, final int position) {
        holder.txtName.setText(deliveredOrderDetails.get(position).getUserName() + "("+ deliveredOrderDetails.get(position).getUserId() +")");
        holder.txtAddress.setText(deliveredOrderDetails.get(position).getUserAddress());
        holder.txtPaymentType.setText(deliveredOrderDetails.get(position).getOrderDate());
        holder.txtOrderstatus.setText(deliveredOrderDetails.get(position).getOrderStatus());
        holder.txtItemsCount.setText(deliveredOrderDetails.get(position).getItemCount() + " Items");
        //holder.txtCost.setText(userBasicData.get("Currency")+pendingOrderDetails.get(position).getOrderAmount());
        holder.txtCost.setText(deliveredOrderDetails.get(position).getOrderAmount());
        holder.txtOrderId.setText(deliveredOrderDetails.get(position).getOrderId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("orderId", deliveredOrderDetails.get(position).getOrderId());
                intent.putExtra("orderStatus", deliveredOrderDetails.get(position).getOrderStatus());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveredOrderDetails.size();
    }


    public class DeliveredViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtAddress, txtOrderstatus, txtItemsCount, txtPaymentType, txtOrderdate, txtCost, txtOrderId;

        public DeliveredViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtOrderstatus = itemView.findViewById(R.id.txtOrderstatus);
            txtItemsCount = itemView.findViewById(R.id.txtItemsCount);
            txtPaymentType = itemView.findViewById(R.id.txtPaymentType);
            txtOrderdate = itemView.findViewById(R.id.txtOrderdate);
            txtCost = itemView.findViewById(R.id.txtCost);
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
        }
    }

}

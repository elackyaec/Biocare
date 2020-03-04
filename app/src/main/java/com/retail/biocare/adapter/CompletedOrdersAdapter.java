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

public class CompletedOrdersAdapter extends  RecyclerView.Adapter<CompletedOrdersAdapter.CompletedOrdersViewHolder>{

    private Context context;
    //private ArrayList<CompletedOrderModel> completedOrderDetails;
    private ArrayList<PendingOrdersModel> completedOrderDetails;

    public CompletedOrdersAdapter(Context context, ArrayList completedOrderDetails) {
        this.context = context;
        this.completedOrderDetails = completedOrderDetails;
    }

    @NonNull
    @Override
    public CompletedOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompletedOrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_pending_orders, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedOrdersViewHolder holder, final int position) {

        holder.txtName.setText(completedOrderDetails.get(position).getUserName() + "("+ completedOrderDetails.get(position).getUserId() +")");
        holder.txtAddress.setText(completedOrderDetails.get(position).getUserAddress());
        holder.txtPaymentType.setText(completedOrderDetails.get(position).getOrderDate());
        holder.txtOrderstatus.setText(completedOrderDetails.get(position).getOrderStatus());
        holder.txtItemsCount.setText(completedOrderDetails.get(position).getItemCount() + " Items");
        //holder.txtCost.setText(userBasicData.get("Currency")+pendingOrderDetails.get(position).getOrderAmount());
        holder.txtCost.setText(completedOrderDetails.get(position).getOrderAmount());
        holder.txtOrderId.setText(completedOrderDetails.get(position).getOrderId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("orderId", completedOrderDetails.get(position).getOrderId());
                intent.putExtra("orderStatus", completedOrderDetails.get(position).getOrderStatus());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return completedOrderDetails.size();
    }

    public class CompletedOrdersViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtAddress, txtOrderstatus, txtItemsCount, txtPaymentType, txtOrderdate, txtCost, txtOrderId;

        public CompletedOrdersViewHolder(@NonNull View itemView) {
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

package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.PendingOrdersModel;
import com.retail.biocare.R;

import java.util.ArrayList;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class PendingOrdersAdapter extends  RecyclerView.Adapter<PendingOrdersAdapter.PendingOrdersViewHolder>{

    private Context context;
    private ArrayList<PendingOrdersModel> pendingOrderDetails;

    public PendingOrdersAdapter(Context context, ArrayList pendingOrderDetails) {
        this.context = context;
        this.pendingOrderDetails = pendingOrderDetails;
    }

    @NonNull
    @Override
    public PendingOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PendingOrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_pending_orders, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrdersViewHolder holder, int position) {

        holder.txtName.setText(pendingOrderDetails.get(position).getUserName() + "("+ pendingOrderDetails.get(position).getUserId() +")");
        holder.txtAddress.setText(pendingOrderDetails.get(position).getUserAddress());
        holder.txtPaymentType.setText(pendingOrderDetails.get(position).getOrderDate());
        holder.txtOrderstatus.setText(pendingOrderDetails.get(position).getOrderStatus());
        holder.txtItemsCount.setText(pendingOrderDetails.get(position).getItemCount() + " Items");
        //holder.txtCost.setText(userBasicData.get("Currency")+pendingOrderDetails.get(position).getOrderAmount());
        holder.txtCost.setText(pendingOrderDetails.get(position).getOrderAmount());
        holder.txtOrderId.setText(pendingOrderDetails.get(position).getOrderId());
    }

    @Override
    public int getItemCount() {
        return pendingOrderDetails.size();
    }

    public class PendingOrdersViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtAddress, txtOrderstatus, txtItemsCount, txtPaymentType, txtOrderdate, txtCost, txtOrderId;

        public PendingOrdersViewHolder(@NonNull View itemView) {
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

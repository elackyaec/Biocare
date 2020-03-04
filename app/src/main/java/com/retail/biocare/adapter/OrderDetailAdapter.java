package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.OrderDetailModel;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {

    private Context context;
    private ArrayList<OrderDetailModel> orderDetails;

    public OrderDetailAdapter(Context context, ArrayList<OrderDetailModel> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_order_details_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {

        holder.txtItemnameMain.setText(orderDetails.get(position).getItenNane());
        holder.txtItempriceMain.setText(StaticDatas.userBasicData.get("Currency")+orderDetails.get(position).getItemPrice());
        holder.txtQty.setText(orderDetails.get(position).getItemQty());

    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemnameMain, txtQty, txtItempriceMain, txtOtion;

        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItemnameMain = itemView.findViewById(R.id.txtItemnameMain);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtItempriceMain = itemView.findViewById(R.id.txtItempriceMain);
            txtOtion = itemView.findViewById(R.id.txtOtion);

            txtOtion.setVisibility(View.GONE);

        }
    }

}

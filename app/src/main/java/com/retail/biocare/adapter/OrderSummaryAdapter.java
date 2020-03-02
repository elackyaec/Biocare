package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;

import java.util.ArrayList;

public class OrderSummaryAdapter extends  RecyclerView.Adapter<OrderSummaryAdapter.OrderSummaryViewHolder>{

    private Context context;

    public OrderSummaryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderSummaryViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_order_summary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderSummaryViewHolder holder, int position) {

        holder.txtItem.setText(StaticDatas.orderSummaryDetails.get(position).getItemName()+ " x " +StaticDatas.orderSummaryDetails.get(position).getItemQty());
        holder.txtPrice.setText(StaticDatas.userBasicData.get("Currency")+String.format("%.2f",StaticDatas.orderSummaryDetails.get(position).getItemTotal()));
    }

    @Override
    public int getItemCount() {
        return StaticDatas.orderSummaryDetails.size();
    }


    public class OrderSummaryViewHolder extends RecyclerView.ViewHolder {

        TextView txtItem, txtPrice, txtOtion;


        public OrderSummaryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItem = itemView.findViewById(R.id.txtItem);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtOtion = itemView.findViewById(R.id.txtOtion);

            txtOtion.setVisibility(View.GONE);
        }
    }

}

package com.retail.biocare.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.model.PaymentHistoryModel;
import com.retail.biocare.model.PendingWithdrawModel;

import java.util.List;


public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.DataObjectHolder> {

    Context context;
    List<PaymentHistoryModel> mData;


    public PaymentHistoryAdapter(Context context, List<PaymentHistoryModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtUsername,txtFullname,txtDate,txtMessage,txtAmt;

        public DataObjectHolder(View itemView) {
            super(itemView);

txtUsername=(TextView)itemView.findViewById(R.id.txt_useerame);
            txtFullname=(TextView)itemView.findViewById(R.id.txt_compeltename);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtMessage=(TextView)itemView.findViewById(R.id.txt_sttaus);
            txtAmt=(TextView)itemView.findViewById(R.id.txt_amt);

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paymenthistory, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.txtUsername.setText(mData.get(position).getUsername());
        holder.txtFullname.setText(mData.get(position).getFullname());
        holder.txtAmt.setText(mData.get(position).getAmount());
        holder.txtDate.setText(mData.get(position).getDate());
        holder.txtMessage.setText(mData.get(position).getMessage());

    }







    @Override
    public int getItemCount() {
        return mData.size();
    }

}


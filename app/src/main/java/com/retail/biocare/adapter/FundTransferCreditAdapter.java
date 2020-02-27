package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.model.FundTransferHistoryModel;

import java.util.List;


public class FundTransferCreditAdapter extends RecyclerView.Adapter<FundTransferCreditAdapter.DataObjectHolder> {

    Context context;
    List<FundTransferHistoryModel> mData;
    String name;

    public FundTransferCreditAdapter(Context context, List<FundTransferHistoryModel> mData, String name) {
        this.context = context;
        this.mData = mData;
        this.name = name;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtUsername,txtFullname,txtDate,txtMessage,txtCredit,txtUserid;

        public DataObjectHolder(View itemView) {
            super(itemView);

txtUsername=(TextView)itemView.findViewById(R.id.txt_useerame);
            txtFullname=(TextView)itemView.findViewById(R.id.txt_compeltename);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtMessage=(TextView)itemView.findViewById(R.id.txt_sttaus);
            txtCredit=(TextView)itemView.findViewById(R.id.txtprice);
        txtUserid=(TextView)itemView.findViewById(R.id.txtFundId);

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fundcredit, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.txtUsername.setText(mData.get(position).getUsername());
        holder.txtFullname.setText(mData.get(position).getFullname());
        holder.txtCredit.setText(mData.get(position).getCredit());
        holder.txtDate.setText(mData.get(position).getDate());
        holder.txtMessage.setText(mData.get(position).getMessage());
        holder.txtUserid.setText(name);



    }







    @Override
    public int getItemCount() {
        return mData.size();
    }

}


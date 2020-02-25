package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.model.FundTransferHistoryModel;
import com.retail.biocare.model.PaymentHistoryModel;

import java.util.List;


public class FundTransferHistoryAdapter extends RecyclerView.Adapter<FundTransferHistoryAdapter.DataObjectHolder> {

    Context context;
    List<FundTransferHistoryModel> mData;


    public FundTransferHistoryAdapter(Context context, List<FundTransferHistoryModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtUsername,txtFullname,txtDate,txtMessage,txtCredit,txtDebit;

        public DataObjectHolder(View itemView) {
            super(itemView);

txtUsername=(TextView)itemView.findViewById(R.id.txt_useerame);
            txtFullname=(TextView)itemView.findViewById(R.id.txt_compeltename);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtMessage=(TextView)itemView.findViewById(R.id.txt_sttaus);
            txtCredit=(TextView)itemView.findViewById(R.id.txt_credit);
            txtDebit=(TextView)itemView.findViewById(R.id.txt_devit);

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fundtransferhistory, parent, false);

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
        holder.txtDebit.setText(mData.get(position).getDebit());



    }







    @Override
    public int getItemCount() {
        return mData.size();
    }

}


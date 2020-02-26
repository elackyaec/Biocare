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
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.model.IncomeModel;
import com.retail.biocare.model.PayoutHistoryModel;

import java.util.List;


public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.DataObjectHolder> {

    Context context;
    List<IncomeModel> mData;


    public IncomeAdapter(Context context, List<IncomeModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtName,txtUsername,txtAmt,txtTDS,txtTax,txtNetAmt;

        public DataObjectHolder(View itemView) {
            super(itemView);

txtName=(TextView)itemView.findViewById(R.id.txtName);
            txtUsername=(TextView)itemView.findViewById(R.id.txt_username);
            txtAmt=(TextView)itemView.findViewById(R.id.txtAmount);
            txtTDS=(TextView)itemView.findViewById(R.id.txtTDS);
            txtTax=(TextView)itemView.findViewById(R.id.txtTax);
            txtNetAmt=(TextView)itemView.findViewById(R.id.txt_netamt);


        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_income_report, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {


        holder.txtName.setText(mData.get(position).getName());
        holder.txtUsername.setText(mData.get(position).getUsername());
        holder.txtAmt.setText("Amount : $"+mData.get(position).getAmount());
        holder.txtTDS.setText("TDS : $"+mData.get(position).getTds());
        holder.txtTax.setText("Tax : $"+mData.get(position).getTax());
        holder.txtNetAmt.setText("$"+mData.get(position).getNetamt());

    }







    @Override
    public int getItemCount() {
        return mData.size();
    }

}


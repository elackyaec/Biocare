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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.model.TransferEpinModel;
import com.retail.biocare.model.TransferReportEpinModel;

import java.util.List;


public class TransferEpinsReportsAdapter extends RecyclerView.Adapter<TransferEpinsReportsAdapter.DataObjectHolder> {

    Context context;
    List<TransferReportEpinModel> mData;


    public TransferEpinsReportsAdapter(Context context, List<TransferReportEpinModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtPinID,txtDate,txtStatus,txtPkg,txtPin,txtAmt,txtFrom,txtTo;


        public DataObjectHolder(View itemView) {
            super(itemView);

            txtPinID=(TextView)itemView.findViewById(R.id.txt_usedpin);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtPkg=(TextView)itemView.findViewById(R.id.txt_pkg);
            txtPin=(TextView)itemView.findViewById(R.id.txt_pin);
            txtAmt=(TextView)itemView.findViewById(R.id.txt_amt);
            txtFrom=(TextView)itemView.findViewById(R.id.txtFrom);
            txtTo=(TextView)itemView.findViewById(R.id.txtTo);


        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transferepin_report, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.txtPinID.setText("Pin ID : "+mData.get(position).getPinid());
        holder.txtDate.setText(mData.get(position).getDate());
        holder.txtPkg.setText(mData.get(position).getPkgname());
        holder.txtPin.setText(mData.get(position).getPinno());
        holder.txtAmt.setText("₹"+mData.get(position).getAmt());
        holder.txtFrom.setText("From : "+mData.get(position).getFrom());
        holder.txtTo.setText("To : "+mData.get(position).getTo());



    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

}


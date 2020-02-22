package com.retail.biocare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.model.DashboardModel;
import com.retail.biocare.model.UsedEpinModel;

import java.util.List;


public class UsedEpinsAdapter extends RecyclerView.Adapter<UsedEpinsAdapter.DataObjectHolder> {

    Context context;
    List<UsedEpinModel> mData;


    public UsedEpinsAdapter(Context context, List<UsedEpinModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtName,txtID,txtDate,txtStatus,txtPkg,txtPin,txtAmt;
LinearLayout bglayout;

        public DataObjectHolder(View itemView) {
            super(itemView);

txtName=(TextView)itemView.findViewById(R.id.txt_usedname);
            txtID=(TextView)itemView.findViewById(R.id.txt_usedpin);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtPkg=(TextView)itemView.findViewById(R.id.txt_pkg);
            txtStatus=(TextView)itemView.findViewById(R.id.txt_sttaus);
            txtPin=(TextView)itemView.findViewById(R.id.txt_pin);
            bglayout=(LinearLayout)itemView.findViewById(R.id.statuslayout);
            txtAmt=(TextView)itemView.findViewById(R.id.txt_amt);

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_used_epin, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
holder.txtName.setText(mData.get(position).getUsedname());
        holder.txtID.setText("Used ID : "+mData.get(position).getId());
        holder.txtDate.setText(mData.get(position).getDate());
        holder.txtPkg.setText(mData.get(position).getPkgname());
        holder.txtPin.setText(mData.get(position).getPinno());
        holder.txtAmt.setText("₹"+mData.get(position).getAmount());

        if(mData.get(position).getStatus().equals("1"))
        {
            holder.bglayout.setBackgroundResource(R.drawable.btn_bg_darkgreen_curve);
            holder.txtStatus.setText("Paid");
        }
        else
        {
            holder.bglayout.setBackgroundResource(R.drawable.btn_bg_red_curve);
            holder.txtStatus.setText("Unpaid");
        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

}


package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.model.IncomeModel;
import com.retail.biocare.model.RewardReportModel;

import java.util.List;


public class RewardReportAdapter extends RecyclerView.Adapter<RewardReportAdapter.DataObjectHolder> {

    Context context;
    List<RewardReportModel> mData;
    String status;


    public RewardReportAdapter(Context context, List<RewardReportModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtName,txtUsername,txtAmt,txtStatus,txtrewardname,txtLvel,txtUserid;

        public DataObjectHolder(View itemView) {
            super(itemView);

txtName=(TextView)itemView.findViewById(R.id.txtName);
            txtUserid=(TextView)itemView.findViewById(R.id.txtFundId);
            txtAmt=(TextView)itemView.findViewById(R.id.txtprice);
            txtStatus=(TextView)itemView.findViewById(R.id.txtPaymentType1);
            txtrewardname=(TextView)itemView.findViewById(R.id.txtrewardname);
            txtLvel=(TextView)itemView.findViewById(R.id.txtmembers);


        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rewardreport, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

holder.txtUserid.setText(mData.get(position).getUserid());
        holder.txtName.setText(mData.get(position).getUsername());
        holder.txtAmt.setText("$"+mData.get(position).getAmount());
        holder.txtrewardname.setText(mData.get(position).getRewardname());
        holder.txtLvel.setText(mData.get(position).getJoin()+" Members");

    }







    @Override
    public int getItemCount() {
        return mData.size();
    }

}


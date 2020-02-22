package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.LoginTimingsModels;
import com.retail.biocare.R;

import java.util.ArrayList;

public class LoginTimingsAdapter extends RecyclerView.Adapter<LoginTimingsAdapter.LoginTimingViewHolder> {

    private Context context;
    private ArrayList<LoginTimingsModels> loginTimingData;

    public LoginTimingsAdapter(Context context, ArrayList<LoginTimingsModels> loginTimingData) {
        this.context = context;
        this.loginTimingData = loginTimingData;
    }

    @NonNull
    @Override
    public LoginTimingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LoginTimingViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_timings, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoginTimingViewHolder holder, int position) {

        holder.txtIP.setText(loginTimingData.get(position).getIpAddress());
        holder.txtUserCode.setText(loginTimingData.get(position).getUserCode());
        holder.txtDate.setText(loginTimingData.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LoginTimingViewHolder extends RecyclerView.ViewHolder {

        private TextView txtIP, txtDate, txtUserCode;

        public LoginTimingViewHolder(@NonNull View itemView) {
            super(itemView);


            txtIP = itemView.findViewById(R.id.txtIP);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtUserCode = itemView.findViewById(R.id.txtUserCode);


        }
    }

}

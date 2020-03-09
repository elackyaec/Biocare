package com.retail.biocare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.JoiningInvoiceModel;
import com.retail.biocare.R;

import java.util.ArrayList;

public class JoiningInvoiceAdapter extends RecyclerView.Adapter<JoiningInvoiceAdapter.JoiningInvoiceViewHolder> {

    private Context context;
    private ArrayList<JoiningInvoiceModel> joiningInvoiceDetails;

    public JoiningInvoiceAdapter(Context context, ArrayList<JoiningInvoiceModel> joiningInvoiceDetails) {
        this.context = context;
        this.joiningInvoiceDetails = joiningInvoiceDetails;
    }

    @NonNull
    @Override
    public JoiningInvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JoiningInvoiceViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_joining_invoice, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JoiningInvoiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class JoiningInvoiceViewHolder extends RecyclerView.ViewHolder{

        private TextView txtJoiningIncoiceDescription, txtJoiningIncoiceQty, txtJoiningIncoiceAmount;

        public JoiningInvoiceViewHolder(@NonNull View itemView) {
            super(itemView);

            txtJoiningIncoiceDescription = itemView.findViewById(R.id.txtJoiningIncoiceDescription);
            txtJoiningIncoiceQty = itemView.findViewById(R.id.txtJoiningIncoiceQty);
            txtJoiningIncoiceAmount = itemView.findViewById(R.id.txtJoiningIncoiceAmount);

        }
    }

}

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
import com.retail.biocare.model.FundTransferHistoryModel;
import com.retail.biocare.model.PayoutHistoryModel;

import java.util.List;


public class PayoutHistoryAdapter extends RecyclerView.Adapter<PayoutHistoryAdapter.DataObjectHolder> {

    Context context;
    List<PayoutHistoryModel> mData;
    Dialog detailsdialog;


    public PayoutHistoryAdapter(Context context, List<PayoutHistoryModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtUsername,txtFullname,txtDate,txtMessage,txtAmount;
        Button btnView;

        public DataObjectHolder(View itemView) {
            super(itemView);

txtUsername=(TextView)itemView.findViewById(R.id.txt_useerame);
            txtFullname=(TextView)itemView.findViewById(R.id.txt_compeltename);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtMessage=(TextView)itemView.findViewById(R.id.txt_sttaus);
            txtAmount=(TextView)itemView.findViewById(R.id.txt_amt);
            btnView=(Button) itemView.findViewById(R.id.btn_view) ;

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payouthistory, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.txtUsername.setText(mData.get(position).getUsername());
        holder.txtFullname.setText(mData.get(position).getFullname());
        holder.txtDate.setText(mData.get(position).getDate());
      holder.txtMessage.setText(mData.get(position).getMessage());
        holder.txtAmount.setText(mData.get(position).getAmount());

holder.btnView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
opendetailsdialog(mData.get(position).getUsername(),mData.get(position).getFullname(),mData.get(position).getDate(),
        mData.get(position).getAmount(),mData.get(position).getAccname(),mData.get(position).getAccn0(),
        mData.get(position).getBankname(),mData.get(position).getBranch(),mData.get(position).getBankcode());
    }
});

    }


    private void opendetailsdialog(String username, String fullname, String w_date, String w_amt, String accname, String accn0, String bankname, String branch, String bankcode) {

        detailsdialog = new Dialog(context);
        detailsdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        detailsdialog.setContentView(R.layout.dialog_payoutreport_details);
        detailsdialog.setCancelable(true);
        detailsdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        detailsdialog.show();

        TextView txtUsername=(TextView)detailsdialog.findViewById(R.id.txt_username);
        TextView txtFullname=(TextView)detailsdialog.findViewById(R.id.txt_fullname);
        TextView txtwithdrawamt=(TextView)detailsdialog.findViewById(R.id.txt_withdrawamt);
        TextView txtWithdrawdate=(TextView)detailsdialog.findViewById(R.id.txt_withdrawdate);
        TextView txtaccno=(TextView)detailsdialog.findViewById(R.id.txt_accname);
        TextView txtaccname=(TextView)detailsdialog.findViewById(R.id.txt_accno);
        TextView txtbankname=(TextView)detailsdialog.findViewById(R.id.txt_bankname);
        TextView txtbranch=(TextView)detailsdialog.findViewById(R.id.txt_branch);
        TextView txtbankcode=(TextView)detailsdialog.findViewById(R.id.txt_bankcode);
        ImageView imgClose=(ImageView)detailsdialog.findViewById(R.id.img_cancel);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsdialog.dismiss();
            }
        });

        txtUsername.setText(username);
        txtFullname.setText(fullname);
        txtwithdrawamt.setText(w_amt);
        txtWithdrawdate.setText(w_date);
        txtaccno.setText(accn0);
        txtbankcode.setText(bankcode);
        txtbankname.setText(bankname);
        txtbranch.setText(branch);
        txtaccname.setText(accname);
    }





    @Override
    public int getItemCount() {
        return mData.size();
    }

}


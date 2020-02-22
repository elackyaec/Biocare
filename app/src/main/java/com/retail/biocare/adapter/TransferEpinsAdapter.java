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
import com.retail.biocare.activity.TransferEpinActivity;
import com.retail.biocare.model.TransferEpinModel;
import com.retail.biocare.model.UsedEpinModel;

import java.util.List;


public class TransferEpinsAdapter extends RecyclerView.Adapter<TransferEpinsAdapter.DataObjectHolder> {

    Context context;
    List<TransferEpinModel> mData;
    Dialog transferDialog;
    String sttaus;


    public TransferEpinsAdapter(Context context, List<TransferEpinModel> mData,String sttaus) {
        this.context = context;
        this.mData = mData;
        this.sttaus=sttaus;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

TextView txtPinID,txtDate,txtStatus,txtPkg,txtPin,txtAmt,txtUswerID;
LinearLayout bglayout;
Button txtTransferEpin;
CheckBox checkBox;

        public DataObjectHolder(View itemView) {
            super(itemView);

            txtTransferEpin=(Button)itemView.findViewById(R.id.txt_transferEpin);
            txtPinID=(TextView)itemView.findViewById(R.id.txt_usedpin);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtPkg=(TextView)itemView.findViewById(R.id.txt_pkg);
            txtStatus=(TextView)itemView.findViewById(R.id.txt_sttaus);
            txtPin=(TextView)itemView.findViewById(R.id.txt_pin);
            txtAmt=(TextView)itemView.findViewById(R.id.txt_amt);
            bglayout=(LinearLayout)itemView.findViewById(R.id.statuslayout);
            checkBox=(CheckBox)itemView.findViewById(R.id.chkbox);

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transfer_epin, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.txtPinID.setText(mData.get(position).getPinid());
        holder.txtDate.setText(mData.get(position).getDate());
        holder.txtPkg.setText(mData.get(position).getPkgname());
        holder.txtStatus.setText(mData.get(position).getStatus());
        holder.txtPin.setText(mData.get(position).getPinno());
        holder.txtAmt.setText(mData.get(position).getAmt());

        if(sttaus.equals("1"))
        {
            holder.checkBox.setChecked(true);
        }
        else
        {
            holder.checkBox.setChecked(false);
        }

        holder.txtTransferEpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentransferdialog();
            }
        });

    }

    private void opentransferdialog() {

        transferDialog = new Dialog(context);
        transferDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        transferDialog.setContentView(R.layout.dialog_transfreepin);
        transferDialog.setCancelable(true);
        transferDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        transferDialog.show();
      EditText  edtUserid=(EditText)transferDialog.findViewById(R.id.edt_userid);
        EditText edtUsername=(EditText)transferDialog.findViewById(R.id.edt_username);
       Button btnDialogTransfer=(Button) transferDialog.findViewById(R.id.btntransfre);
        Button  btnCancel=(Button)transferDialog.findViewById(R.id.btncancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferDialog.dismiss();
            }
        });
        btnDialogTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Pin Transferred Successfully",Toast.LENGTH_SHORT).show();
                transferDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}


package com.retail.biocare.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.activity.AddAddressActivity;


public class AddresslistAdapter extends RecyclerView.Adapter<AddresslistAdapter.DataObjectHolder> {
    private static final String TAG = "MessageAdapter";

    Context context;
    AlertDialog.Builder builder;

    public AddresslistAdapter(Context context) {
        this.context = context;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView txtDelete, txtEdit;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtEdit = (TextView) itemView.findViewById(R.id.txt_edit);
            txtDelete = (TextView) itemView.findViewById(R.id.txt_delete);


        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addresslist, parent, false);
        builder = new AlertDialog.Builder(context);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {


        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to delete this address?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // ((Activity)context).finish();

                                Toast.makeText(context, "Address deleted successfully", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Delete Address");
                alert.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return 10;
    }


}



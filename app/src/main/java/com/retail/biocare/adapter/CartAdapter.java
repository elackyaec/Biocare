package com.retail.biocare.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Interfaces.CartQuantityChanged;
import com.retail.biocare.Models.CartItemsModels;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = "CartAdapter";

    private Context context;
    CartQuantityChanged cartQuantityChanged;

    public CartAdapter(Context context, CartQuantityChanged cartQuantityChanged) {
        this.context = context;
        this.cartQuantityChanged = cartQuantityChanged;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_cart_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(context, R.array.quantity, R.layout.item_spinner_dropdown_siva);
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        holder.spinnerQty.setAdapter(adapter);

        holder.spinnerQty.setSelection(Integer.parseInt(StaticDatas.cartDetails.get(position).getItemQty())-1);

        holder.spinnerQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int sposition, long id) {

                StaticDatas.cartDetails.get(position).setItemQty(String.valueOf(sposition+1));
                StaticDatas.cartDetailsNew.get(position).setQuantity(String.valueOf(sposition+1));

                Log.d(TAG, "onItemSelected: setTotalamount: "+(StaticDatas.cartDetailsNew.get (position).getPrice()*(sposition+1)));
                StaticDatas.cartDetailsNew.get(position).setTotalamount(StaticDatas.cartDetailsNew.get (position).getPrice()*sposition+1);


                cartQuantityChanged.OncartQuantityChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.txtItemname.setText(StaticDatas.cartDetails.get(position).getItemName() +" : "+ StaticDatas.cartDetails.get(position).getItemtxtPrice());


        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = DeleteConfirmationAlert(position);
                diaBox.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return StaticDatas.cartDetails.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemname, txtOtion;
        private Spinner spinnerQty;
        private ImageView imgRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItemname = itemView.findViewById(R.id.txtItemname);
            txtOtion = itemView.findViewById(R.id.txtOtion);
            spinnerQty = itemView.findViewById(R.id.spinnerQty);
            imgRemove = itemView.findViewById(R.id.imgRemove);

            txtOtion.setVisibility(View.GONE);

        }
    }

    private AlertDialog DeleteConfirmationAlert(final int rposition) {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(context)
                //set message, title, and icon
                .setTitle("Confirm")
                .setMessage("Do you really want to remove this item?")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code

                        try {

                            Log.d(TAG, "onClick: StaticDatas.cartDetails Size Before: "+StaticDatas.cartDetails.size());

                            //cartDetails.remove(rposition);
                            StaticDatas.cartDetails.remove(rposition);
                            StaticDatas.addedItemIds.remove(rposition);


                            Log.d(TAG, "onClick: StaticDatas.cartDetails After: "+StaticDatas.cartDetails.size());

                            notifyDataSetChanged();

                            Log.d(TAG, "onClick: Removal Success: position: "+rposition);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, "OnRemoveException: "+e);

                        }
                    }

                })



                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }


}

package com.retail.biocare.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.retail.biocare.Models.CategoryItemModels;
import com.retail.biocare.R;
import com.retail.biocare.activity.ItemDescriptionActivity;

import java.util.ArrayList;

import static com.retail.biocare.StaticData.StaticDatas.hostURL;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.CategoryItemViewHolder> {

    private static final String TAG = "CategoryItemRecyclerAda";



    private Context context;
    private ArrayList<CategoryItemModels> itemDetails;

    public ProductsRecyclerAdapter(Context context, ArrayList<CategoryItemModels> itemDetails) {
        this.context = context;
        this.itemDetails = itemDetails;
    }



    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_products_new, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, final int position) {

        try {
            //Glide.with(context).load(itemDetails.get(position).getItemImage()).into(holder.imgItemImage);
            Glide.with(context).load(hostURL + itemDetails.get(position).getItemImage().substring(3)).signature(new ObjectKey(userProfileData.get("DateModified"))).placeholder(R.drawable.noimage).into(holder.imgItemImage);

        }
        catch (Exception e){
            Log.d(TAG, "onBindViewHolder: Glide Exception : "+e);
        }

        holder.txtItemName.setText(itemDetails.get(position).getItemName());
        holder.txtItemPrice.setText(itemDetails.get(position).getItemPrice());
        holder.txtSize.setText(itemDetails.get(position).getItemSize());
        //holder.txtItemOff.setText(itemDetails.get(position).getItemOffer());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ItemDescriptionActivity.class);

                intent.putExtra("itemName", itemDetails.get(position).getItemName());
                intent.putExtra("itemImage", itemDetails.get(position).getItemImage());
                intent.putExtra("itemPrice",  itemDetails.get(position).getItemPrice());
                intent.putExtra("itemColor",  itemDetails.get(position).getItemColor());
                intent.putExtra("itemSize",  itemDetails.get(position).getItemSize());
                intent.putExtra("itemDescription",  itemDetails.get(position).getItemDescription());
                intent.putExtra("floatPrice", itemDetails.get(position).getMrpprice1());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemDetails.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItemImage;
        TextView txtItemName, txtItemPrice, txtItemOff, txtSize;
        RatingBar ratingBar;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItemImage = itemView.findViewById(R.id.imgItem);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            txtSize = itemView.findViewById(R.id.txtSize);
            /*txtItemOff = itemView.findViewById(R.id.txtItemOff);
            ratingBar = itemView.findViewById(R.id.ratingBar);*/

        }
    }

}

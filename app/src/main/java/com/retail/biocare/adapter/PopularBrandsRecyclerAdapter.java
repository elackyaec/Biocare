package com.retail.biocare.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.retail.biocare.R;
import com.retail.biocare.model.RestaurantListModel;

import java.util.ArrayList;
import java.util.List;

public class PopularBrandsRecyclerAdapter extends RecyclerView.Adapter<PopularBrandsRecyclerAdapter.TopPicksViewHolder> {

    private Context context;
    private List<RestaurantListModel> popularPickRestaturants = new ArrayList<>();
    private ArrayList<String> popularrIDs = new ArrayList<>();


    private static final String TAG = "PopularBrandsRecyclerAd";


    public PopularBrandsRecyclerAdapter(Context context) {
        this.context = context;
    }

    public PopularBrandsRecyclerAdapter(Context context, List<RestaurantListModel> popularPickRestaturants, ArrayList<String> popularrIDs) {
        this.context = context;
        this.popularPickRestaturants = popularPickRestaturants;
        this.popularrIDs = popularrIDs;
    }

    @NonNull
    @Override
    public TopPicksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycelr_popular_brands,parent,false);
        return new TopPicksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopPicksViewHolder holder, final int position) {



    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class TopPicksViewHolder extends RecyclerView.ViewHolder{

        TextView txtRestName, txtDeliveryTime;
        ImageView imgRestImages;

        public TopPicksViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRestName = itemView.findViewById(R.id.txtRestName);
            txtDeliveryTime = itemView.findViewById(R.id.txtDeliveryTime);
            imgRestImages = itemView.findViewById(R.id.imgRestImages);


        }
    }

}

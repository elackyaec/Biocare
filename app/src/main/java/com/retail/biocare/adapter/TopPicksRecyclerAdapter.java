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


public class TopPicksRecyclerAdapter extends RecyclerView.Adapter<TopPicksRecyclerAdapter.TopPicksViewHolder> {

    private static final String TAG = "TopPicksRecyclerAdapter";

    private Context context;
    private List<RestaurantListModel> topPickRestaturants = new ArrayList<>();
    private ArrayList<String> toprIDs = new ArrayList<>();


    public TopPicksRecyclerAdapter(Context context) {
        this.context = context;
    }

    public TopPicksRecyclerAdapter(Context context, List<RestaurantListModel> topPickRestaturants, ArrayList<String> toprIDs) {
        this.context = context;
        this.topPickRestaturants = topPickRestaturants;
        this.toprIDs = toprIDs;
    }

    @NonNull
    @Override
    public TopPicksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycelr_top_picks,parent,false);
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

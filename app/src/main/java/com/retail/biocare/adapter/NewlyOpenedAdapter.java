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

public class NewlyOpenedAdapter extends RecyclerView.Adapter<NewlyOpenedAdapter.NewlyOpenedViewHolder> {

    private Context context;
    private List<RestaurantListModel> nearbyRestaurantsDetails;
    private ArrayList<String> newlyrIDs = new ArrayList<>();

    private static final String TAG = "NewlyOpenedAdapter";

    public NewlyOpenedAdapter(Context context) {
        this.context = context;
    }

    public NewlyOpenedAdapter(Context context, List<RestaurantListModel> nearbyRestaurantsDetails, ArrayList<String> newlyrIDs) {
        this.context = context;
        this.nearbyRestaurantsDetails = nearbyRestaurantsDetails;
        this.newlyrIDs = newlyrIDs;
    }

    @NonNull
    @Override
    public NewlyOpenedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_newly_opened, parent, false);
        return new NewlyOpenedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewlyOpenedViewHolder holder, final int position) {



    }

    @Override
    public int getItemCount() {

            return 2;
    }

    public class NewlyOpenedViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtDeliverytime,txtmobile,txtTotalReview,txtAvgreview,txtAddress;
        ImageView imageRest;

        public NewlyOpenedViewHolder(@NonNull View itemView) {
            super(itemView);


            txtName = (TextView)itemView.findViewById(R.id.txtName);
            // txtCategory = (TextView)itemView.findViewById(R.id.txt_category);
            txtmobile = (TextView)itemView.findViewById(R.id.txt_mobile);

            txtDeliverytime = (TextView)itemView.findViewById(R.id.txt_deliverytime);
            txtTotalReview = (TextView)itemView.findViewById(R.id.txt_toatlreview);
            txtAvgreview = (TextView)itemView.findViewById(R.id.txt_avgreview);
            txtAddress = (TextView)itemView.findViewById(R.id.txt_address);
            imageRest = itemView.findViewById(R.id.imageRest);

            
        }
    }

}

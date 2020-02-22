package com.retail.biocare.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.activity.TransferEpinActivity;
import com.retail.biocare.activity.TransferEpinReportActivity;
import com.retail.biocare.activity.UnusedPinActivity;
import com.retail.biocare.activity.UsedEpinActivity;
import com.retail.biocare.model.DashboardModel;

import java.util.List;


public class EpinsAdapter extends RecyclerView.Adapter<EpinsAdapter.DataObjectHolder> {

    Context context;
    List<DashboardModel> mData;


    public EpinsAdapter(Context context, List<DashboardModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtName, txtNo;
        View view1, view2;

        public DataObjectHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtNo = (TextView) itemView.findViewById(R.id.txt_total);
            view1 = (View) itemView.findViewById(R.id.view_1);
            view2 = (View) itemView.findViewById(R.id.view2);


        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_epins, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {


holder.txtNo.setText(mData.get(position).getNo());
holder.txtName.setText(mData.get(position).getName());

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(position==0)
        {

        }
        if(position==1)
        {
            Intent intent=new Intent(context, UnusedPinActivity.class);
            context.startActivity(intent);
        }
        if(position==2)
        {
           /* Intent intent=new Intent(context, UnusedPinActivity.class);
            context.startActivity(intent);*/
        }
        if(position==3)
        {
            Intent intent=new Intent(context, TransferEpinActivity.class);
            context.startActivity(intent);
        }

        if(position==4)
        {
            Intent intent=new Intent(context, TransferEpinReportActivity.class);
            context.startActivity(intent);
        }
    }
});

if(position==0)
{
    holder.imageView.setImageResource(mData.get(position).getImage());

    holder.view1.setBackgroundResource(R.color.appblue);
    holder.imageView.setColorFilter(Color.argb(255, 23, 136, 238));
    holder.view2.setBackgroundResource(R.color.appblue);

   // holder.txtNo.setTextColor(Color.parseColor("#1788EE"));

}
        if(position==1)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.appgreen);
           holder.imageView.setColorFilter(Color.argb(255, 88, 173, 105));
            holder.view2.setBackgroundResource(R.color.appgreen);

            // holder.txtNo.setTextColor(Color.parseColor("#58AD69"));

        }
        if(position==2)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.colorPrimary);
            holder.imageView.setColorFilter(Color.argb(255, 250, 84, 98));
            // holder.txtNo.setTextColor(Color.parseColor("#FA5462"));
            holder.view2.setBackgroundResource(R.color.colorPrimary);


        }
        if(position==3)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.darkblue);
            holder.imageView.setColorFilter(Color.argb(255, 86, 109, 241));
            // holder.txtNo.setTextColor(Color.parseColor("#566DF1"));
            holder.view2.setBackgroundResource(R.color.darkblue);


        }
        if(position==4)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.violet);
            holder.imageView.setColorFilter(Color.argb(255, 137, 81, 235));
            // holder.txtNo.setTextColor(Color.parseColor("#8951EB"));
            holder.view2.setBackgroundResource(R.color.violet);


        }
        if(position==5)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.orange);
            holder.imageView.setColorFilter(Color.argb(255, 247, 183, 85));
            // holder.txtNo.setTextColor(Color.parseColor("#F7B755"));
            holder.view2.setBackgroundResource(R.color.orange);


        }
        if(position==6)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.darkred);
            holder.imageView.setColorFilter(Color.argb(255, 204, 51, 0));
            // holder.txtNo.setTextColor(Color.parseColor("#cc3300"));
            holder.view2.setBackgroundResource(R.color.darkred);


        }
        if(position==7)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.lightgreen1);
            holder.imageView.setColorFilter(Color.argb(255, 153, 204, 0));
            holder.view2.setBackgroundResource(R.color.darkred);

            // holder.txtNo.setTextColor(Color.parseColor("#99cc00"));

        }


        if(position==8)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.lightpurple);
            holder.imageView.setColorFilter(Color.argb(255, 166, 77, 255));
            // holder.txtNo.setTextColor(Color.parseColor("#a64dff"));
            holder.view2.setBackgroundResource(R.color.lightpurple);


        }
        if(position==9)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.yellow);
            holder.imageView.setColorFilter(Color.argb(255, 233, 193, 30));

            // holder.txtNo.setTextColor(Color.parseColor("#E9C11E"));
            holder.view2.setBackgroundResource(R.color.yellow);

        }
        if(position==10)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.aqua);
            holder.imageView.setColorFilter(Color.argb(255, 23, 201, 201));

            // holder.txtNo.setTextColor(Color.parseColor("#17C9C9"));
            holder.view2.setBackgroundResource(R.color.aqua);

        }

        if(position==11)
        {
            holder.imageView.setImageResource(mData.get(position).getImage());

            holder.view1.setBackgroundResource(R.color.pink2);
            holder.imageView.setColorFilter(Color.argb(255, 204, 0, 204));

            // holder.txtNo.setTextColor(Color.parseColor("#cc00cc"));
            holder.view2.setBackgroundResource(R.color.pink2);

        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

}


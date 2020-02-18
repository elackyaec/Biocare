package com.retail.biocare.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.RequiresApi;

import com.retail.biocare.R;
import com.retail.biocare.model.ClassLeftDrawer;

import java.util.List;

public class LeftNavAdapter extends ArrayAdapter<ClassLeftDrawer> {

    Context context;
   List<ClassLeftDrawer> listitem;
   String user_id;
   SharedPreferences pref;
   SharedPreferences.Editor editor;

    public LeftNavAdapter(Context context, int resourceId, List<ClassLeftDrawer> items) {
        super(context, resourceId, items);
        this.context = context;
        this.listitem=items;
    }

    /*private view holder class*/
    public class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtNotifcation;

        RelativeLayout layoutcircle;
        View view1;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("WrongConstant")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ClassLeftDrawer rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_main_left_drawer_menu, null);
            holder = new ViewHolder();
             holder.txtTitle = (TextView) convertView.findViewById(R.id.txv_nave);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_nave);
            holder.view1=(View)convertView.findViewById(R.id.view1);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.txtTitle.setText(rowItem.getMenu_name());
        holder.imageView.setImageResource(rowItem.getMenu_img());



        return convertView;
    }
}
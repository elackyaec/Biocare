package com.retail.biocare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.retail.biocare.DeliveredOrdersActivity;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.activity.OrdersActivity;
import com.retail.biocare.activity.ProductActivity;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class FragmentPurchases extends Fragment {
    private View rootView;
    private TextView txtBalance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_purchases, container, false);

        txtBalance = rootView.findViewById(R.id.txtBalance);
        txtBalance.setText(userBasicData.get("Currency") + StaticDatas.AvailableBalance);


        rootView.findViewById(R.id.buyProducts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProductActivity.class));
            }
        });

        rootView.findViewById(R.id.layoutMyorders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OrdersActivity.class));
            }
        });

        rootView.findViewById(R.id.deliveredOrdersLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DeliveredOrdersActivity.class));
            }
        });

        return rootView;
    }
}

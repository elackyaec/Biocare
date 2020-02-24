package com.retail.biocare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.activity.BuyEpinActivity;
import com.retail.biocare.activity.TransferEpinActivity;
import com.retail.biocare.activity.TransferEpinReportActivity;
import com.retail.biocare.activity.UnusedPinActivity;
import com.retail.biocare.activity.UsedEpinActivity;
import com.retail.biocare.adapter.EpinsAdapter;
import com.retail.biocare.model.DashboardModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentEpins extends Fragment {

    private View rootView;

    private RecyclerView recyclerView;
    ArrayList<DashboardModel> dashboardModels=new ArrayList<>();

LinearLayout usedpinslayout,transferepinslayout,buyepinslayout,unusedpinslayout,transferpinsreportlayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_epins, container, false);


        recyclerView = rootView.findViewById(R.id.recyclerEpins);
        usedpinslayout=rootView.findViewById(R.id.usedepinslayout);
        transferepinslayout=rootView.findViewById(R.id.transferepinslayout);
        buyepinslayout=rootView.findViewById(R.id.buyepinslayout);
        unusedpinslayout=rootView.findViewById(R.id.unusedpinslayout);
        transferpinsreportlayout=rootView.findViewById(R.id.transferpinsreportlayout);


      /*  dashboardModels.add(new DashboardModel(R.drawable.totalmail," ","Used EPins"));
        dashboardModels.add(new DashboardModel(R.drawable.usedepin," ","UnUsed EPins"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin," ","Buy EPins Ewallet"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin," ","Transfer EPins"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin," ","Transfer EPins Report"));

        EpinsAdapter epinsAdapter = new EpinsAdapter(getContext(), dashboardModels);

        recyclerView.setAdapter(epinsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));*/

      usedpinslayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(getContext(), UsedEpinActivity.class);
              startActivity(intent);
          }
      });
        transferepinslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), TransferEpinActivity.class);
                startActivity(intent);
            }
        });
        buyepinslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), BuyEpinActivity.class);
                startActivity(intent);
            }
        });

        unusedpinslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), UnusedPinActivity.class);
                startActivity(intent);
            }
        });
        transferpinsreportlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), TransferEpinReportActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}

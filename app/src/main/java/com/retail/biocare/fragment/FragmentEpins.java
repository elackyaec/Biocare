package com.retail.biocare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.EpinsAdapter;
import com.retail.biocare.model.DashboardModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentEpins extends Fragment {

    private View rootView;

    private RecyclerView recyclerView;
    ArrayList<DashboardModel> dashboardModels=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_epins, container, false);


        recyclerView = rootView.findViewById(R.id.recyclerEpins);


        dashboardModels.add(new DashboardModel(R.drawable.totalmail," ","Used EPins"));
        dashboardModels.add(new DashboardModel(R.drawable.usedepin," ","UnUsed EPins"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin," ","Buy EPins Ewallet"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin," ","Transfer EPins"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin," ","Transfer EPins Report"));

        EpinsAdapter epinsAdapter = new EpinsAdapter(getContext(), dashboardModels);

        recyclerView.setAdapter(epinsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return rootView;
    }
}

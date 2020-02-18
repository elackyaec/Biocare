package com.retail.biocare.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retail.biocare.R;
import com.retail.biocare.adapter.DashboardAdapter;
import com.retail.biocare.model.DashboardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    List<DashboardModel> dashboardModels=new ArrayList<>();
    DashboardAdapter dashboardAdapter;

    public AdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment AdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFragment newInstance() {
        AdminFragment fragment = new AdminFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        dashboardModels.add(new DashboardModel(R.drawable.totadmin,"1","Total\n Admin"));
        dashboardModels.add(new DashboardModel(R.drawable.todaymembers,"1000","Today Members"));
        dashboardModels.add(new DashboardModel(R.drawable.totmembers,"1001","Total Members"));
        dashboardModels.add(new DashboardModel(R.drawable.withdraw,"5000","Total Withdraw"));
        dashboardModels.add(new DashboardModel(R.drawable.released,"100","Funds Released"));
        dashboardModels.add(new DashboardModel(R.drawable.requested,"1000","Funds Requested"));
        dashboardModels.add(new DashboardModel(R.drawable.sent,"150","Total Sent Mail"));
        dashboardModels.add(new DashboardModel(R.drawable.readmail,"1500","Total Read Mail"));
        dashboardModels.add(new DashboardModel(R.drawable.totalmail,"5600","Total\n Mail"));
        dashboardModels.add(new DashboardModel(R.drawable.usedepin,"13","Total Used Epin"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin,"10","Total Unused Epin"));
        dashboardModels.add(new DashboardModel(R.drawable.totadmin,"100","Total\nEpins"));

        dashboardAdapter=new DashboardAdapter(getContext(),dashboardModels);
        recyclerView.setAdapter(dashboardAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        return view;
    }


}

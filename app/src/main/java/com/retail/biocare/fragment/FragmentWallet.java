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
import com.retail.biocare.adapter.WalletAdapter;
import com.retail.biocare.model.DashboardModel;

import java.util.ArrayList;

public class FragmentWallet extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;

    ArrayList<DashboardModel> dashboardModels=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_wallet, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerWallet);

        dashboardModels.add(new DashboardModel(R.drawable.totalmail," ","Payout Report"));
        dashboardModels.add(new DashboardModel(R.drawable.withdraw," ","Withdraw Funds"));
        dashboardModels.add(new DashboardModel(R.drawable.withdraw2," ","Pending Withdraw Funds"));
        dashboardModels.add(new DashboardModel(R.drawable.moneytransfer," ","Fund Transfer "));
        dashboardModels.add(new DashboardModel(R.drawable.moneytransfer," ","Fund Transfer History"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin," ","Account Balance"));

        WalletAdapter walletAdapter = new WalletAdapter(getContext(), dashboardModels);

        recyclerView.setAdapter(walletAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return rootView;
    }
}

package com.retail.biocare.SubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.JoiningInvoiceModel;
import com.retail.biocare.R;
import com.retail.biocare.adapter.JoiningInvoiceAdapter;

import java.util.ArrayList;

public class FragmentInvoiceDetails extends Fragment {
    private View rootView;

    private ArrayList<JoiningInvoiceModel> joiningInvoiceDetails = new ArrayList<>();
    private JoiningInvoiceAdapter joiningInvoiceAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_invoice_details, container, false);

        initRecycler();
        return rootView;
    }

    private void initRecycler() {

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerJoiningReceipt);
        joiningInvoiceAdapter = new JoiningInvoiceAdapter(getContext(), joiningInvoiceDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(joiningInvoiceAdapter);

    }
}

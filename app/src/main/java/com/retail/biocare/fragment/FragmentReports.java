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

import com.retail.biocare.R;
import com.retail.biocare.activity.AllIncomeReportActivity;
import com.retail.biocare.activity.BinaryReportActivity;

public class FragmentReports extends Fragment {
    private View rootView;
    LinearLayout binaryreportslayout,directreportlayout,levelreportlayout,allincomelayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_reports, container, false);
binaryreportslayout=(LinearLayout)rootView.findViewById(R.id.binaryreportslayout);
        directreportlayout=(LinearLayout)rootView.findViewById(R.id.directreportlayout);
        levelreportlayout=(LinearLayout)rootView.findViewById(R.id.levelreportlayout);
        allincomelayout=(LinearLayout)rootView.findViewById(R.id.allincomelayout);

binaryreportslayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(), BinaryReportActivity.class);
        intent.putExtra("reportid","13");
        startActivity(intent);
    }
});

        levelreportlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), BinaryReportActivity.class);
                intent.putExtra("reportid","12");
                startActivity(intent);
            }
        });

        directreportlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), BinaryReportActivity.class);
                intent.putExtra("reportid","11");
                startActivity(intent);
            }
        });

        allincomelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AllIncomeReportActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}

package com.retail.biocare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.retail.biocare.R;
import com.retail.biocare.SubFragments.FragmentAppointmentLetter;
import com.retail.biocare.SubFragments.FragmentInvoiceDetails;

public class FragmentReceipts extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_receipts, container, false);

        ViewPager viewPager = rootView.findViewById(R.id.viewPagerReceipts);
        TabLayout tabLayout = rootView.findViewById(R.id.tabReceipts);

        viewPager.setAdapter(new FragmentReceiptsViewPager(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }



    private class FragmentReceiptsViewPager extends FragmentPagerAdapter{

        private String[] tabTitles = new String[]{"Appointment Letter", "Invoice Details"};

        public FragmentReceiptsViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0: return new FragmentAppointmentLetter();
                case 1: return new FragmentInvoiceDetails();

            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}

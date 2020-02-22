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
import com.retail.biocare.SubFragments.FragmentComposeMessage;
import com.retail.biocare.SubFragments.FragmentInboxMessage;
import com.retail.biocare.SubFragments.FragmentInvoiceDetails;
import com.retail.biocare.SubFragments.FragmentOutboxMessage;

public class FragmentMessages extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_mesages, container, false);

        ViewPager viewPager = rootView.findViewById(R.id.viewPagerMessages);
        TabLayout tabLayout = rootView.findViewById(R.id.tabMessages);

        viewPager.setAdapter(new FragmentReceiptsViewPager(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }



    private class FragmentReceiptsViewPager extends FragmentPagerAdapter{

        private String[] tabTitles = new String[]{"Compose", "Inbox","Outbox"};

        public FragmentReceiptsViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0: return new FragmentComposeMessage();
                case 1: return new FragmentInboxMessage();
                case 2: return new FragmentOutboxMessage();

            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}

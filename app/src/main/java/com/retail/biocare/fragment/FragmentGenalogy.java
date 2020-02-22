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
import com.retail.biocare.SubFragments.FragmentGenDirect;
import com.retail.biocare.SubFragments.FragmentGenDownline;
import com.retail.biocare.SubFragments.FragmentGenLevel;

public class FragmentGenalogy extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_genalogy, container, false);

        TabLayout tabLayout = rootView.findViewById(R.id.tabGenalogy);
        ViewPager viewPager = rootView.findViewById(R.id.viewPagerGenalogy);
        viewPager.setAdapter(new GenalogyViewPager(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }






    private class GenalogyViewPager extends FragmentPagerAdapter{

        private String[] tabTitles = new String[] {"Treeview", "Direct view  ", "Downline"};

        public GenalogyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0 : return new FragmentGenLevel();
                case 1: return new FragmentGenDirect();
                case 2: return new FragmentGenDownline();

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

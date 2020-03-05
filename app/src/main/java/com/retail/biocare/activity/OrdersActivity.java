package com.retail.biocare.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.retail.biocare.R;
import com.retail.biocare.SubFragments.FragmentCompletedOrders;
import com.retail.biocare.SubFragments.FragmentPendingOrders;

public class OrdersActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        TabLayout tabLayout = findViewById(R.id.tabOrders);
        ViewPager viewPager = findViewById(R.id.viewpagerOrders);
        OrdersViewOager ordersViewOager = new OrdersViewOager(getSupportFragmentManager());
        viewPager.setAdapter(ordersViewOager);
        tabLayout.setupWithViewPager(viewPager);

    }


    private class OrdersViewOager extends FragmentPagerAdapter {

        private String[] tabTitles = {"Pending", "Delivered"};

        public OrdersViewOager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new FragmentPendingOrders();
            else if (position == 1)
                return new FragmentCompletedOrders();

            else
                return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}

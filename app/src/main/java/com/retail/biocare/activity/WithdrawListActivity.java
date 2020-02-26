package com.retail.biocare.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.retail.biocare.R;
import com.retail.biocare.SubFragments.PaymentHistoryFragment;
import com.retail.biocare.SubFragments.PendingWithdrawFragment;


import java.util.ArrayList;
import java.util.List;

public class WithdrawListActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Button btnBack;

    private static final String TAG = "WithdrawListActivity";

    public static boolean isClickable = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawlist);

        Intent intent = getIntent();
        isClickable = intent.getBooleanExtra("isClickable",false);

        btnBack = (Button) findViewById(R.id.btn_back);
        tabLayout=(TabLayout) findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.viewpage);
       // tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#106883"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff5252"));
        tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);




        Log.d(TAG, "onCreate: isClickable: "+isClickable);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void setupViewPager(ViewPager viewpager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(PendingWithdrawFragment.newInstance(), "PENDING");
        adapter.addFragment(PaymentHistoryFragment.newInstance(), "COMPLETED");
        viewpager.setAdapter(adapter);


    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            // PrefConnect.writeString(getContext(), PrefConnect.CATEGORY_ID, resp.getCategories().get(position).getCategoryId());

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

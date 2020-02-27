package com.retail.biocare.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.retail.biocare.R;
import com.retail.biocare.SubFragments.FundCreditFragment;
import com.retail.biocare.SubFragments.FundDebitFragment;
import com.retail.biocare.SubFragments.PaymentHistoryFragment;
import com.retail.biocare.SubFragments.PendingWithdrawFragment;

import java.util.ArrayList;
import java.util.List;

public class FundTransferHistoryActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Button btnBack;
    RelativeLayout layoutBack;

    private static final String TAG = "WithdrawListActivity";

    public static boolean isClickable = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundtransferhistory);

        Intent intent = getIntent();
        isClickable = intent.getBooleanExtra("isClickable",false);

        layoutBack = (RelativeLayout) findViewById(R.id.layout_back);
        tabLayout=(TabLayout) findViewById(R.id.tabMessages);
        viewPager=(ViewPager)findViewById(R.id.viewPagerMessages);
       // tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#106883"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff5252"));
        tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);




        Log.d(TAG, "onCreate: isClickable: "+isClickable);


        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void setupViewPager(ViewPager viewpager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FundCreditFragment.newInstance(), "CREDIT");
        adapter.addFragment(FundDebitFragment.newInstance(), "DEBIT");
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

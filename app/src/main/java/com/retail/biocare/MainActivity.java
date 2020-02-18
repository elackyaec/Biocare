package com.retail.biocare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.retail.biocare.adapter.LeftNavAdapter;
import com.retail.biocare.fragment.AddressFragment;
import com.retail.biocare.fragment.AdminFragment;
import com.retail.biocare.fragment.DashboardFragment;
import com.retail.biocare.model.ClassLeftDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
BottomNavigationView bottomNavigationView;
TextView txtTitle;
    List<ClassLeftDrawer> rowItems;

    public static final Integer[] images = {R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol, R.drawable.legal_hammer_symbol};
    ListView lstNave;
    private ActionBarDrawerToggle mDrawerToggle;
    public static String[] titles={"Search Category","Search Category","Search Category","Search Category","Search Category","Search Category","Search Category","Search Category","Search Category","Search Category"};
LeftNavAdapter leftNavAdapter;
    DrawerLayout drawerLayoutNew;
    ClassLeftDrawer item;
    RelativeLayout relativeLayoutMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);
        txtTitle=(TextView)findViewById(R.id.txt_title);
        lstNave=(ListView)findViewById(R.id.lst_nave);
        drawerLayoutNew=(DrawerLayout)findViewById(R.id.drawer_layout_new);
        relativeLayoutMenu=(RelativeLayout)findViewById(R.id.relativemenu);


        beginTransction(new DashboardFragment());
        setupDrawer();
        setupnavigation();
        relativeLayoutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutNew.openDrawer(GravityCompat.START);

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

switch (menuItem.getItemId())
{
    case R.id.nav_home:
        beginTransction(new DashboardFragment());
        txtTitle.setText("Dashboard");
        break;

    case R.id.nav_feeds:
        beginTransction(new AdminFragment());
        txtTitle.setText("Admin");
        break;
    case R.id.nav_msg:
        beginTransction(new AddressFragment());
        txtTitle.setText("Address");
        break;

}
                return true;
            }
        });
    }

    public void beginTransction(final Fragment fragment) {

        try {
         FragmentManager fragmentManager = getSupportFragmentManager();



            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(R.id.frame_container, fragment);
            //transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            Log.e("EX",e.getMessage());
        }


    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayoutNew, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                rowItems.get(0).setTitles("0");
                leftNavAdapter.notifyDataSetChanged();

            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);


            }

        };


        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayoutNew.setDrawerListener(mDrawerToggle);
        // drawerLayoutNew.addDrawerListener(mDrawerToggle);
    }

    private void setupnavigation() {

        rowItems = new ArrayList<ClassLeftDrawer>();

        for (int i = 0; i < titles.length; i++) {
            item = new ClassLeftDrawer(images[i], titles[i]);
            rowItems.add(item);

        }

        leftNavAdapter = new LeftNavAdapter(MainActivity.this,
                R.layout.activity_main_left_drawer_menu, rowItems);
        lstNave.setAdapter(leftNavAdapter);
        lstNave.setOnItemClickListener(MainActivity.this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

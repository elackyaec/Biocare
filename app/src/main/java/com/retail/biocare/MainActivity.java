package com.retail.biocare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.retail.biocare.Interfaces.BankDetailsListener;
import com.retail.biocare.Interfaces.ProfileUpdated;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.activity.LoginActivity;
import com.retail.biocare.activity.ManageBankActivity;
import com.retail.biocare.adapter.LeftNavAdapter;
import com.retail.biocare.fragment.DashboardFragment;
import com.retail.biocare.fragment.FragmentEpins;
import com.retail.biocare.fragment.FragmentGenalogy;
import com.retail.biocare.fragment.FragmentMessages;
import com.retail.biocare.fragment.FragmentProfile;
import com.retail.biocare.fragment.FragmentPurchases;
import com.retail.biocare.fragment.FragmentReceipts;
import com.retail.biocare.fragment.FragmentReports;
import com.retail.biocare.fragment.FragmentWallet;
import com.retail.biocare.model.ClassLeftDrawer;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.bankDetailsMap;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, ProfileUpdated, BankDetailsListener {


    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;
    TextView txtTitle;
    List<ClassLeftDrawer> rowItems;

    public static final Integer[] images = {R.drawable.receipt_png, R.drawable.messages_png, R.drawable.purchases_png, R.drawable.income_png, R.drawable.shutdown_icon};
    ListView lstNave;
    private ActionBarDrawerToggle mDrawerToggle;
    public static String[] titles = {"Receipts", "Messages", "Purchases", "Reports", "Logout"};
    LeftNavAdapter leftNavAdapter;
    DrawerLayout drawerLayoutNew;
    ClassLeftDrawer item;
    RelativeLayout relativeLayoutMenu;
    AlertDialog.Builder builder;

    //Interface
    public static ProfileUpdated profileUpdated;
    public static BankDetailsListener bankDetailsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        builder = new AlertDialog.Builder(MainActivity.this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        lstNave = (ListView) findViewById(R.id.lst_nave);
        drawerLayoutNew = (DrawerLayout) findViewById(R.id.drawer_layout_new);
        relativeLayoutMenu = (RelativeLayout) findViewById(R.id.relativemenu);

        profileUpdated=this;
        profileUpdated.onProfileUpdated();

        bankDetailsListener=this;
        bankDetailsListener.onBankDataChange();

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



                bottomNavigationView.getMenu().setGroupCheckable(0, true, true);


                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        beginTransction(new DashboardFragment());
                        txtTitle.setText("Dashboard");
                        break;

                    case R.id.nav_feeds:
                        beginTransction(new FragmentWallet());
                        txtTitle.setText("Wallet");
                        break;
                    case R.id.nav_msg:
                        beginTransction(new FragmentEpins());
                        txtTitle.setText("E-PINs");
                        break;


                    case R.id.nav_account:
                        beginTransction(new FragmentProfile(profileUpdated));
                        txtTitle.setText("Profile");
                        break;

                    case R.id.nav_genealogy:
                        beginTransction(new FragmentGenalogy());
                        txtTitle.setText("Genealogy");
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

            Log.e("EX", e.getMessage());
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


        drawerLayoutNew.closeDrawer(GravityCompat.START);


        bottomNavigationView.getMenu().setGroupCheckable(0, false, true);



        Log.d(TAG, "onItemClick: "+position);

        switch (position) {
            case 0:
                beginTransction(new FragmentReceipts());
                txtTitle.setText("Receipts");
                break;

            case 1:
                beginTransction(new FragmentMessages());
                txtTitle.setText("Messages");
                break;
            case 2:
                beginTransction(new FragmentPurchases());
                txtTitle.setText("Purchases");
                break;


            case 3:
                beginTransction(new FragmentReports());
                txtTitle.setText("Reports");
                break;

            case 4:
                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to Logout ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
dialog.cancel();
                                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Logout");
                alert.show();
                break;

        }

    }

    @Override
    public void onBankDataChange() {
        new GetBankDetails().execute("");
    }


    @Override
    public void onProfileUpdated() {
        new GetProfileDetails().execute(userBasicData.get("UserID"));
    }



    private class GetBankDetails extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            bankDetailsMap.clear();

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(TAG, "onPostExecute: Response: "+s);
            try{

                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                bankDetailsMap.put("Customername",jsonObject.getString("Customername"));
                bankDetailsMap.put("AccountNumber",jsonObject.getString("AccountNumber"));
                bankDetailsMap.put("BankName",jsonObject.getString("BankName"));
                bankDetailsMap.put("Branch",jsonObject.getString("Branch"));
                bankDetailsMap.put("AccountType",jsonObject.getString("AccountType"));
                bankDetailsMap.put("BankCode",jsonObject.getString("BankCode"));
                bankDetailsMap.put("Email",jsonObject.getString("Email"));

            }catch (Exception e){

                Log.e(TAG, "onPostExecute: JSON: ",e );
            }



        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","MemberbankSearch","MemberId="+ StaticDatas.userBasicData.get("UserID"));
        }
    }


    private class GetProfileDetails extends AsyncTask<String, String, String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(TAG, "onPostExecute: GetProfileDetails: "+s);

            userProfileData.clear();

            try {
                JSONArray jsonArray = new JSONArray(s);
                JSONObject c = jsonArray.getJSONObject(0);

                userProfileData.put("CustomerID",c.getString("CustomerID"));
                userProfileData.put("Sponsorname",c.getString("Sponsorname"));
                userProfileData.put("PlacementID",c.getString("PlacementID"));
                userProfileData.put("Firstname",c.getString("Firstname"));
                userProfileData.put("Lastname",c.getString("Lastname"));
                userProfileData.put("Dateofbirth",c.getString("Dateofbirth"));
                userProfileData.put("AddressLine1",c.getString("AddressLine1"));
                userProfileData.put("AddressLine2",c.getString("AddressLine2"));
                userProfileData.put("City",c.getString("City"));
                userProfileData.put("State",c.getString("State"));
                userProfileData.put("Zipcode",c.getString("Zipcode"));
                userProfileData.put("Gender",c.getString("gender1"));
                userProfileData.put("Country",c.getString("Name"));
                userProfileData.put("Phone",c.getString("Phone"));
                userProfileData.put("Username",c.getString("Username"));
                userProfileData.put("Passport",c.getString("passport"));
                userProfileData.put("PhotoinPassport",c.getString("passportphoto"));
                userProfileData.put("idfront",c.getString("idfront"));
                userProfileData.put("idback",c.getString("idback"));
                userProfileData.put("nationalidphoto",c.getString("nationalidphoto"));
                userProfileData.put("driver",c.getString("driver"));
                userProfileData.put("photoindriverLiscense",c.getString("driverphoto"));
                userProfileData.put("kycapply",c.getString("kycapply"));
                userProfileData.put("DateModified",c.getString("DateModify"));
                userProfileData.put("Customername",c.getString("Customername"));
                userProfileData.put("ProfilePhoto",c.getString("photo"));
                userProfileData.put("Email",c.getString("Email"));


                try {
                    FragmentProfile.txtName.setText(userProfileData.get("Firstname")+" "+userProfileData.get("Lastname"));
                }
                catch (Exception e){
                    Log.e(TAG, "Text View onPostExecute: ", e);
                }

            } catch (JSONException e) {
                Log.e(TAG, "onPostExecute: ",e );

                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","MemberSearch","MemberId="+strings[0]);
        }
    }




}

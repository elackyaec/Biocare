package com.retail.biocare.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.IncomeAdapter;
import com.retail.biocare.model.IncomeModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class AllIncomeReportActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    LinearLayout fromdatelayout,todatelayout;
    TextView txtFromDate,txtTodate,txtNotFound;
    String from,to;

    String selected_date,dateReq;
    Calendar calendar;
    int year, month, day, hour, minute;
    TextView txtUsername,txtFullname,txtSponsor,txtLevel,txtBinary;
    TextView txtRewards,txtGross,txtTDs,txtTax,txtDeduction,txtNetAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allincomereport);
        layoutBack=(RelativeLayout)findViewById(R.id.layout_back);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        fromdatelayout=(LinearLayout) findViewById(R.id.fromdatelayout);
        todatelayout=(LinearLayout)findViewById(R.id.todatelayout);
        txtFromDate=(TextView)findViewById(R.id.txt_fromdate);
        txtTodate=(TextView)findViewById(R.id.txt_todate);
        txtNotFound=(TextView)findViewById(R.id.txt_notfound);
        calendar = Calendar.getInstance();

        txtUsername=(TextView)findViewById(R.id.txt_username);
        txtFullname=(TextView)findViewById(R.id.txt_fullname);
        txtSponsor=(TextView)findViewById(R.id.txt_sponsor);
        txtLevel=(TextView)findViewById(R.id.txt_level);
        txtBinary=(TextView)findViewById(R.id.txtBinary);
        txtGross=(TextView)findViewById(R.id.txt_gross);
        txtTDs=(TextView)findViewById(R.id.txt_tds);
        txtTax=(TextView)findViewById(R.id.txt_tax);
        txtDeduction=(TextView)findViewById(R.id.txt_deduction);
        txtNetAmt=(TextView)findViewById(R.id.txt_netamt);
        txtRewards=(TextView)findViewById(R.id.txt_rewards);


        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fromdatelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
calldatedialog("1");
            }
        });
        todatelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calldatedialog("2");
            }
        });
        if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
        {
            new GetReports().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
        }
        String date = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        txtFromDate.setText(date);
        txtTodate.setText(date);
        from=date;
        to=date;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void calldatedialog(final String status) {
        DatePickerDialog dpd = new DatePickerDialog(AllIncomeReportActivity.this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1,
                                          int monthOfYear, int dayOfMonth) {
                        selected_date = GlobalMethods.Date(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        calendar.set(Calendar.YEAR, year1);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                       dateReq = GlobalMethods.DateConverdionDate3(selected_date);
                        Log.e("firstpickda", dateReq);


                        if(status.equals("1"))
                        {
                            txtFromDate.setText(dateReq);
                            from=dateReq;
                            new GetReports().execute();

                        }
                        else
                        {
                            txtTodate.setText(dateReq);
                            to=dateReq;
                            new GetReports().execute();

                        }

                    }
                }, year, month, day);

        dpd.show();
        if (status.equals("1")) {

            dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        } else {
            dpd.getDatePicker().setMinDate(calendar.getTimeInMillis() - 1000);
        }
    }

    private class GetReports extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(AllIncomeReportActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            Log.e("FT",from+"::"+to);

            if (s.equals("NODATA")){


            }
else if(s.equals("[]"))
            {
               txtNotFound.setVisibility(View.VISIBLE);
            }
            else{
                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject c=jsonArray.getJSONObject(i);


                        Log.e("SS",c.getString("Sponsor"));
                       txtBinary.setText("$"+c.getString("Binary"));
                       txtDeduction.setText("$"+c.getString("Deduction"));
                       txtFullname.setText(c.getString("Fullname"));
                       txtUsername.setText(c.getString("Username"));
                      // txtGross.setText(c.getString(""));
                       txtLevel.setText("$"+c.getString("Level"));
                       txtNetAmt.setText("$"+c.getString("NetAmt"));
                       txtTax.setText("$"+c.getString("ServiceTax"));
                       txtSponsor.setText("$"+c.getString("Sponsor"));
                       txtTDs.setText("$"+c.getString("TDS"));
                       txtRewards.setText("$"+c.getString("Rewards"));

                        txtNotFound.setVisibility(View.GONE);


                    }


                } catch (Exception e) {

                    Log.e("ReportsEx",e.getMessage());
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetAllincomeStatement","MemberId="+userBasicData.get("UserID")+"&Fromdt="+from+"&Todate="+to+"&PageIndex="+"1");


        }
    }

}

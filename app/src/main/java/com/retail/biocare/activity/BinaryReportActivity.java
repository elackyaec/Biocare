package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.IncomeAdapter;
import com.retail.biocare.adapter.PaymentHistoryAdapter;
import com.retail.biocare.model.IncomeModel;
import com.retail.biocare.model.PaymentHistoryModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;


public class BinaryReportActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<IncomeModel> incomeModels=new ArrayList<>();
    IncomeAdapter incomeAdapter;
TextView txtNotFound,txtTitle;
Spinner spinner1,spinner2;
    ArrayList<String> options=new ArrayList<>();
    String month,year;
    ArrayList<Integer> yearlist=new ArrayList<>();
    String reportid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomereport);
        layoutBack=(RelativeLayout)findViewById(R.id.layout_back);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        txtNotFound=(TextView)findViewById(R.id.txt_notfound) ;
        txtTitle=(TextView)findViewById(R.id.txt_title) ;

        spinner1=(Spinner)findViewById(R.id.spinner);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        options.add("January");
        options.add("February");
        options.add("March");
        options.add("April");
        options.add("May");
        options.add("June");
        options.add("July");
        options.add("August");
        options.add("September");
        options.add("October");
        options.add("November");
        options.add("December");



        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_spinner_dropdown_three, options);
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month=String.valueOf(position+1);
                incomeModels.clear();
new GetReports().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for(int i=2020;i<=2040;i++)
        {
            yearlist.add(i);
            ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), R.layout.item_spinner_dropdown_three, yearlist);
            adapter1.setDropDownViewResource(R.layout.item_spinner_dropdown);
            spinner2.setAdapter(adapter1);

            final int finalI = i;
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    year= String.valueOf(yearlist.get(position));
                    incomeModels.clear();

                    new GetReports().execute();
                    Log.e("MM",year);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

        try {
            if(getIntent()!=null)
            {
                reportid=getIntent().getStringExtra("reportid");

                Log.e("RID",reportid);
                if(reportid.equals("11"))
                {
                    txtTitle.setText("Direct Income Report");

                }
               else if(reportid.equals("12"))
                {
                    txtTitle.setText("Level Income Report");

                }
              else  if(reportid.equals("13"))
                {
                    txtTitle.setText("Binary Income Report");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class GetReports extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(BinaryReportActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            //incomeModels=new ArrayList<>();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (s.equals("NODATA")){


            }

            else if(s.equals("[]"))
            {
                txtNotFound.setVisibility(View.VISIBLE);
            }

            else{
Log.e("RID",reportid);
                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject c=jsonArray.getJSONObject(i);

                        String username=c.getString("Username");
                        String fullnmae=c.getString("Name");
                        String tds=c.getString("TDS");
                        String amount=c.getString("Amount");
                        String tax=c.getString("ServiceTax");
                        String netamt=c.getString("NETAmount");

                        incomeModels.add(new IncomeModel(fullnmae,username,amount,tds,tax,netamt));
                        if(incomeModels.size()>0)
                        {
                            incomeAdapter=new IncomeAdapter(BinaryReportActivity.this,incomeModels,reportid);
                            recyclerView.setAdapter(incomeAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(BinaryReportActivity.this));
                            txtNotFound.setVisibility(View.GONE);
                        }
                        else
                        {
                            txtNotFound.setVisibility(View.VISIBLE);


                        }

                    }


                } catch (Exception e) {
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetIncomereport","MemberId="+userBasicData.get("UserID")+"&ReportTypeId="+reportid+"&Month="+month+"&Year="+year+"&PageIndex="+"1");


        }
    }

}

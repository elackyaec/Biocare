package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.TransferEpinsReportsAdapter;
import com.retail.biocare.adapter.UnusedEpinsAdapter;
import com.retail.biocare.adapter.UsedEpinsAdapter;
import com.retail.biocare.model.TransferReportEpinModel;
import com.retail.biocare.model.UnusedEpinModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class TransferEpinReportActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<TransferReportEpinModel> usedEpinModels = new ArrayList<>();
    TransferEpinsReportsAdapter transferEpinsReportsAdapter;
    TextView txtNotFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferepinreport);

        layoutBack = (RelativeLayout) findViewById(R.id.layout_back);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        txtNotFound=(TextView)findViewById(R.id.txt_notfound);



        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
        {
            new GetEpinReport().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class GetEpinReport extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(TransferEpinReportActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            usedEpinModels=new ArrayList<>();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (s.equals("NODATA")){


            }

            else{

                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject c=jsonArray.getJSONObject(i);

                        usedEpinModels.add(new TransferReportEpinModel(c.getString("PinId"),c.getString("PinNumber"),c.getString("Packagename"),c.getString("Amount"),c.getString("Fromuser"),c.getString("Touser"),c.getString("CreatedDate")));

                        if(usedEpinModels.size()>0)
                        {
                            transferEpinsReportsAdapter = new TransferEpinsReportsAdapter(TransferEpinReportActivity.this, usedEpinModels);
                            recyclerView.setAdapter(transferEpinsReportsAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(TransferEpinReportActivity.this));
                        }
                        else
                        {
                            txtNotFound.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }


                    }


                } catch (Exception e) {

                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetEPinReport","MemberId="+userBasicData.get("UserID")+"&PageIndex="+"1");


        }
    }

}

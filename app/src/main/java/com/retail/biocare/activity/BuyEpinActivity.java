package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Interfaces.QtyChange;
import com.retail.biocare.R;
import com.retail.biocare.adapter.UnusedEpinsAdapter;
import com.retail.biocare.model.PackageModel;
import com.retail.biocare.model.UnusedEpinModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class BuyEpinActivity extends AppCompatActivity implements QtyChange {
    RelativeLayout layoutBack;
    ImageView imgPlus, imgMinus;
    int _counter = 1;
    String _stringVal, pkgid;
    TextView txtQty, txtPkgamt, txtAmtPayable, txtBalance;
    Spinner spinner;
    TextView edtUsername;
    Button btnCancel, btnBuy;
    ArrayList<String> options;
    List<PackageModel> packageModels = new ArrayList<>();
    int value = 0;
    float amount = 0;

    QtyChange otyChanged;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyepin);

        layoutBack = (RelativeLayout) findViewById(R.id.layout_back);
        imgPlus = (ImageView) findViewById(R.id.imgplus);
        imgMinus = (ImageView) findViewById(R.id.imgminus);
        txtQty = (TextView) findViewById(R.id.txtvalue);
        txtPkgamt = (TextView) findViewById(R.id.txt_pkgamt);
        txtAmtPayable = (TextView) findViewById(R.id.txt_amtpayable);
        edtUsername = (TextView) findViewById(R.id.edt_username);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        spinner = (Spinner) findViewById(R.id.spinner);
        txtBalance = (TextView) findViewById(R.id.txt_total);


        otyChanged = this;
        edtUsername.setText(userBasicData.get("Name"));


        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _stringVal = txtQty.getText().toString();

                new EpinSave().execute();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (GlobalMethods.isNetworkAvailable(getApplicationContext())) {
            new GetPackages().execute();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _counter++;
                amount = Float.parseFloat(txtPkgamt.getText().toString());
                otyChanged.onQtyChanged(_counter, amount);

                //_stringVal = Integer.toString(_counter);
                //txtQty.setText(_stringVal);
               /*value=Integer.parseInt(txtQty.getText().toString());
                 amount=Integer.parseInt(txtPkgamt.getText().toString());
                float finalval=Float.parseFloat(String.valueOf(value))*Float.parseFloat(String.valueOf(amount));
               Log.e("VAL",finalval+"");

               txtAmtPayable.setText(String.valueOf(finalval));*/

            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_counter > 1) {
                    _counter--;
                    amount = Float.parseFloat(txtPkgamt.getText().toString());
                    otyChanged.onQtyChanged(_counter, amount);
                    /*value=Integer.parseInt(txtQty.getText().toString());
                    amount=Integer.parseInt(txtPkgamt.getText().toString());
                    float finalval=Float.parseFloat(String.valueOf(value))*Float.parseFloat(String.valueOf(amount));
                    _stringVal = Integer.toString(_counter);
                    txtQty.setText(_stringVal);
                    Log.e("VAL",finalval+"");
                    txtAmtPayable.setText(String.valueOf(finalval));*/


                } else {
                    Toast.makeText(getApplicationContext(), "Quantity cannot be Zero", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onQtyChanged(int val, float amount) {

        float finalval = val * amount;

        txtQty.setText(String.valueOf(val));
        txtAmtPayable.setText(String.valueOf(finalval));

    }


    private class GetPackages extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(BuyEpinActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            //packageModels=new ArrayList<>();
            options = new ArrayList<>();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (s.equals("NODATA")) {


            } else {

                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject c = jsonArray.getJSONObject(i);


                        options.add(c.getString("Name"));
                        packageModels.add(new PackageModel(c.getString("Amount"), c.getString("Name"), c.getString("Id")));

                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_spinner_dropdown_three, options);
                        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                pkgid = packageModels.get(position).getPkgid();
                                _counter = 1;

                                txtPkgamt.setText(packageModels.get(position).getAmount());
                                amount = Float.parseFloat(txtPkgamt.getText().toString());
                                otyChanged.onQtyChanged(1, amount);

                                for (int i = 0; i < options.size(); i++) {

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }


                } catch (Exception e) {
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "GetPackageddl", "");


        }
    }

    private class EpinSave extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(BuyEpinActivity.this);

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
            Log.e("SaveEpin", pkgid + "::" + _stringVal);

            if (s.equals("NODATA")) {


            } else if (s.equals("1")) {

                Toast.makeText(getApplicationContext(), "Epins Bought Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "EpinSave", "MemberId=" + userBasicData.get("UserID") + "&PackageID=" + pkgid + "&TotalPin=" + _stringVal);


        }
    }

}

package com.retail.biocare.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.TransferEpinsAdapter;
import com.retail.biocare.adapter.TransferEpinsReportsAdapter;
import com.retail.biocare.adapter.UnusedEpinsAdapter;
import com.retail.biocare.adapter.UsedEpinsAdapter;
import com.retail.biocare.model.TransferEpinModel;
import com.retail.biocare.model.TransferReportEpinModel;
import com.retail.biocare.model.UnusedEpinModel;
import com.retail.biocare.model.UsedEpinModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class TransferEpinActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<TransferEpinModel> transferEpinModels;
    TransferEpinsAdapter transferEpinsAdapter;
Dialog transferDialog;
Button btnTransfer,btnDialogTransfer,btnCancel;
EditText edtUserid,edtUsername;
CheckBox checkBoxAll;
String checkstatus="";
    TextView txtNotFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferepin);

        layoutBack = (RelativeLayout) findViewById(R.id.layout_back);
        btnTransfer=(Button)findViewById(R.id.btn_transfer);
checkBoxAll=(CheckBox)findViewById(R.id.checkbox_all);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        txtNotFound=(TextView)findViewById(R.id.txt_notfound);

        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

btnTransfer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
opentransferdialog();
    }
});

checkBoxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
checkstatus="1";

            transferEpinsAdapter = new TransferEpinsAdapter(TransferEpinActivity.this, transferEpinModels,checkstatus);
            recyclerView.setAdapter(transferEpinsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(TransferEpinActivity.this));
        }
        else
        {
            checkstatus="0";
            transferEpinsAdapter = new TransferEpinsAdapter(TransferEpinActivity.this, transferEpinModels,checkstatus);
            recyclerView.setAdapter(transferEpinsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(TransferEpinActivity.this));
        }
    }
});

if(GlobalMethods.isNetworkAvailable(getApplicationContext()))
{
new TransferEpin().execute();
}
else
{
    Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();

}

    }

    private void opentransferdialog() {

        transferDialog = new Dialog(TransferEpinActivity.this);
        transferDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        transferDialog.setContentView(R.layout.dialog_transfreepin);
        transferDialog.setCancelable(true);
        transferDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        transferDialog.show();
edtUserid=(EditText)transferDialog.findViewById(R.id.edt_userid);
edtUsername=(EditText)transferDialog.findViewById(R.id.edt_username);
        btnDialogTransfer=(Button) transferDialog.findViewById(R.id.btntransfre);
        btnCancel=(Button)transferDialog.findViewById(R.id.btncancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferDialog.dismiss();
            }
        });
        btnDialogTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pin Transferred Successfully",Toast.LENGTH_SHORT).show();
                transferDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class TransferEpin extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(TransferEpinActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            transferEpinModels=new ArrayList<>();
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

                        transferEpinModels.add(new TransferEpinModel(c.getString("PinID"),c.getString("PinNumber"),c.getString("UsedID"),c.getString("Amount"),c.getString("PackageName"),c.getString("CreatedDate"),c.getString("PaidStatus"),""));

                       // String json="[{\"PinID\":\""+c.getString("PinID")+"\"}]";
                       // Log.e("JSON",json);
                      //  new PostTransferEpin().execute(json);
                        if(transferEpinModels.size()>0)
                        {
                            txtNotFound.setVisibility(View.GONE);
                            transferEpinsAdapter = new TransferEpinsAdapter(TransferEpinActivity.this, transferEpinModels,checkstatus);
                            recyclerView.setAdapter(transferEpinsAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(TransferEpinActivity.this));
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
            return new ExtractfromReply().performPost("WSMember","GetUnusedpin","MemberId="+userBasicData.get("UserID")+"&PageIndex="+"1");


        }
    }
    private class PostTransferEpin extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(TransferEpinActivity.this);

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

            if (s.equals("NODATA")){


            }
            else if(s.equals("1"))
            {
                Toast.makeText(TransferEpinActivity.this,"Pin Transferred Successfully",Toast.LENGTH_SHORT).show();
                transferDialog.dismiss();
            }
            else{


                Toast.makeText(TransferEpinActivity.this,"Pin not transferred",Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","EpinTransactions","MemberId="+userBasicData.get("UserID")+"&jsonString="+strings[0]);


        }
    }


}

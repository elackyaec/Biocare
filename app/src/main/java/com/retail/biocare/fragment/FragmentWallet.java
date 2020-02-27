package com.retail.biocare.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.activity.FundTransferActivity;
import com.retail.biocare.activity.FundTransferHistoryActivity;
import com.retail.biocare.activity.PayoutHistoryActivity;
import com.retail.biocare.activity.WithdrawFundActivity;
import com.retail.biocare.activity.WithdrawListActivity;
import com.retail.biocare.model.DashboardModel;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class FragmentWallet extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;

    ArrayList<DashboardModel> dashboardModels=new ArrayList<>();
    LinearLayout withdrawfundslayout,fundtransferlayout,pendingwithdrawlayout;
    LinearLayout paymenthistorylayout,transferhistorylayout,payoutreportlayout;
    float floatmat,floatbal;
    TextView txtTotal;
    String balance,pwd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_wallet, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerWallet);
withdrawfundslayout=(LinearLayout)rootView.findViewById(R.id.withdrawfundslayout);
        fundtransferlayout=(LinearLayout)rootView.findViewById(R.id.fundtransferlayout);
        pendingwithdrawlayout=(LinearLayout)rootView.findViewById(R.id.pendingwithdrawlayout);
        paymenthistorylayout=(LinearLayout)rootView.findViewById(R.id.paymenthistorylayout);
        transferhistorylayout=(LinearLayout)rootView.findViewById(R.id.transferhistorylayout);
        payoutreportlayout=(LinearLayout)rootView.findViewById(R.id.payoutreportlayout);
txtTotal=(TextView)rootView.findViewById(R.id.txt_total);

        withdrawfundslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WithdrawFundActivity.class);
                startActivity(intent);
            }
        });
        fundtransferlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), FundTransferActivity.class);
                startActivity(intent);
            }
        });
        pendingwithdrawlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WithdrawListActivity.class);
                startActivity(intent);
            }
        });

        transferhistorylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), FundTransferHistoryActivity.class);
                startActivity(intent);
            }
        });
        payoutreportlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), PayoutHistoryActivity.class);
                startActivity(intent);
            }
        });
        new GetBalance().execute();
        return rootView;
    }

    private class GetBalance extends AsyncTask<String, String, String> {

        //ProgressDialog progressDialog = new ProgressDialog(ProfileDetailsActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("NODATA")){


            }
            else
            {
                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject c=jsonArray.getJSONObject(i);

                        balance=c.getString("Balance");
                        pwd=c.getString("TransactionPassword");
                        float value=Float.parseFloat(balance);
                        String s1 = String.format("%.2f", value);
                        floatbal=Float.parseFloat(balance);

                        txtTotal.setText("$"+s1);

                        Log.e("PWD",pwd);
                    }


                } catch (Exception e) {
                }

            }


        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetEwalletbalanceTranspwd","MemberId="+userBasicData.get("UserID"));
        }
    }

}

package com.retail.biocare.SubFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.retail.biocare.R;
import com.retail.biocare.activity.PendingWithdrawFundActivity;
import com.retail.biocare.adapter.PendingWFundAdapter;
import com.retail.biocare.model.PendingWithdrawModel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PendingWithdrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendingWithdrawFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    List<PendingWithdrawModel> pendingWithdrawModels;
    PendingWFundAdapter pendingWFundAdapter;
    TextView txtNotFound;

    public PendingWithdrawFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PendingWithdrawFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PendingWithdrawFragment newInstance() {
        PendingWithdrawFragment fragment = new PendingWithdrawFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_withdraw, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        txtNotFound=(TextView)view.findViewById(R.id.txt_notfound);
        if(GlobalMethods.isNetworkAvailable(getContext()))
        {
            new GetPendingFunds().execute();
        }
        else
        {
            Toast.makeText(getContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    private class GetPendingFunds extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            pendingWithdrawModels=new ArrayList<>();
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

                try {

                    JSONArray jsonArray = new JSONArray(s);
                    txtNotFound.setVisibility(View.GONE);
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject c=jsonArray.getJSONObject(i);

                        String username=c.getString("username");
                        String fullnmae=c.getString("CustomerName");
                        String date=c.getString("Datecreated");
                        String amount=c.getString("Debit");
                        String bank=c.getString("Bank");

                        String accname = c.getString("AccountName");
                        String accn0 = c.getString("AccountNumber");
                        String bankname = c.getString("BankName");
                        String branch = c.getString("Branch");
                        String bankcode = c.getString("Bankcode");
                        pendingWithdrawModels.add(new PendingWithdrawModel(username,fullnmae,"",date,amount,accname,accn0,bankname,branch,bankcode));

                        if(pendingWithdrawModels.size()>0)
                        {
                            pendingWFundAdapter=new PendingWFundAdapter(getContext(),pendingWithdrawModels);
                            recyclerView.setAdapter(pendingWFundAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
            return new ExtractfromReply().performPost("WSMember","GetPendingWithdrawfund","MemberId="+userBasicData.get("UserID")+"&PayoutDate="+"0"+"&PageIndex="+"1");


        }
    }

}

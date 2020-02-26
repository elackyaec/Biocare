package com.retail.biocare.SubFragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.MessageModel;
import com.retail.biocare.R;
import com.retail.biocare.adapter.MessageAdapter;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class FragmentInboxMessage extends Fragment {
    private View rootView;
    RecyclerView recyclerView;
    MessageAdapter adapter;
    List<MessageModel> messagelist=new ArrayList<>();
    String message,name,date,subject,status="1";
    TextView txtNotFound;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_inbox_message, container, false);
        txtNotFound=(TextView)rootView.findViewById(R.id.txtnotfound) ;
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerview);
        if(GlobalMethods.isNetworkAvailable(getContext()))
        {
            new GetInbox().execute();
        }
        else
        {
            Toast.makeText(getContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    private class GetInbox extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            messagelist = new ArrayList<>();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (s.equals("NODATA")) {


            }
            else if(s.equals("[]"))
            {
                txtNotFound.setVisibility(View.VISIBLE);
            }

            else {

                try {
txtNotFound.setVisibility(View.GONE);
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject c = jsonArray.getJSONObject(i);

                        name=c.getString("Sendername");
                        date=c.getString("CreatedDate");

                        subject=c.getString("subject");
                        String  message=c.getString("Message");
                        String id=c.getString("id");
                        Log.e("ID1",id);

                        messagelist.add(new MessageModel(date, subject, message, name,id));

                        if (messagelist.size() > 0) {
                            adapter = new MessageAdapter(getContext(), messagelist,status);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            txtNotFound.setVisibility(View.GONE);
                        } else {
                            txtNotFound.setVisibility(View.VISIBLE);
                        }
                    }


                } catch (Exception e) {
                    Log.e("ES", e.getMessage());
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "MessageInbox", "MemberId=" + userBasicData.get("UserID") + "&PageIndex=" + "1");


        }
    }

}

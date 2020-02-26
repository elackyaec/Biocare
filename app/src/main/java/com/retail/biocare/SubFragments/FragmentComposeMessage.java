package com.retail.biocare.SubFragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.retail.biocare.R;
import com.retail.biocare.activity.WithdrawFundActivity;
import com.retail.biocare.model.SendMsgModel;
import com.retail.biocare.model.Usermodel;
import com.retail.biocare.utils.ExtractfromReply;
import com.retail.biocare.utils.GlobalMethods;
import com.retail.biocare.utils.TransferEpinArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class FragmentComposeMessage extends Fragment {
    private View rootView;
    Spinner spinner;
    EditText edtSubject,edtMsg;
    Button btnSend,btnCancel;
    String msg,subject,userselect,userid,json;
    List<Usermodel> usermodels;
    ArrayList<String> options;
    List<SendMsgModel> sendMsgModels=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_compose_message, container, false);
spinner=(Spinner)rootView.findViewById(R.id.spinner);
edtSubject=(EditText)rootView.findViewById(R.id.txt_subject);
        edtMsg=(EditText)rootView.findViewById(R.id.edt_msg);
        btnSend=(Button)rootView.findViewById(R.id.btn_sendmsg);
        btnCancel=(Button)rootView.findViewById(R.id.btn_cancel);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
        if (GlobalMethods.isNetworkAvailable(getContext())) {
            new GetUsers().execute();
        } else {
            Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    private void validation() {

        boolean cancel=false;
        msg=edtMsg.getText().toString();
        subject=edtSubject.getText().toString();


        if(TextUtils.isEmpty(subject))
        {
            cancel=true;
            Toast.makeText(getContext(),"Subject is empty",Toast.LENGTH_SHORT).show();
        }
       else if(TextUtils.isEmpty(msg))
        {
            cancel=true;
            Toast.makeText(getContext(),"Message is empty",Toast.LENGTH_SHORT).show();
        }

       if(!cancel)
       {
           if(GlobalMethods.isNetworkAvailable(getContext()))
           {
               sendMsgModels.add(new SendMsgModel("0",userBasicData.get("UserID"),userid,subject,msg,userBasicData.get("UserID")));
               json = new Gson().toJson(sendMsgModels);
               Log.e("JSONMSG",json+"::"+userid);
new SendMsg().execute(json);
           }
           else
           {
               Toast.makeText(getContext(),getString(R.string.no_internet),Toast.LENGTH_SHORT).show();

           }
       }
    }


    private class GetUsers extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            usermodels = new ArrayList<>();
            options=new ArrayList<>();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            if (s.equals("NODATA")) {


            } else {

                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject c = jsonArray.getJSONObject(i);

                        options.add(c.getString("Name"));
                        usermodels.add(new Usermodel(c.getString("Customername"),c.getString("Name"),c.getString("Id")));

                        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item_spinner_dropdown_three, options);
                        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                userselect=options.get(position);
                                userid=usermodels.get(position).getId();



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
            return new ExtractfromReply().performPost("WSMember", "GetUserddl", "");


        }
    }
    private class SendMsg extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
progressDialog.show();
progressDialog.setMessage("Please wait....");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
progressDialog.dismiss();
            if (s.equals("NODATA")) {


            } else if(s.equals("1")) {
                Toast.makeText(getContext(),"Message sent successfully",Toast.LENGTH_SHORT).show();


            }


        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "MessageSave", "jsonString=" + strings[0]);
        }
    }

}

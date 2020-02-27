package com.retail.biocare.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.retail.biocare.Models.MessageModel;
import com.retail.biocare.R;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.userBasicData;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.DataObjectHolder> {
    private static final String TAG = "MessageAdapter";

    Context context;
    CallBack callBack;
    Dialog viewdialog;
    Button btnCancel;
    List<MessageModel> messagelist = new ArrayList<>();
    TextView txtMsg,txtSubject;
    String status;

    public MessageAdapter(Context context, List<MessageModel> messagelist,String status) {
        this.context = context;
        this.messagelist = messagelist;
        this.status=status;
    }

    public interface CallBack {
        public void call(int position);
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtmsg, txtDate, txt_sub,txTTitle;
        Button btnView;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtmsg = (TextView) itemView.findViewById(R.id.txt_msg);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
            txt_sub = (TextView) itemView.findViewById(R.id.txt_sub);
            btnView=(Button)itemView.findViewById(R.id.btn_view);
            txTTitle = (TextView) itemView.findViewById(R.id.txttitel);

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {


        holder.txtDate.setText(messagelist.get(position).getDate());
        holder.txtmsg.setText(messagelist.get(position).getMessage());
        holder.txt_sub.setText("Subject: "+messagelist.get(position).getSubject());

        String finName = messagelist.get(position).getName().substring(0, 1).toUpperCase() + messagelist.get(position).getName().substring(1);

        holder.txtName.setText(finName);

if(status.equals("1"))
{
    holder.txTTitle.setText("Sent From : ");
}
else
{
    holder.txTTitle.setText("Send To : ");

}
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

Log.e("ID",messagelist.get(position).getMsgid());
                openviewdialog(messagelist.get(position).getMsgid(),messagelist.get(position).getMessage());
            }
        });

    }

    private void openviewdialog(String id,String msg) {

        viewdialog = new Dialog(context);
        viewdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewdialog.setContentView(R.layout.dialog_msg);
        viewdialog.setCancelable(true);
        viewdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewdialog.show();

         txtMsg = viewdialog.findViewById(R.id.txtMsg);

         txtSubject = viewdialog.findViewById(R.id.txtSubject);
        txtMsg.setText(msg);

//txtSubject.setText(sub);
        btnCancel = (Button) viewdialog.findViewById(R.id.btnclose);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewdialog.dismiss();

            }
        });
        //new GetMsg().execute(id);


    }

    @Override
    public int getItemCount() {
        return messagelist.size();
    }
    private class GetMsg extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            messagelist = new ArrayList<>();
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


                       String message=c.getString("message");

                        txtMsg.setText(message);
                    }


                } catch (Exception e) {
                    Log.e("ES", e.getMessage());
                }

            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "MessageSearch", "MessageId=" + strings[0]);


        }
    }


}



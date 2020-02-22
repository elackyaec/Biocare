package com.retail.biocare.utils;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.retail.biocare.StaticData.StaticDatas.appUUID;
import static com.retail.biocare.StaticData.StaticDatas.hostURL;

public class ExtractfromReply {

    private static final String TAG = "ExtractfromReply";


    public static String parseData(StringBuilder replyData){
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">";
        return replyData.substring(xmlHeader.length()).split("</string>")[0];
    }


    public String performPost(String path, String oP, String parameters){


        String response="NODATA";

        String finalParameter = parameters +"&UID="+appUUID;
        final String USER_AGENT = "Mozilla/5.0";

        Log.d(TAG, "performPost: finalParameter: "+finalParameter);

        try {
            URL obj = new URL(hostURL+path+".asmx/"+oP);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            //os.write(parameters.getBytes());
            os.write(finalParameter.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            Log.d(TAG, "performPost: "+path+" :: "+oP+" Responsecode :"+ responseCode);


            BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder buf = new StringBuilder();
            String line;

            while ((line=reader.readLine()) != null) {
                buf.append(line);
            }


            Log.d(TAG, "performPost: "+path+" :: "+oP+" :: Reply"+ ExtractfromReply.parseData(buf));
            response = ExtractfromReply.parseData(buf);
        }
        catch (Exception e){
            Log.d(TAG, "performPost: Exception"+e);
//            Toast.makeText(context, "performPost: Exception"+e, Toast.LENGTH_SHORT).show();
        }

        return response;

    }

}

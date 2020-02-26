package com.retail.biocare.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GlobalMethods {
   public static String EMAIL_PATTERN = "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$";

    public static String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static void Toast(Context context, String Message) {
        Toast toast = Toast.makeText(context, Message, Toast.LENGTH_LONG);
        toast.show();
    }

    public static final boolean validateEmail(String target) {
        if (target !=null && target.length() > 1) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(target);
            return matcher.matches();
        } else if (target.length() == 0) {
            return false;
        } else {
            return false;
        }
    }
    public static boolean validateEmailAddressFormat(String emailAddress) {

        //android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();

        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
    public static String getUniqueId(Context c) {
        return Settings.Secure.getString(c.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }


    public static String TimeConversion24to12(String date){

        DateFormat df = new SimpleDateFormat("HH:mm:ss");

        DateFormat outputformat = new SimpleDateFormat("hh:mm a");
        Date dates = null;
        String output = null;
        try{

            dates= df.parse(date);

            output = outputformat.format(dates);


        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String Date(String date){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String DateConverdion(String date){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("dd MMM yyyy");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String DateConverdionDate(String date){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("MM/dd/yyyy");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String DateConverdionDate3(String date){

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("MM/dd/yyyy");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String DateConverdion1(String date){

        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("MM/dd/yyyy");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String DateTime24(String date){

        DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }

    public static String TimeConversion(String date){

        DateFormat df = new SimpleDateFormat("hh:mm:ss a");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String TimeConversionHome(String date){

        DateFormat df = new SimpleDateFormat("hh:mm");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("hh:mm a");
        Date dates = null;
        String output = null;
        try{
            //Converting the input String to Date
            dates= df.parse(date);
            //Changing the format of date and storing it in String
            output = outputformat.format(dates);
            //Displaying the date

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static String Time24TO12(String time){
        String output = null;
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final Date dateObj = sdf.parse(time);

            output = new SimpleDateFormat("hh:mm a").format(dateObj);

        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static String Time24TO12ss(String time){
        String output = null;
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            final Date dateObj = sdf.parse(time);

            output = new SimpleDateFormat("hh:mm a").format(dateObj);

        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return output;
    }
}

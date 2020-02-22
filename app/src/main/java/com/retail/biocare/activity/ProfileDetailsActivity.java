package com.retail.biocare.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.google.gson.Gson;
import com.retail.biocare.Models.UserDetailsModel;
import com.retail.biocare.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import static com.retail.biocare.StaticData.StaticDatas.hostURL;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class ProfileDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ProfileDetailsActivity";

    private EditText edtFirstname;
    private EditText edtLastname;
    private EditText edtEmail;
    private EditText edtPhoneno;
    private EditText edtMobileNo;
    private EditText edtAddress;
    private EditText edtStreet;
    private EditText edtCity;
    private EditText edtZipcode;
    private EditText edtState;
    private EditText edtCountry;

    private String CustomerID, FirstName, LastName, DateofBirth, Gender, Marital, address1, address2, city, state, zip, country, Phone, Email, Nominee, Relation, NomineeaAge, KeyValue;

    private ImageView imgProfile;
    private String imageEncoded="";
    private Bitmap imageFromPrevios;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        findViews();
        setData();

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();

            }
        });

    }

    private void validate() {

        CustomerID = userProfileData.get("CustomerID");
        FirstName = String.valueOf(edtFirstname.getText()); //ToValidate
        LastName = String.valueOf(edtLastname.getText()); //ToValidate
        DateofBirth = userProfileData.get("Dateofbirth");
        Gender = userProfileData.get("Gender");
        Marital = "";
        address1 = String.valueOf(edtAddress.getText()); //ToValidate
        address2 = String.valueOf(edtStreet.getText()); //ToValidate
        city = String.valueOf(edtCity.getText()); //ToValidate
        state = String.valueOf(edtState.getText()); //ToValidate
        zip = String.valueOf(edtZipcode.getText());//ToValidate
        country = String.valueOf(edtCountry.getText()); //ToValidate
        Phone = String.valueOf(edtMobileNo.getText()); //ToValidate
        Email = userProfileData.get("Email");
        Nominee = " ";
        Relation = " ";
        NomineeaAge = "0";
        KeyValue = imageEncoded;

        if(FirstName.isEmpty()){
            edtFirstname.requestFocus();
            edtFirstname.setError("Required");
        }

        else if(LastName.isEmpty()){
            edtLastname.requestFocus();
            edtLastname.setError("Required");
        }

        else if(address1.isEmpty()){
            edtAddress.requestFocus();
            edtAddress.setError("Required");
        }

        else if(address2.isEmpty()){
            edtStreet.requestFocus();
            edtStreet.setError("Required");
        }

        else if(city.isEmpty()){
            edtCity.requestFocus();
            edtCity.setError("Required");
        }

        else if(state.isEmpty()){
            edtState.requestFocus();
            edtState.setError("Required");
        }

        else if(zip.isEmpty()){
            edtZipcode.requestFocus();
            edtZipcode.setError("Required");
        }

        else if(country.isEmpty()){
            edtCountry.requestFocus();
            edtCountry.setError("Required");
        }

        else if(Phone.isEmpty()){
            edtMobileNo.requestFocus();
            edtMobileNo.setError("Required");
        }

        else {

            UserDetailsModel userDetailsModel = new UserDetailsModel(CustomerID, FirstName, LastName, DateofBirth, Gender, Marital, address1, address2, city, state, zip, country, Phone, Email, Nominee, Relation, NomineeaAge, KeyValue);

            String jsonData = new Gson().toJson(userDetailsModel);
            Log.d(TAG, "validate: jsonData: "+jsonData);

        }




    }



    private void setData() {

        edtFirstname.setText(userProfileData.get("Firstname"));
        edtLastname.setText(userProfileData.get("Lastname"));
        edtEmail.setText(userProfileData.get("Email"));
        edtMobileNo.setText(userProfileData.get("Phone"));
        edtAddress.setText(userProfileData.get("AddressLine1"));
        edtStreet.setText(userProfileData.get("AddressLine2"));
        edtState.setText(userProfileData.get("State"));
        edtCity.setText(userProfileData.get("City"));
        edtZipcode.setText(userProfileData.get("Zipcode"));
        edtCountry.setText(userProfileData.get("Country"));

        imgProfile = findViewById(R.id.imgProfile);

        try {
            Glide.with(ProfileDetailsActivity.this).load(hostURL+userBasicData.get("Photo").substring(3)).signature(new ObjectKey(userProfileData.get("DateModified"))).into(imgProfile);
            new GetBitmapfromPrev().execute(hostURL+userBasicData.get("Photo").substring(3));
        }
        catch (Exception e){
            Log.e(TAG, "onCreateView: ",e );
        }

    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-21 16:13:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        edtFirstname = (EditText)findViewById( R.id.edt_firstname );
        edtLastname = (EditText)findViewById( R.id.edt_lastname );
        edtEmail = (EditText)findViewById( R.id.edt_email );
        edtPhoneno = (EditText)findViewById( R.id.edt_phoneno );
        edtMobileNo = (EditText)findViewById( R.id.edt_mobile_no );
        edtAddress = (EditText)findViewById( R.id.edt_address );
        edtStreet = (EditText)findViewById( R.id.edt_street );
        edtCity = (EditText)findViewById( R.id.edt_city );
        edtZipcode = (EditText)findViewById( R.id.edt_zipcode );
        edtState = (EditText)findViewById( R.id.edt_state );
        edtCountry = (EditText)findViewById( R.id.edt_country );


    }


    private class GetBitmapfromPrev extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                imageFromPrevios = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                //Bitmap imageFromPrevios = imgProfile.getDrawingCache();
                imageEncoded = encodeTobase64(imageFromPrevios);
                //Log.d(TAG, "imageEncoded from Previous: " + imageEncoded);
            } catch (IOException e) {
                System.out.println(e);
            }


            return null;
        }
    }

    public String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();
        imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

       /* try {
            encryptedData = EncDec.encrypt(imageEncoded);
            Log.d(TAG, "AES Encrypted Data: "+ encryptedData);

            Log.d(TAG, "encodeTobase64: Decrypted :"+EncDec.decrypt(encryptedData));

        }catch (Exception e){
            Log.d(TAG, "AES Encrypted Exception"+ e);
        }*/
        //Log.d(TAG, "encodeTobase64: "+ imageEncoded);

        //new CallAPI().execute("");
        return imageEncoded;
    }


}

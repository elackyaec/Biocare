package com.retail.biocare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.retail.biocare.Models.TestinomialModel;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.utils.ExtractfromReply;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class TestinomialSaveActivity extends AppCompatActivity {

    private static final String TAG = "TestinomialSaveActivity";

    String KeyValue ="";

    TestinomialModel testinomialModel;

    private EditText txtTitle, txtTestinomialDescription;
    private Button btnSendTestinomial;
    private TextView txtName, txtuserCode;

    private Uri uri;
    private Bitmap bitmap;
    private Dialog camera_dialog;

    private ImageView imgImage;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    private String id, title, description, MemberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinomial_save);

        txtTitle = findViewById(R.id.txtTitle);
        txtTestinomialDescription = findViewById(R.id.txtTestinomialDescription);
        btnSendTestinomial = findViewById(R.id.btnSendTestinomial);
        imgImage = findViewById(R.id.imgImage);
        txtName = findViewById(R.id.txtName);
        txtuserCode =findViewById(R.id.txtuserCode);
        txtName.setText(userProfileData.get("Firstname"));
        txtuserCode.setText(StaticDatas.userBasicData.get("Username"));
        findViewById(R.id.img_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setVerifiedDialog();

            }
        });
        findViewById(R.id.btncancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        btnSendTestinomial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = "0";
                title = String.valueOf(txtTitle.getText());
                description = String.valueOf(txtTestinomialDescription.getText());
                MemberId = StaticDatas.userBasicData.get("UserID");

                if (title.isEmpty())
                {
                    txtTitle.requestFocus();
                    txtTitle.setError("Required");
                }
                else if (description.isEmpty()){
                    txtTestinomialDescription.requestFocus();
                    txtTestinomialDescription.setError("Required");
                }

                else {

                    testinomialModel = new TestinomialModel(id, title, description, KeyValue, MemberId);
                    String jsonData = "["+new Gson().toJson(testinomialModel)+"]";

                    Log.d(TAG, "onClick: "+ jsonData);

                    new UpdateTestinomial().execute(jsonData);
                }

            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setVerifiedDialog() {

        camera_dialog = new Dialog(TestinomialSaveActivity.this);
        camera_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        camera_dialog.setContentView(R.layout.dialog_photo);
        camera_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        camera_dialog.show();
        camera_dialog.setCancelable(false);

        TextView txt_gallery = (TextView) camera_dialog.findViewById(R.id.txt_gallery);
        TextView txt_take_photo = (TextView) camera_dialog.findViewById(R.id.txt_take_photo);
        Button btn_cancel = (Button) camera_dialog.findViewById(R.id.btn_cancel);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera_dialog.dismiss();

            }
        });

        txt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera_dialog.dismiss();
                galleryIntent();
            }
        });
        txt_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera_dialog.dismiss();
                cameraIntent();
            }
        });
    }

    private void galleryIntent() {
        try {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cameraIntent() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();
        KeyValue = Base64.encodeToString(b, Base64.DEFAULT);

       /* try {
            encryptedData = EncDec.encrypt(KeyValue);
            Log.d(TAG, "AES Encrypted Data: "+ encryptedData);

            Log.d(TAG, "encodeTobase64: Decrypted :"+EncDec.decrypt(encryptedData));

        }catch (Exception e){
            Log.d(TAG, "AES Encrypted Exception"+ e);
        }*/
        //Log.d(TAG, "encodeTobase64: "+ KeyValue);

        //new CallAPI().execute("");
        return KeyValue;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECT_FILE) {
                    uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        //imgProfile.setImageBitmap(bitmap);
                        Glide.with(TestinomialSaveActivity.this).load(bitmap).into(imgImage);
                        //save(encodeTobase64(bitmap));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.d(TAG, "onActivityResult: "+encodeTobase64(bitmap));

                    Log.d(TAG, "onActivityResult: Bitmap "+ KeyValue);
                    /*ExifInterface ei = new ExifInterface(String.valueOf(img_foodImage));
                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_UNDEFINED);

                    rotatedBitmap = null;
                    switch(orientation) {

                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotatedBitmap = rotateImage(bitmap, 90);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotatedBitmap = rotateImage(bitmap, 180);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotatedBitmap = rotateImage(bitmap, 270);
                            break;

                        case ExifInterface.ORIENTATION_NORMAL:
                        default:
                            rotatedBitmap = bitmap;
                    }*/

                } else if (requestCode == REQUEST_CAMERA) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    Glide.with(TestinomialSaveActivity.this).load(bitmap).into(imgImage);
                    // ExifInterface ei = new ExifInterface(String.valueOf(img_foodImage));
                    Log.d(TAG, "AfterGlide ");
                    //int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);

                    encodeTobase64(bitmap);
                    //Log.d(TAG, "onActivityResult: "+encodeTobase64(bitmap));

                    /*switch (orientation) {

                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotatedBitmap = rotateImage(bitmap, 90);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotatedBitmap = rotateImage(bitmap, 180);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotatedBitmap = rotateImage(bitmap, 270);
                            break;

                        case ExifInterface.ORIENTATION_NORMAL:
                        default:
                            rotatedBitmap = bitmap;
                    }*/

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "onActivityResult: Exception "+ e);
        }

    }


    private class UpdateTestinomial extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(TestinomialSaveActivity.this);

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

            Log.d(TAG, "onPostExecute: Response: "+s);

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","TestimonialSave","jsonString="+strings[0]);
        }
    }
}

package com.retail.biocare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.retail.biocare.R;

import static com.retail.biocare.StaticData.StaticDatas.hostURL;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

interface QuantityChanged{
    void onQuatityChanged(int qty);
}

public class ItemDescriptionActivity extends AppCompatActivity implements QuantityChanged{

    private static final String TAG = "ItemDescriptionActivity";

    private ImageView imgItem;
    private Float floatPrice;
    private TextView txtItemname, txtCategory, txtDesc, txtPrice, txtQuantity, txtCartTotal;


    private String intentImage, intentIteName, intentPrice, intetnDescription , secondLine;


    private int itemCount = 1;

    QuantityChanged quantityChanged = this;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgItem = findViewById(R.id.imgItem);
        txtItemname = findViewById(R.id.txtItemname);
        txtCategory = findViewById(R.id.txtCategory);
        txtDesc = findViewById(R.id.txtDesc);
        txtPrice = findViewById(R.id.txtPrice);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtCartTotal = findViewById(R.id.txtCartTotal);



        /*
                intent.putExtra("itemName", itemDetails.get(position).getItemName());
                intent.putExtra("itemImage", itemDetails.get(position).getItemName());
                intent.putExtra("itemPrice",  itemDetails.get(position).getItemPrice());
                intent.putExtra("itemColor",  itemDetails.get(position).getItemColor());
                intent.putExtra("itemSize",  itemDetails.get(position).getItemSize());
                intent.putExtra("itemDescription",  itemDetails.get(position).getItemDescription());

         */

        Intent intent = getIntent();

        intentImage = intent.getStringExtra("itemImage");
        intentIteName = intent.getStringExtra("itemName");
        intentPrice = intent.getStringExtra("itemPrice");
        intetnDescription = intent.getStringExtra("itemDescription");
        floatPrice = Float.parseFloat(intent.getStringExtra("floatPrice"));

        secondLine ="Size: "+intent.getStringExtra("itemSize") + "     Color: "+intent.getStringExtra("itemColor");
        txtCategory.setText(secondLine);

        txtItemname.setText(intentIteName);
        txtDesc.setText(intentPrice);
        txtDesc.setText(intetnDescription);

        txtPrice.setText(intentPrice);

        //txtQuantity.setText(String.valueOf(itemCount));

        quantityChanged.onQuatityChanged(1);

        findViewById(R.id.imgPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCount<9){
                    itemCount++;
                    quantityChanged.onQuatityChanged(itemCount);
                }
            }
        });

        findViewById(R.id.imgMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCount>1){
                    itemCount--;
                    quantityChanged.onQuatityChanged(itemCount);
                }
            }
        });






        try {
            //Glide.with(context).load(itemDetails.get(position).getItemImage()).into(holder.imgItemImage);
            Glide.with(this).load(hostURL + intentImage.substring(3)).signature(new ObjectKey(userProfileData.get("DateModified"))).placeholder(R.drawable.noimage).into(imgItem);

        }
        catch (Exception e){
            Log.d(TAG, "Glide Exception : "+e);
        }
    }

    @Override
    public void onQuatityChanged(int qty) {

        txtQuantity.setText(String.valueOf(qty));

        txtCartTotal.setText(userBasicData.get("Currency")+String.valueOf(floatPrice * qty));

    }
}

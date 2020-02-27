package com.retail.biocare.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.Models.CategoryItemModels;
import com.retail.biocare.R;
import com.retail.biocare.adapter.ProductsRecyclerAdapter;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";

    private ArrayList<CategoryItemModels>  productsDetails = new ArrayList<>();
    private ProductsRecyclerAdapter productsRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_list);

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initRecycler();
        setData();

    }

    private void setData() {

        new GetProducts().execute("0","0","0","1");


    }

    private void initRecycler() {

        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);
        productsRecyclerAdapter = new ProductsRecyclerAdapter(this, productsDetails);
        recyclerView.setAdapter(productsRecyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }


    private class GetProducts extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(ProductActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            productsDetails.clear();
            progressDialog.setMessage("Please wait");
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            Log.d(TAG, "onPostExecute: Response: ");

            if (!s.equalsIgnoreCase("NODATA")){

                String slNo, itemId ,itemName, itemImage, itemOffer, itemPrice, itemMRP, itemRating, shippingCharge , itemTax, itemAvailableQuantity;
                String costprice, itemDescription, itemSize, itemColor, mrpprice1, shipcharges1, categoryId, subCategoryId;


                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject c = jsonArray.getJSONObject(i);

                        slNo = c.getString("SlNo");
                        itemId = c.getString("Id");
                        itemName = c.getString("ProductName");
                        itemImage = c.getString("photo1");
                        itemOffer = "0"; //ToDo
                        itemPrice = c.getString("memberprice");
                        itemMRP = c.getString("mrpprice");
                        itemRating = "0"; //ToDO
                        shippingCharge = c.getString("shipCharges");
                        itemTax = c.getString("Tax");
                        itemAvailableQuantity = c.getString("AvailableQuantity");
                        costprice = c.getString("costprice");
                        itemDescription = c.getString("description");
                        itemSize = c.getString("size");
                        itemColor = c.getString("color");
                        mrpprice1 = c.getString("mrpprice1");
                        shipcharges1 = c.getString("shipcharges1");
                        categoryId = c.getString("CategoryId");
                        subCategoryId = c.getString("SubCategoryId");


                        productsDetails.add(new CategoryItemModels(slNo, itemId ,itemName, itemImage, itemOffer, itemPrice, itemMRP, itemRating, shippingCharge , itemTax, itemAvailableQuantity,costprice, itemDescription, itemSize, itemColor, mrpprice1, shipcharges1, categoryId, subCategoryId));




                    }

                }catch (Exception e){

                    Toast.makeText(ProductActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onPostExecute: ",e );
                }


                productsRecyclerAdapter.notifyDataSetChanged();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember","GetAvailableProduct","CategoryId="+strings[0]+"&SubCategoryId="+strings[1]+"&ProductId="+strings[2]+"&PageIndex="+strings[3]);
        }
    }

}

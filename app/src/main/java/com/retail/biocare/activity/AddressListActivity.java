package com.retail.biocare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.AddresslistAdapter;


public class AddressListActivity extends AppCompatActivity {

    AddresslistAdapter addresslistAdapter;
    RecyclerView recyclerView;
    RelativeLayout layoutBack;
    Button btnAdd;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresslist);
        layoutBack = (RelativeLayout) findViewById(R.id.layout_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        btnAdd=(Button)findViewById(R.id.btn_add);
        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddressListActivity.this,AddAddressActivity.class);
                startActivity(intent);
            }
        });
        addresslistAdapter=new AddresslistAdapter(AddressListActivity.this);
        recyclerView.setAdapter(addresslistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddressListActivity.this));
    }
}

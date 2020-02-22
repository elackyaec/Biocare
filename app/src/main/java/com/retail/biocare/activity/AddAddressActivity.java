package com.retail.biocare.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.retail.biocare.R;


public class AddAddressActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    String color;
    LinearLayout layoutheader;
    Button btbSavr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        layoutBack=(RelativeLayout)findViewById(R.id.layout_back);
        layoutheader=(LinearLayout)findViewById(R.id.header);
        btbSavr=(Button) findViewById(R.id.btn_save);

        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btbSavr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            if(getIntent()!=null) {
                color = getIntent().getStringExtra("color");
                if (color.equalsIgnoreCase("red")) {

                    layoutheader.setBackgroundResource(R.color.colorPrimary);
                    btbSavr.setBackgroundResource(R.color.colorPrimary);

                    Window window = AddAddressActivity.this.getWindow();

                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(AddAddressActivity.this, R.color.colorPrimary));
                    }
                } else if (color.equalsIgnoreCase("green"))
                {
                    layoutheader.setBackgroundResource(R.color.colorSecondary);
                    btbSavr.setBackgroundResource(R.color.colorSecondary);
                    Window window = AddAddressActivity.this.getWindow();

                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(AddAddressActivity.this, R.color.colorSecondary));
                    }
                }

                else if(color.equalsIgnoreCase("blue"))
                {
                    layoutheader.setBackgroundResource(R.color.colorTertiary);
                    btbSavr.setBackgroundResource(R.color.colorTertiary);
                    Window window = AddAddressActivity.this.getWindow();

                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(AddAddressActivity.this, R.color.colorTertiary));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

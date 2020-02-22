package com.retail.biocare.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retail.biocare.R;
import com.retail.biocare.adapter.TransferEpinsAdapter;
import com.retail.biocare.adapter.UsedEpinsAdapter;
import com.retail.biocare.model.TransferEpinModel;
import com.retail.biocare.model.UsedEpinModel;

import java.util.ArrayList;
import java.util.List;

public class TransferEpinActivity extends AppCompatActivity {
    RelativeLayout layoutBack;
    RecyclerView recyclerView;
    List<TransferEpinModel> transferEpinModels = new ArrayList<>();
    TransferEpinsAdapter transferEpinsAdapter;
Dialog transferDialog;
Button btnTransfer,btnDialogTransfer,btnCancel;
EditText edtUserid,edtUsername;
CheckBox checkBoxAll;
String checkstatus="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferepin);

        layoutBack = (RelativeLayout) findViewById(R.id.layout_back);
        btnTransfer=(Button)findViewById(R.id.btn_transfer);
checkBoxAll=(CheckBox)findViewById(R.id.checkbox_all);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));
        transferEpinModels.add(new TransferEpinModel("Pin ID: 123", "456467", "", "$400", "PKG_001", "12/2/2020", "Active","345"));

        transferEpinsAdapter = new TransferEpinsAdapter(TransferEpinActivity.this, transferEpinModels,checkstatus);
        recyclerView.setAdapter(transferEpinsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TransferEpinActivity.this));
        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

btnTransfer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
opentransferdialog();
    }
});

checkBoxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
checkstatus="1";
            transferEpinsAdapter = new TransferEpinsAdapter(TransferEpinActivity.this, transferEpinModels,checkstatus);
            recyclerView.setAdapter(transferEpinsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(TransferEpinActivity.this));
        }
        else
        {
            checkstatus="0";
            transferEpinsAdapter = new TransferEpinsAdapter(TransferEpinActivity.this, transferEpinModels,checkstatus);
            recyclerView.setAdapter(transferEpinsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(TransferEpinActivity.this));
        }
    }
});
    }

    private void opentransferdialog() {

        transferDialog = new Dialog(TransferEpinActivity.this);
        transferDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        transferDialog.setContentView(R.layout.dialog_transfreepin);
        transferDialog.setCancelable(true);
        transferDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        transferDialog.show();
edtUserid=(EditText)transferDialog.findViewById(R.id.edt_userid);
edtUsername=(EditText)transferDialog.findViewById(R.id.edt_username);
        btnDialogTransfer=(Button) transferDialog.findViewById(R.id.btntransfre);
        btnCancel=(Button)transferDialog.findViewById(R.id.btncancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferDialog.dismiss();
            }
        });
        btnDialogTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pin Transferred Successfully",Toast.LENGTH_SHORT).show();
                transferDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

package com.retail.biocare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.retail.biocare.ChangePasswordActivity;
import com.retail.biocare.Interfaces.ProfileUpdated;
import com.retail.biocare.LoginTimingActivity;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.TestinomialSaveActivity;
import com.retail.biocare.activity.AddReviewActivity;
import com.retail.biocare.activity.AddressListActivity;
import com.retail.biocare.activity.ChangeTransactionPasswordActivity;
import com.retail.biocare.activity.KYCApplicationActivity;
import com.retail.biocare.activity.KYCDeatilsActivity;
import com.retail.biocare.activity.ManageBankActivity;
import com.retail.biocare.activity.ProfileDetailsActivity;

import static com.retail.biocare.StaticData.StaticDatas.hostURL;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

public class FragmentProfile extends Fragment {

    private static final String TAG = "FragmentProfile";

    private View rootView;

    ProfileUpdated profileUpdated;

    public static TextView txtName,txtAddressLine1,txtAddressLine2,txtCity,txtCountry;

    public FragmentProfile(ProfileUpdated profileUpdated) {
        this.profileUpdated = profileUpdated;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        txtName = rootView.findViewById(R.id.txtName);
        txtAddressLine1 = rootView.findViewById(R.id.txt_addressline1);
        txtAddressLine2 = rootView.findViewById(R.id.txt_addressline2);
        txtCity = rootView.findViewById(R.id.txtCity);
        txtCountry = rootView.findViewById(R.id.txt_country);

        //txtName.setText(StaticDatas.userBasicData.get("Name"));
        txtName.setText(userProfileData.get("Firstname")+" "+userProfileData.get("Lastname"));
        txtAddressLine1.setText(userProfileData.get("AddressLine1"));
        txtAddressLine2.setText(userProfileData.get("AddressLine2"));
        txtCity.setText(userProfileData.get("City")+", "+userProfileData.get("State")+" - "+userProfileData.get("Zipcode"));
        txtCountry.setText(userProfileData.get("Country"));

        ImageView imgProfile = rootView.findViewById(R.id.imgProfile);

        try {
            Glide.with(getContext()).load(hostURL+userBasicData.get("Photo").substring(3)).signature(new ObjectKey(userProfileData.get("DateModified"))).placeholder(R.drawable.profile).into(imgProfile);
        }
        catch (Exception e){
            Log.e(TAG, "onCreateView: ",e );
        }

        rootView.findViewById(R.id.layoutProfileDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), ProfileDetailsActivity.class);
                startActivity(intent);
            }
        });

        rootView.findViewById(R.id.layoutTestinomial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), TestinomialSaveActivity.class);
                startActivity(intent);
            }
        });

        rootView.findViewById(R.id.linearManageBank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ManageBankActivity.class));
            }
        });

        rootView.findViewById(R.id.linearChangePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });

        rootView.findViewById(R.id.linear_LoginTiming).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginTimingActivity.class));
            }
        });

        rootView.findViewById(R.id.linearChangeTransPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangeTransactionPasswordActivity.class));
            }
        });

        rootView.findViewById(R.id.linear_Feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddReviewActivity.class));
            }
        });

        rootView.findViewById(R.id.kYClayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), KYCDeatilsActivity.class));
            }
        });

        rootView.findViewById(R.id.linearKYCApplication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), KYCApplicationActivity.class));
            }
        });

        rootView.findViewById(R.id.addresslayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddressListActivity.class));
            }
        });

        return rootView;
    }
}

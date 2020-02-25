package com.retail.biocare.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.retail.biocare.MainActivity;
import com.retail.biocare.R;
import com.retail.biocare.StaticData.StaticDatas;
import com.retail.biocare.adapter.DashboardAdapter;
import com.retail.biocare.model.DashboardModel;
import com.retail.biocare.utils.ColorArray;
import com.retail.biocare.utils.ExtractfromReply;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.retail.biocare.StaticData.StaticDatas.dashboadrdLoaded;
import static com.retail.biocare.StaticData.StaticDatas.dashboardMap;
import static com.retail.biocare.StaticData.StaticDatas.hostURL;
import static com.retail.biocare.StaticData.StaticDatas.userBasicData;
import static com.retail.biocare.StaticData.StaticDatas.userProfileData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String TAG = "DashboardFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected final String[] parties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    RecyclerView recyclerView;
    List<DashboardModel> dashboardModels = new ArrayList<>();
    DashboardAdapter dashboardAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PieChart chart;

    private TextView txtName, txtuserCode, txtBalance, txtTotalEarning, txtDirectIncome, txtBinaryIncome, txtLevelIncome, txttotalWithdrawls;
    private TextView txtFundTrasnfered, txtFundReceived, txtTotalOrders, txtNewOrders, txtCompletedOrders, txtNotification;
    private TextView txtUsedEpins, txtUnusedEpins, txtKyc, txtMessageSent, txtInbox;

    private ImageView imgProfile, imgKyc;

    private boolean shouldAsync = false;

    public DashboardFragment() {
        // Required empty public constructor
    }


    public DashboardFragment(boolean state) {

        shouldAsync = state;

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        chart = view.findViewById(R.id.chart1);
        recyclerView = view.findViewById(R.id.recyclerview);

        txtName = view.findViewById(R.id.txtName);
        txtuserCode = view.findViewById(R.id.txtuserCode);
        imgProfile = view.findViewById(R.id.imgProfile);
        txtBalance = view.findViewById(R.id.txtBalance);
        txtTotalEarning = view.findViewById(R.id.txtTotalEarning);
        txtDirectIncome = view.findViewById(R.id.txtDirectIncome);
        txtBinaryIncome = view.findViewById(R.id.txtBinaryIncome);
        txtLevelIncome = view.findViewById(R.id.txtLevelIncome);
        txttotalWithdrawls = view.findViewById(R.id.txttotalWithdrawls);
        txtFundTrasnfered = view.findViewById(R.id.txtFundTrasnfered);
        txtFundReceived = view.findViewById(R.id.txtFundReceived);
        txtTotalOrders = view.findViewById(R.id.txtTotalOrders);
        txtNewOrders = view.findViewById(R.id.txtNewOrders);
        txtCompletedOrders = view.findViewById(R.id.txtCompletedOrders);
        txtNotification = view.findViewById(R.id.txtNotification);
        txtUsedEpins = view.findViewById(R.id.txtUsedEpins);
        txtUnusedEpins = view.findViewById(R.id.txtUnusedEpins);
        txtKyc = view.findViewById(R.id.txtKyc);
        txtMessageSent = view.findViewById(R.id.txtMessageSent);
        txtInbox = view.findViewById(R.id.txtInbox);
        imgKyc = view.findViewById(R.id.imgKyc);

       // txtNotification.setSelected(true);

        if (dashboadrdLoaded) {
            txtNotification.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            txtNotification.setText(dashboardMap.get("Notification"));
            txtNotification.setSelected(true);
            txtNotification.setSingleLine(true);
            txtNotification.setSelected(true);
        }

        //txtTotBalance = view.findViewById(R.id.txtTotBalance);


        txtName.setText(userProfileData.get("Firstname"));
        txtuserCode.setText(StaticDatas.userBasicData.get("Username"));

        try {
            Glide.with(getContext()).load(hostURL + userBasicData.get("Photo").substring(3)).signature(new ObjectKey(userProfileData.get("DateModified"))).placeholder(R.drawable.blankprofile).into(imgProfile);
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: ", e);
        }

        //initChart();

        if (!dashboadrdLoaded && shouldAsync)
            new GetDashBoard().execute();

       /* RecyclerView recycler_TopPicks = view.findViewById(R.id.recycler_TopPicks);
        TopPicksRecyclerAdapter topPicksRecyclerAdapter = new TopPicksRecyclerAdapter(getContext());
        recycler_TopPicks.setAdapter(topPicksRecyclerAdapter);
        recycler_TopPicks.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        RecyclerView recycler_popularBrands = view.findViewById(R.id.recycler_popularBrands);
        PopularBrandsRecyclerAdapter popularBrandsRecyclerAdapter = new PopularBrandsRecyclerAdapter(getContext());
        recycler_popularBrands.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recycler_popularBrands.setAdapter(popularBrandsRecyclerAdapter);

        RecyclerView recyclerNewlyOpened = view.findViewById(R.id.recyclerNewlyOpened);
        NewlyOpenedAdapter newlyOpenedAdapter = new NewlyOpenedAdapter(getContext());
        recyclerNewlyOpened.setAdapter(newlyOpenedAdapter);
        recyclerNewlyOpened.setLayoutManager(new LinearLayoutManager(getContext()));*/


        return view;
    }


    void initChart() {
        chart.setUsePercentValues(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        //   chart.setOnChartValueSelectedListener(getContext());
        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

       /* Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);*/

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
        chart.setDrawEntryLabels(false);

        // entry label styling
        //    chart.setEntryLabelColor(Color.WHITE);
        //  chart.setEntryLabelTextSize(12f);
        setData(3, 10);

        dashboardModels.add(new DashboardModel(R.drawable.totadmin, "1", "Total\n Admin"));
        dashboardModels.add(new DashboardModel(R.drawable.todaymembers, "1000", "Today Members"));
        dashboardModels.add(new DashboardModel(R.drawable.totmembers, "1001", "Total Members"));
        dashboardModels.add(new DashboardModel(R.drawable.withdraw, "5000", "Total Withdraw"));
        dashboardModels.add(new DashboardModel(R.drawable.released, "100", "Fund Released"));
        dashboardModels.add(new DashboardModel(R.drawable.requested, "1000", "Fund Requested"));
        dashboardModels.add(new DashboardModel(R.drawable.sent, "150", "Total Sent Mail"));
        dashboardModels.add(new DashboardModel(R.drawable.readmail, "1500", "Total Read Mail"));
        dashboardModels.add(new DashboardModel(R.drawable.totalmail, "5600", "Total\n Mail"));
        dashboardModels.add(new DashboardModel(R.drawable.usedepin, "13", "Total Used Epin"));
        dashboardModels.add(new DashboardModel(R.drawable.unusedepin, "10", "Total Unused Epin"));
        dashboardModels.add(new DashboardModel(R.drawable.totadmin, "100", "Total\nEpins"));
    }

    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        /*for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length],
                    getResources().getDrawable(R.drawable.home_menu)));
        }*/

       /* entries.add(new PieEntry(Float.parseFloat(dashboardMap.get("TotalOrders"))));
        entries.add(new PieEntry(Float.parseFloat(dashboardMap.get("NewOrders"))));
        entries.add(new PieEntry(Float.parseFloat(dashboardMap.get("CompletedOrders"))));*/


        if (dashboardMap.get("TotalOrders").equalsIgnoreCase("0") && dashboardMap.get("NewOrders").equalsIgnoreCase("0") && dashboardMap.get("CompletedOrders").equalsIgnoreCase("0")) {
            entries.add(new PieEntry(Float.parseFloat("0")));
            entries.add(new PieEntry(Float.parseFloat("2")));
            entries.add(new PieEntry(Float.parseFloat("0")));
        }

        else {
            entries.add(new PieEntry(Float.parseFloat(dashboardMap.get("TotalOrders"))));
            entries.add(new PieEntry(Float.parseFloat(dashboardMap.get("NewOrders"))));
            entries.add(new PieEntry(Float.parseFloat(dashboardMap.get("CompletedOrders"))));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();


        for (int c : ColorArray.LIBERTY_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setDrawValues(false);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }


    /**
     * Notification that the progress level has changed. Clients can use the fromUser parameter
     * to distinguish user-initiated changes from those that occurred programmatically.
     *
     * @param seekBar  The SeekBar whose progress has changed
     * @param progress The current progress level. This will be in the range min..max where min
     * @param fromUser True if the progress change was initiated by the user.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    /**
     * Notification that the user has started a touch gesture. Clients may want to use this
     * to disable advancing the seekbar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Notification that the user has finished a touch gesture. Clients may want to use this
     * to re-enable advancing the seekbar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Called when a value has been selected inside the chart.
     *
     * @param e The selected Entry
     * @param h The corresponding highlight object that contains information
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    /**
     * Called when nothing has been selected or an "un-select" has been made.
     */
    @Override
    public void onNothingSelected() {

    }

/*    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Total Admin\n5000");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }*/

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Total Orders\n" + dashboardMap.get("TotalOrders"));
        s.setSpan(new RelativeSizeSpan(.99f), 0, 12, 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 12, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 12, s.length(), 0);
        return s;
    }


    private class GetDashBoard extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(getContext());


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (!s.equalsIgnoreCase("NODATA")) {
                dashboadrdLoaded = true;

                try {
                    JSONArray jsonArray = new JSONArray(s);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    dashboardMap.put("TotalEarnings", jsonObject.getString("TotalEarnings"));
                    dashboardMap.put("AvailableBalance", jsonObject.getString("AvailableBalance"));
                    dashboardMap.put("DirectIncome", jsonObject.getString("DirectIncome"));
                    dashboardMap.put("LevelIncome", jsonObject.getString("LevelIncome"));
                    dashboardMap.put("BinaryIncome", jsonObject.getString("BinaryIncome"));
                    dashboardMap.put("Notification", jsonObject.getString("Notification"));
                    dashboardMap.put("Kyc", jsonObject.getString("Kyc"));
                    dashboardMap.put("UnusedEpins", jsonObject.getString("UnusedEpins"));
                    dashboardMap.put("UsedEpins", jsonObject.getString("UsedEpins"));
                    dashboardMap.put("TransferedEpins", jsonObject.getString("TransferedEpins"));
                    dashboardMap.put("ReceivedEpins", jsonObject.getString("ReceivedEpins"));
                    dashboardMap.put("TotalWithdrawals", jsonObject.getString("TotalWithdrawals"));
                    dashboardMap.put("FundTrasnfered", jsonObject.getString("FundTrasnfered"));
                    dashboardMap.put("FundReceived", jsonObject.getString("FundReceived"));
                    dashboardMap.put("MessageSent", jsonObject.getString("MessageSent"));
                    dashboardMap.put("Inbox", jsonObject.getString("Inbox"));
                    dashboardMap.put("TotalOrders", jsonObject.getString("TotalOrders"));
                    dashboardMap.put("NewOrders", jsonObject.getString("NewOrders"));
                    dashboardMap.put("CompletedOrders", jsonObject.getString("CompletedOrders"));


                    txtBalance.setText(userBasicData.get("Currency") + jsonObject.getString("AvailableBalance"));
                    txtTotalEarning.setText(userBasicData.get("Currency") + jsonObject.getString("TotalEarnings"));
                    txtDirectIncome.setText(userBasicData.get("Currency") + jsonObject.getString("DirectIncome"));
                    txtBinaryIncome.setText(userBasicData.get("Currency") + jsonObject.getString("BinaryIncome"));
                    txtLevelIncome.setText(userBasicData.get("Currency") + jsonObject.getString("LevelIncome"));
                    txttotalWithdrawls.setText(userBasicData.get("Currency") + jsonObject.getString("TotalWithdrawals"));
                    txtFundTrasnfered.setText(userBasicData.get("Currency") + jsonObject.getString("FundTrasnfered"));
                    txtFundReceived.setText(userBasicData.get("Currency") + jsonObject.getString("FundReceived"));
                    txtTotalOrders.setText(jsonObject.getString("TotalOrders"));
                    txtNewOrders.setText(jsonObject.getString("NewOrders"));
                    txtUsedEpins.setText("Used Epins="+jsonObject.getString("UsedEpins"));
                    txtCompletedOrders.setText(jsonObject.getString("CompletedOrders"));
                    txtUnusedEpins.setText("Unused Epins="+jsonObject.getString("UnusedEpins"));
                    txtKyc.setText(jsonObject.getString("Kyc").toUpperCase());

                    if (jsonObject.getString("Kyc").equalsIgnoreCase("nill")){

                        imgKyc.setImageResource(R.drawable.cautionpng);
                    }
                    else if (jsonObject.getString("Kyc").equalsIgnoreCase("pending")){
                        imgKyc.setImageResource(R.drawable.pending);

                    }
                    else if (jsonObject.getString("Kyc").equalsIgnoreCase("Approved")){

                        imgKyc.setImageResource(R.drawable.greentick);

                    }

                    txtMessageSent.setText(jsonObject.getString("MessageSent"));
                    txtInbox.setText(jsonObject.getString("Inbox"));

                   // txtNotification.setSele
                    txtNotification.setText(Html.fromHtml(jsonObject.getString("Notification"))+"                                         ");
                    txtNotification.setSelected(true);


                    initChart();

                } catch (Exception e) {
                    Log.e(TAG, "onPostExecute: ", e);
                }


            }

            Log.d(TAG, "onPostExecute: Response: " + s);

        }

        @Override
        protected String doInBackground(String... strings) {
            return new ExtractfromReply().performPost("WSMember", "GetMemberdashboardApp", "MemberId=" + userBasicData.get("UserID") + "&PageIndex=1");
        }
    }
}

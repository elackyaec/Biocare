package com.retail.biocare.StaticData;

import com.retail.biocare.Models.CartItemModelNew;
import com.retail.biocare.Models.CartItemsModels;
import com.retail.biocare.Models.OrderSummaryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StaticDatas {

    public static String hostURL = "http://biocare.ayydemo.com/";
    public static String appUUID = "35510b94-5476-11ea-a2e3-2e728ce88125";
    public static String loginPassword = "";


    public static Map<String, String> userBasicData = new HashMap<>();
    public static Map<String, String> userProfileData = new HashMap<>();
    public static Map<String, String> bankDetailsMap = new HashMap<>();

    public static Map<String, String> dashboardMap = new HashMap<>();


    public static boolean dashboadrdLoaded = false, isSecondTime=false;

    //Dashboard
    public static String AvailableBalance, TotalEarnings, DirectIncome, BinaryIncome, LevelIncome, TotalWithdrawals, FundTrasnfered, FundReceived;
    public static String TotalOrders, NewOrders, UsedEpins, CompletedOrders, UnusedEpins, Kyc="", MessageSent, Inbox, Notification="";


    //Cart Array
    public static ArrayList<CartItemsModels> cartDetails = new ArrayList<>();
    public static ArrayList<CartItemModelNew> cartDetailsNew = new ArrayList<>();
    public static ArrayList<String> addedItemIds = new ArrayList<>();

    //OrderSummary
    public static ArrayList<OrderSummaryModel> orderSummaryDetails = new ArrayList<>();
}

package com.retail.biocare.Models;

public class LoginTimingsModels {

    private String userCode, ipAddress, date;

    public LoginTimingsModels(String userCode, String ipAddress, String date) {
        this.userCode = userCode;
        this.ipAddress = ipAddress;
        this.date = date;
    }


    public String getUserCode() {
        return userCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getDate() {
        return date;
    }
}

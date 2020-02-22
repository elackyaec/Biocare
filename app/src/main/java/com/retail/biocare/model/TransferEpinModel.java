package com.retail.biocare.model;

public class TransferEpinModel {
    String pinid,pinno,userid,amt,pkgname,date,status,trasnferepin;

    public String getPinid() {
        return pinid;
    }

    public String getPinno() {
        return pinno;
    }

    public String getUserid() {
        return userid;
    }

    public String getAmt() {
        return amt;
    }

    public String getPkgname() {
        return pkgname;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getTrasnferepin() {
        return trasnferepin;
    }

    public TransferEpinModel(String pinid, String pinno, String userid, String amt, String pkgname, String date, String status, String trasnferepin) {
        this.pinid = pinid;
        this.pinno = pinno;
        this.userid = userid;
        this.amt = amt;
        this.pkgname = pkgname;
        this.date = date;
        this.status = status;
        this.trasnferepin = trasnferepin;
    }
}

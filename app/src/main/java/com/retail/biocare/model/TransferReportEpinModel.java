package com.retail.biocare.model;

public class TransferReportEpinModel {

    String pinid,pinno,pkgname,amt,from,to,date;

    public String getPinid() {
        return pinid;
    }

    public String getPinno() {
        return pinno;
    }

    public String getPkgname() {
        return pkgname;
    }

    public String getAmt() {
        return amt;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public TransferReportEpinModel(String pinid, String pinno, String pkgname, String amt, String from, String to, String date) {
        this.pinid = pinid;
        this.pinno = pinno;
        this.pkgname = pkgname;
        this.amt = amt;
        this.from = from;
        this.to = to;
        this.date = date;
    }
}

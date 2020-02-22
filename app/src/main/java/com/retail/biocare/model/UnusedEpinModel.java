package com.retail.biocare.model;

public class UnusedEpinModel {

    String id,pinno,amount,pkgname,date,status;

    public String getId() {
        return id;
    }

    public String getPinno() {
        return pinno;
    }

    public String getAmount() {
        return amount;
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

    public UnusedEpinModel(String id, String pinno, String amount,String pkgname, String date, String status) {
        this.id = id;
        this.pinno = pinno;
        this.amount = amount;
        this.pkgname = pkgname;
        this.date = date;
        this.status = status;
    }
}

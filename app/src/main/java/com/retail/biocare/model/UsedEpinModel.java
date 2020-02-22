package com.retail.biocare.model;

public class UsedEpinModel {

    String id,pinno,amount,usedname,pkgname,date,status;

    public String getId() {
        return id;
    }

    public String getPinno() {
        return pinno;
    }

    public String getAmount() {
        return amount;
    }

    public String getUsedname() {
        return usedname;
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

    public UsedEpinModel(String id, String pinno, String amount, String usedname, String pkgname, String date, String status) {
        this.id = id;
        this.pinno = pinno;
        this.amount = amount;
        this.usedname = usedname;
        this.pkgname = pkgname;
        this.date = date;
        this.status = status;
    }
}

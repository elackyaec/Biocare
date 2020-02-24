package com.retail.biocare.model;

public class PackageModel {
String amount,pkgnname,pkgid;

    public String getAmount() {
        return amount;
    }

    public String getPkgnname() {
        return pkgnname;
    }

    public String getPkgid() {
        return pkgid;
    }

    public PackageModel(String amount, String pkgnname, String pkgid) {
        this.amount = amount;
        this.pkgnname = pkgnname;
        this.pkgid = pkgid;
    }
}

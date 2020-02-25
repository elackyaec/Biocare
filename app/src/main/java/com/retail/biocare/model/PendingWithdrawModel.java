package com.retail.biocare.model;

public class PendingWithdrawModel {

    String username,fullname,status,date,amount;
String accname,accn0,bankname,branch,bankcode;

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getAccname() {
        return accname;
    }

    public String getAccn0() {
        return accn0;
    }

    public String getBankname() {
        return bankname;
    }

    public String getBranch() {
        return branch;
    }

    public String getBankcode() {
        return bankcode;
    }

    public PendingWithdrawModel(String username, String fullname, String status, String date, String amount, String accname, String accn0, String bankname, String branch, String bankcode) {
        this.username = username;
        this.fullname = fullname;
        this.status = status;
        this.date = date;
        this.amount = amount;
        this.accname = accname;
        this.accn0 = accn0;
        this.bankname = bankname;
        this.branch = branch;
        this.bankcode = bankcode;
    }
}

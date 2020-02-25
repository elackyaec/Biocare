package com.retail.biocare.model;

public class FundTransferHistoryModel {

    String username,fullname,credit,debit,message,date;

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getCredit() {
        return credit;
    }

    public String getDebit() {
        return debit;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public FundTransferHistoryModel(String username, String fullname, String credit, String debit, String message, String date) {
        this.username = username;
        this.fullname = fullname;
        this.credit = credit;
        this.debit = debit;
        this.message = message;
        this.date = date;
    }
}

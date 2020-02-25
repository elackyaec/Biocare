package com.retail.biocare.model;

public class PaymentHistoryModel {
    String username,fullname,amount,date,message;

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public PaymentHistoryModel(String username, String fullname, String amount, String date, String message) {
        this.username = username;
        this.fullname = fullname;
        this.amount = amount;
        this.date = date;
        this.message = message;
    }
}

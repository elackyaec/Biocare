package com.retail.biocare.Models;

public class PasswordModel {

    private String CustomerID, Password, TransactionPassword, MemberId;

    public PasswordModel(String customerID, String password, String transactionPassword, String memberId) {
        CustomerID = customerID;
        Password = password;
        TransactionPassword = transactionPassword;
        MemberId = memberId;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getPassword() {
        return Password;
    }

    public String getTransactionPassword() {
        return TransactionPassword;
    }

    public String getMemberId() {
        return MemberId;
    }
}

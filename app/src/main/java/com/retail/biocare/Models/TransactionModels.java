package com.retail.biocare.Models;

public class TransactionModels {

    private String CustomerID, TransactionPassword, MemberId;

    public TransactionModels(String customerID, String transactionPassword, String memberId) {
        CustomerID = customerID;
        TransactionPassword = transactionPassword;
        MemberId = memberId;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getTransactionPassword() {
        return TransactionPassword;
    }

    public String getMemberId() {
        return MemberId;
    }
}

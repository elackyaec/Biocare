package com.retail.biocare.model;

public class IncomeModel {
    String name,username,amount,tds,tax,netamt;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAmount() {
        return amount;
    }

    public String getTds() {
        return tds;
    }

    public String getTax() {
        return tax;
    }

    public String getNetamt() {
        return netamt;
    }

    public IncomeModel(String name, String username, String amount, String tds, String tax, String netamt) {
        this.name = name;
        this.username = username;
        this.amount = amount;
        this.tds = tds;
        this.tax = tax;
        this.netamt = netamt;
    }
}

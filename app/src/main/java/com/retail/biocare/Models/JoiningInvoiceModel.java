package com.retail.biocare.Models;

public class JoiningInvoiceModel {

    private String description, qty, price, total;

    public JoiningInvoiceModel(String description, String qty, String price, String total) {
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public String getQty() {
        return qty;
    }

    public String getPrice() {
        return price;
    }

    public String getTotal() {
        return total;
    }
}

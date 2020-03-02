package com.retail.biocare.Models;

public class OrderSummaryModel {
    private String itemName, itemQty;
    private Float itemTotal;

    public OrderSummaryModel(String itemName, String itemQty, Float itemTotal) {
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemTotal = itemTotal;
    }


    public String getItemName() {
        return itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public Float getItemTotal() {
        return itemTotal;
    }
}

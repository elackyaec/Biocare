package com.retail.biocare.Models;

public class OrderDetailModel {

    private String itenNane, itemQty, itemPrice, itemOption;

    public OrderDetailModel(String itenNane, String itemQty, String itemPrice, String itemOption) {
        this.itenNane = itenNane;
        this.itemQty = itemQty;
        this.itemPrice = itemPrice;
        this.itemOption = itemOption;
    }

    public String getItenNane() {
        return itenNane;
    }

    public String getItemQty() {
        return itemQty;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemOption() {
        return itemOption;
    }
}

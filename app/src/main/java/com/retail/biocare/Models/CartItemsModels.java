package com.retail.biocare.Models;

public class CartItemsModels {

        private String itemId, itemName, itemQty,  itemtxtPrice;
        private float itemPrice, itemTax, itemShipping;


    public CartItemsModels(String itemId, String itemName, String itemQty, String itemtxtPrice, float itemPrice, float itemTax, float itemShipping) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemtxtPrice = itemtxtPrice;
        this.itemPrice = itemPrice;
        this.itemTax = itemTax;
        this.itemShipping = itemShipping;
    }

    public float getItemTax() {
        return itemTax;
    }

    public float getItemShipping() {
        return itemShipping;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public String getItemtxtPrice() {
        return itemtxtPrice;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }
}

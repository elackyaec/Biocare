package com.retail.biocare.Models;

public class CheckoutCartModel {

    private String customerid, productid, color, size, price, quantity,totalamount,uniqueid,tax,shipcharges , paymentmethodId;

    public CheckoutCartModel(String customerid, String productid, String color, String size, String price, String quantity, String totalamount, String uniqueid, String tax, String shipcharges, String paymentmethodId) {
        this.customerid = customerid;
        this.productid = productid;
        this.color = color;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.totalamount = totalamount;
        this.uniqueid = uniqueid;
        this.tax = tax;
        this.shipcharges = shipcharges;
        this.paymentmethodId = paymentmethodId;
    }

    public String getCustomerid() {
        return customerid;
    }

    public String getProductid() {
        return productid;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public String getTax() {
        return tax;
    }

    public String getShipcharges() {
        return shipcharges;
    }

    public String getPaymentmethodId() {
        return paymentmethodId;
    }
}

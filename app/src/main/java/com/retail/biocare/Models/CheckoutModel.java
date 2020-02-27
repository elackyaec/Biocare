package com.retail.biocare.Models;

public class CheckoutModel {

    private String customerid, productid, color, size, price, quantity,totalamount,uniqueid,tax,shipcharges, billname, billphone, billemail, billadd1, billadd2, billcity, billstate, billcountry, billzipcode, shipname, shipphone, shipemail, shipadd1, shipadd2, shipcity, shipstate, shipcountry, shipzipcode, comments;

    public CheckoutModel(String customerid, String productid, String color, String size, String price, String quantity, String totalamount, String uniqueid, String tax, String shipcharges, String billname, String billphone, String billemail, String billadd1, String billadd2, String billcity, String billstate, String billcountry, String billzipcode, String shipname, String shipphone, String shipemail, String shipadd1, String shipadd2, String shipcity, String shipstate, String shipcountry, String shipzipcode, String comments) {
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
        this.billname = billname;
        this.billphone = billphone;
        this.billemail = billemail;
        this.billadd1 = billadd1;
        this.billadd2 = billadd2;
        this.billcity = billcity;
        this.billstate = billstate;
        this.billcountry = billcountry;
        this.billzipcode = billzipcode;
        this.shipname = shipname;
        this.shipphone = shipphone;
        this.shipemail = shipemail;
        this.shipadd1 = shipadd1;
        this.shipadd2 = shipadd2;
        this.shipcity = shipcity;
        this.shipstate = shipstate;
        this.shipcountry = shipcountry;
        this.shipzipcode = shipzipcode;
        this.comments = comments;
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

    public String getBillname() {
        return billname;
    }

    public String getBillphone() {
        return billphone;
    }

    public String getBillemail() {
        return billemail;
    }

    public String getBilladd1() {
        return billadd1;
    }

    public String getBilladd2() {
        return billadd2;
    }

    public String getBillcity() {
        return billcity;
    }

    public String getBillstate() {
        return billstate;
    }

    public String getBillcountry() {
        return billcountry;
    }

    public String getBillzipcode() {
        return billzipcode;
    }

    public String getShipname() {
        return shipname;
    }

    public String getShipphone() {
        return shipphone;
    }

    public String getShipemail() {
        return shipemail;
    }

    public String getShipadd1() {
        return shipadd1;
    }

    public String getShipadd2() {
        return shipadd2;
    }

    public String getShipcity() {
        return shipcity;
    }

    public String getShipstate() {
        return shipstate;
    }

    public String getShipcountry() {
        return shipcountry;
    }

    public String getShipzipcode() {
        return shipzipcode;
    }

    public String getComments() {
        return comments;
    }
}

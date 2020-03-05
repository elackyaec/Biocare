package com.retail.biocare.Models;

public class CheckoutOrderModel {

    /*

     cmd.Parameters.AddWithValue("@customerid ", Convert.ToInt32(dtOrder.Rows[0]["customerid"]));
            cmd.Parameters.AddWithValue("@uniqueid", Convert.ToString(dtOrder.Rows[0]["uniqueid"]));
            cmd.Parameters.AddWithValue("@billname", Convert.ToString(dtOrder.Rows[0]["billname"]));
            cmd.Parameters.AddWithValue("@billphone", Convert.ToString(dtOrder.Rows[0]["billphone"]));
            cmd.Parameters.AddWithValue("@billemail", Convert.ToString(dtOrder.Rows[0]["billemail"]));
            cmd.Parameters.AddWithValue("@billadd1", Convert.ToString(dtOrder.Rows[0]["billadd1"]));
            cmd.Parameters.AddWithValue("@billadd2", Convert.ToString(dtOrder.Rows[0]["billadd2"]));
            cmd.Parameters.AddWithValue("@billcity", Convert.ToString(dtOrder.Rows[0]["billcity"]));
            cmd.Parameters.AddWithValue("@billstate", Convert.ToString(dtOrder.Rows[0]["billstate"]));
            cmd.Parameters.AddWithValue("@billcountry", Convert.ToString(dtOrder.Rows[0]["billcountry"]));
            cmd.Parameters.AddWithValue("@billzipcode", Convert.ToString(dtOrder.Rows[0]["billzipcode"]));
            cmd.Parameters.AddWithValue("@shipname", Convert.ToString(dtOrder.Rows[0]["shipname"]));
            cmd.Parameters.AddWithValue("@shipphone", Convert.ToString(dtOrder.Rows[0]["shipphone"]));
            cmd.Parameters.AddWithValue("@shipemail", Convert.ToString(dtOrder.Rows[0]["shipemail"]));
            cmd.Parameters.AddWithValue("@shipadd1", Convert.ToString(dtOrder.Rows[0]["shipadd1"]));
            cmd.Parameters.AddWithValue("@shipadd2", Convert.ToString(dtOrder.Rows[0]["shipadd2"]));
            cmd.Parameters.AddWithValue("@shipcity", Convert.ToString(dtOrder.Rows[0]["shipcity"]));
            cmd.Parameters.AddWithValue("@shipstate", Convert.ToString(dtOrder.Rows[0]["shipstate"]));
            cmd.Parameters.AddWithValue("@shipcountry", Convert.ToString(dtOrder.Rows[0]["shipcountry"]));
            cmd.Parameters.AddWithValue("@shipzipcode", Convert.ToString(dtOrder.Rows[0]["shipzipcode"]));
            cmd.Parameters.AddWithValue("@comments", Convert.ToString(dtOrder.Rows[0]["comments"]));
            cmd.Parameters.AddWithValue("@paymentmethod", Convert.ToString(dtOrder.Rows[0]["paymentmethod"]));

     */


    private String customerid, uniqueid, billname, billphone, billemail, billadd1, billadd2, billcity, billstate, billcountry, billzipcode, shipname, shipphone, shipemail, shipadd1, shipadd2, shipcity, shipstate, shipcountry, shipzipcode, comments, paymentmethod;

    public CheckoutOrderModel(String customerid, String uniqueid, String billname, String billphone, String billemail, String billadd1, String billadd2, String billcity, String billstate, String billcountry, String billzipcode, String shipname, String shipphone, String shipemail, String shipadd1, String shipadd2, String shipcity, String shipstate, String shipcountry, String shipzipcode, String comments, String paymentmethod) {
        this.customerid = customerid;
        this.uniqueid = uniqueid;
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
        this.paymentmethod = paymentmethod;
    }

    public String getCustomerid() {
        return customerid;
    }

    public String getUniqueid() {
        return uniqueid;
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

    public String getPaymentmethod() {
        return paymentmethod;
    }
}

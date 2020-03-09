package com.retail.biocare.Models;
/*
cmd.Parameters.AddWithValue("@orderid", Convert.ToInt32(orderid));
        cmd.Parameters.AddWithValue("@customerid ", Convert.ToInt32(dtDetails.Rows[0]["customerid"]));
        cmd.Parameters.AddWithValue("@productid", Convert.ToInt32(dtDetails.Rows[0]["productid"]));
        cmd.Parameters.AddWithValue("@color", Convert.ToString(dtDetails.Rows[0]["color"]));
        cmd.Parameters.AddWithValue("@price", Convert.ToDecimal(dtDetails.Rows[0]["price"]));
        cmd.Parameters.AddWithValue("@quantity", Convert.ToInt32(dtDetails.Rows[0]["quantity"]));
        cmd.Parameters.AddWithValue("@size", Convert.ToString(dtDetails.Rows[0]["size"]));
        cmd.Parameters.AddWithValue("@totalamount", Convert.ToDecimal(dtDetails.Rows[0]["totalamount"]));
        cmd.Parameters.AddWithValue("@tax", Convert.ToDecimal(dtDetails.Rows[0]["tax"]));
        cmd.Parameters.AddWithValue("@shipcharges", Convert.ToDecimal(dtDetails.Rows[0]["shipcharges"]));
 */
public class CartItemModelNew {

    private String  customerid, productid, color, quantity,  size;
    private float totalamount, price, tax, shipcharges;

    public CartItemModelNew(String customerid, String productid, String color, String quantity, String size, float totalamount, float price, float tax, float shipcharges) {
        this.customerid = customerid;
        this.productid = productid;
        this.color = color;
        this.quantity = quantity;
        this.size = size;
        this.totalamount = totalamount;
        this.price = price;
        this.tax = tax;
        this.shipcharges = shipcharges;
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

    public String getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }

    public float getTotalamount() {
        return totalamount;
    }

    public float getPrice() {
        return price;
    }

    public float getTax() {
        return tax;
    }

    public float getShipcharges() {
        return shipcharges;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setTotalamount(float totalamount) {
        this.totalamount = totalamount;
    }

}

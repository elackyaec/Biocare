package com.retail.biocare.Models;

public class CategoryItemModels {

    private String slNo, itemId ,itemName, itemImage, itemOffer, itemPrice, itemMRP, itemRating, shippingCharge , itemTax, itemAvailableQuantity;
    private String costprice, itemDescription, itemSize, itemColor, mrpprice1, shipcharges1, categoryId, subCategoryId;


    public CategoryItemModels(String slNo, String itemId, String itemName, String itemImage, String itemOffer, String itemPrice, String itemMRP, String itemRating, String shippingCharge, String itemTax, String itemAvailableQuantity, String costprice, String itemDescription, String itemSize, String itemColor, String mrpprice1, String shipcharges1, String categoryId, String subCategoryId) {
        this.slNo = slNo;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemOffer = itemOffer;
        this.itemPrice = itemPrice;
        this.itemMRP = itemMRP;
        this.itemRating = itemRating;
        this.shippingCharge = shippingCharge;
        this.itemTax = itemTax;
        this.itemAvailableQuantity = itemAvailableQuantity;
        this.costprice = costprice;
        this.itemDescription = itemDescription;
        this.itemSize = itemSize;
        this.itemColor = itemColor;
        this.mrpprice1 = mrpprice1;
        this.shipcharges1 = shipcharges1;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }


    public String getSlNo() {
        return slNo;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getItemOffer() {
        return itemOffer;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemMRP() {
        return itemMRP;
    }

    public String getItemRating() {
        return itemRating;
    }

    public String getShippingCharge() {
        return shippingCharge;
    }

    public String getItemTax() {
        return itemTax;
    }

    public String getItemAvailableQuantity() {
        return itemAvailableQuantity;
    }

    public String getCostprice() {
        return costprice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemSize() {
        return itemSize;
    }

    public String getItemColor() {
        return itemColor;
    }

    public String getMrpprice1() {
        return mrpprice1;
    }

    public String getShipcharges1() {
        return shipcharges1;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }
}

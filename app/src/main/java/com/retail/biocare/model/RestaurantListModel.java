package com.retail.biocare.model;

public class RestaurantListModel {

    String name,category,deliverytime;
    String phone,address,reviews,street,city,zip,state,currency;
    String image;
    int rest_id;
    float rating;

    String openTime,closeTime;


    public String getPhone() {
        return phone;
    }


    public int getRest_id() {
        return rest_id;
    }

    public String getCurrency() {
        return currency;
    }

    public RestaurantListModel(String name, String image, String phone, String category, String deliverytime, String address, String reviews, float rating, int rest_id, String state, String street, String city, String zip, String currency, String openTime, String closeTime) {
        this.name = name;
        this.image = image;
        this.phone=phone;
        this.category=category;
        this.deliverytime=deliverytime;
        this.address=address;
        this.reviews=reviews;
        this.rating=rating;
        this.state=state;
        this.street=street;
        this.zip=zip;
        this.city=city;
        this.currency=currency;
        this.openTime=openTime;
        this.closeTime = closeTime;



    }


    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public String getAddress() {
        return address;
    }

    public String getReviews() {
        return reviews;
    }

    public float getRating() {
        return rating;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }
}

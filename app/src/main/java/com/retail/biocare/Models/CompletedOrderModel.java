package com.retail.biocare.Models;

public class CompletedOrderModel {

    private String orderId, userName, userId, userAddress, orderDate, orderStatus, itemCount, paymentType, orderAmount;

    public CompletedOrderModel(String orderId, String userName, String userId, String userAddress, String orderDate, String orderStatus, String itemCount, String paymentType, String orderAmount) {
        this.orderId = orderId;
        this.userName = userName;
        this.userId = userId;
        this.userAddress = userAddress;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.itemCount = itemCount;
        this.paymentType = paymentType;
        this.orderAmount = orderAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getItemCount() {
        return itemCount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getOrderAmount() {
        return orderAmount;
    }
}

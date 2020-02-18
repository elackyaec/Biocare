package com.retail.biocare.model;

public class DashboardModel {
    int image;
    String no,name;

    public int getImage() {
        return image;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public DashboardModel(int image, String no, String name) {
        this.image = image;
        this.no = no;
        this.name = name;
    }
}

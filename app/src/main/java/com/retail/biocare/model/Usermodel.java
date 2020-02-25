package com.retail.biocare.model;

public class Usermodel {
    String cname,name,id;

    public String getCname() {
        return cname;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Usermodel(String cname, String name, String id) {
        this.cname = cname;
        this.name = name;
        this.id = id;
    }
}

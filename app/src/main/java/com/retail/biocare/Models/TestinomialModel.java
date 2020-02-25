package com.retail.biocare.Models;

public class TestinomialModel {

    private String id, title, description, KeyValue, MemberId;


    public TestinomialModel(String id, String title, String description, String keyValue, String memberId) {
        this.id = id;
        this.title = title;
        this.description = description;
        KeyValue = keyValue;
        MemberId = memberId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyValue() {
        return KeyValue;
    }

    public String getMemberId() {
        return MemberId;
    }
}

package com.retail.biocare.Models;

public class MessageModel {

    String date,subject, message,name,msgid;


    public MessageModel(String date, String subject, String message, String name, String msgid) {
        this.date = date;
        this.subject = subject;
        this.message = message;
        this.name = name;
        this.msgid = msgid;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getMsgid() {
        return msgid;
    }
}

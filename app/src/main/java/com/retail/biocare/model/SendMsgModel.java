package com.retail.biocare.model;

public class SendMsgModel {
    String ID,senderid,receiverid,subject,message,UserId;

    public String getID() {
        return ID;
    }

    public String getSenderid() {
        return senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return UserId;
    }

    public SendMsgModel(String ID, String senderid, String receiverid, String subject, String message, String userId) {
        this.ID = ID;
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.subject = subject;
        this.message = message;
        UserId = userId;
    }
}

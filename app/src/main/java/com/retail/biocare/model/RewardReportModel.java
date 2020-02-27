package com.retail.biocare.model;

public class RewardReportModel {
    String userid,username,rewardname,amount,join,status;

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getRewardname() {
        return rewardname;
    }

    public String getAmount() {
        return amount;
    }

    public String getJoin() {
        return join;
    }

    public String getStatus() {
        return status;
    }

    public RewardReportModel(String userid, String username, String rewardname, String amount, String join, String status) {
        this.userid = userid;
        this.username = username;
        this.rewardname = rewardname;
        this.amount = amount;
        this.join = join;
        this.status = status;
    }
}

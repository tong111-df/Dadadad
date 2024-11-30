package com.example.test.entity;

public class lotteryInfo {
    private int lottery_id;
    private String username;
    private int dacnt;
    private int shicnt;
    private int zuicnt;
    private int bangcnt;
    private int decnt;

    public static lotteryInfo slotteryInfo;

    public static lotteryInfo getSlotteryInfo() {
        return slotteryInfo;
    }

    public static void setSlotteryInfo(lotteryInfo slotteryInfo) {
        lotteryInfo.slotteryInfo = slotteryInfo;
    }

    public lotteryInfo(int lottery_id, String username, int dacnt, int shicnt, int zuicnt, int bangcnt, int decnt) {
        this.lottery_id = lottery_id;
        this.username=username;
        this.dacnt=dacnt;
        this.shicnt=shicnt;
        this.zuicnt=zuicnt;
        this.bangcnt=bangcnt;
        this.decnt=decnt;
    }

    public int getDecnt() {
        return decnt;
    }

    public void setDecnt(int decnt) {
        this.decnt = decnt;
    }

    public int getBangcnt() {
        return bangcnt;
    }

    public void setBangcnt(int bangcnt) {
        this.bangcnt = bangcnt;
    }

    public int getZuicnt() {
        return zuicnt;
    }

    public void setZuicnt(int zuicnt) {
        this.zuicnt = zuicnt;
    }

    public int getShicnt() {
        return shicnt;
    }

    public void setShicnt(int shicnt) {
        this.shicnt = shicnt;
    }

    public int getDacnt() {
        return dacnt;
    }

    public void setDacnt(int dacnt) {
        this.dacnt = dacnt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLottery_id() {
        return lottery_id;
    }

    public void setLottery_id(int lottery_id) {
        this.lottery_id = lottery_id;
    }
}

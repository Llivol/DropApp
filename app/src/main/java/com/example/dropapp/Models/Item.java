package com.example.dropapp.Models;

public class Item {

    private long amount;
    private long amountD;
    private float discPrice;
    private String item;
    private int pointCost;
    private float price;

    public Item(String item, long amount, long amountD, float price, float discPrice, int pointCost) {
        this.amount = amount;
        this.amountD = amountD;
        this.discPrice = discPrice;
        this.item = item;
        this.pointCost = pointCost;
        this.price = price;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmountD() {
        return amountD;
    }

    public void setAmountD(long amountD) {
        this.amountD = amountD;
    }

    public float getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(float discPrice) {
        this.discPrice = discPrice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPointCost() {
        return pointCost;
    }

    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getAcummulatedAmount() {
        return this.amount + this.amountD;
    }
}

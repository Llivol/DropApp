package com.example.dropapp.Models;

import java.util.List;

public class Order {

    private int id;
    private String pointsSpent;
    private List<Item> details;

    public Order(int id, String pointsSpent, List<Item> details) {
        this.id = id;
        this.pointsSpent = pointsSpent;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointsSpent() {
        return pointsSpent;
    }

    public void setPointsSpent(String pointsSpent) {
        this.pointsSpent = pointsSpent;
    }

    public List<Item> getDetails() {
        return details;
    }

    public void setDetails(List<Item> details) {
        this.details = details;
    }
}

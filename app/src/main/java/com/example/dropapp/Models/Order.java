package com.example.dropapp.Models;

import com.example.dropapp.R;

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

    public List<Item> getItems() {
        return details;
    }

    public void setItems(List<Item> details) {
        this.details = details;
    }

    public String itemsToString() {

        String itemsToString = "";

        // TODO: Passar això al fitxer d'strings
        if (details.size() == 0) return "No s'ha demanat res!";

        for (Item item :
                this.details) {

            if (item.getAmount() > 0) {

                itemsToString += (String.format("%s (x%s), ", item.getItem(), Long.toString(item.getAmount())));
            }

            if (item.getAmountD() > 0) {

                itemsToString +=(String.format("%s DROP (x%s), ", item.getItem(), Long.toString(item.getAmountD())));
            }
        }

        if ( itemsToString.length() != 0 ) itemsToString = itemsToString.substring(0, itemsToString.length() - 2);

        return itemsToString;
    }
}

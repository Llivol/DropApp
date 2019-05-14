package com.example.dropapp;

import android.app.Application;

import com.example.dropapp.Models.Order;
import com.example.dropapp.Models.Table;
import com.example.dropapp.Models.Item;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private boolean isFirstTime;
    private ArrayList<Table> tables;
    private ArrayList<Item> items;
    private ArrayList<Order> orders;

    @Override
    public void onCreate() {

        isFirstTime = true;

        tables = new ArrayList<>();
        tables.add(new Table(1, 700, "Vol realitzar comanda"));
        tables.add(new Table(2, 1, "Vol realitzar comanda"));
        tables.add(new Table(3, 100, "Vol realitzar comanda"));
        tables.add(new Table(4, 300, "Vol realitzar comanda"));
        tables.add(new Table(5, 888888, "Vol realitzar comanda"));
        tables.add(new Table(88, 700, "Vol realitzar comanda"));
        tables.add(new Table(12345, 1300, "Vol realitzar comanda"));
        tables.add(new Table(888888888, 0, "Vol realitzar comanda"));

        items = new ArrayList<>();
        items.add(new Item("Cervesa", 0, 0, 1.0f, 0.5f, 100));
        items.add(new Item("Birra", 0, 0, 1.0f, 0.5f, 100));
        items.add(new Item("Caña", 0, 0, 1.0f, 0.5f, 100));
        items.add(new Item("Jarra", 0, 0, 1.0f, 0.5f, 100));
        items.add(new Item("Rubia", 0, 0, 1.0f, 0.5f, 100));
        items.add(new Item("Espumosa", 0, 0, 1.0f, 0.5f, 100));

        orders = new ArrayList<>();
        ArrayList<Item> aux1 = new ArrayList<>();
        aux1.add(new Item("Cervesa", 2, 1, 1.0f, 0.5f, 100));
        aux1.add(new Item("Braves", 1, 0, 1.0f, 0.5f, 100));
        orders.add(new Order(1, "100", aux1));

        ArrayList<Item> aux2 = new ArrayList<>();
        aux2.add(new Item("Sandwitch", 2, 0, 1.0f, 0.5f, 100));
        aux2.add(new Item("Aigua", 1, 0, 1.0f, 0.5f, 100));
        orders.add(new Order(1, "100", aux2));

        ArrayList<Item> aux3 = new ArrayList<>();
        aux3.add(new Item("Coca-Cola", 5, 0, 1.0f, 0.5f, 100));
        orders.add(new Order(1, "100", aux3));

        super.onCreate();
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<Item> getItems() {return items;}
}

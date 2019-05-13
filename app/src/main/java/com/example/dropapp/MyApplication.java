package com.example.dropapp;

import android.app.Application;

import com.example.dropapp.Models.Table;

import java.util.ArrayList;

public class MyApplication extends Application {

    private boolean isFirstTime;
    private ArrayList<Table> tables;

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
}

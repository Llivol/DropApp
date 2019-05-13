package com.example.dropapp;

import android.app.Application;

public class MyApplication extends Application {

    private boolean isFirstTime;

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }

    @Override
    public void onCreate() {

        isFirstTime = true;

        super.onCreate();
    }
}

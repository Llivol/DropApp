package com.example.dropapp.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.dropapp.Listeners.*;

import com.example.dropapp.MyApplication;
import com.example.dropapp.R;

public class BaseActivity extends AppCompatActivity {

    public MyApplication getMyApp() {

        return (MyApplication) getApplication();
    }

    public void setCustomActionBar (boolean back, int title) {

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        if (back) getSupportActionBar().setCustomView(R.layout.action_bar_back);
        else getSupportActionBar().setCustomView(R.layout.action_bar_forward);

        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        TextView titleTextView = findViewById(R.id.tv_title);
        titleTextView.setText(title);
    }

    public void setCustomActionBar (boolean back, String title) {

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        if (back) getSupportActionBar().setCustomView(R.layout.action_bar_back);
        else getSupportActionBar().setCustomView(R.layout.action_bar_forward);

        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        TextView titleTextView = findViewById(R.id.tv_title);
        titleTextView.setText(title);
    }

}

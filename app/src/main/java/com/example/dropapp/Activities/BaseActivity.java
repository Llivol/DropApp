package com.example.dropapp.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.dropapp.Listeners.*;

import com.example.dropapp.MyApplication;
import com.example.dropapp.R;

public class BaseActivity extends AppCompatActivity {

    public MyApplication getMyApp() {

        return (MyApplication) getApplication();
    }

}

package com.example.dropapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.dropapp.Listeners.OnSwipeTouchListener;
import com.example.dropapp.R;

public class TableListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        if (getMyApp().isFirstTime()) firstLaunch();
        else overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.bg_table_list).setOnTouchListener(new OnSwipeTouchListener(TableListActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(TableListActivity.this, "Nothing to do here", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(TableListActivity.this, "Nothing to do here", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Intent intent = new Intent(TableListActivity.this, OrderListActivity.class);
                finish();
                startActivity(intent);
            }
            public void onSwipeBottom() {
                Toast.makeText(TableListActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void firstLaunch() {

        getMyApp().setFirstTime(false);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}

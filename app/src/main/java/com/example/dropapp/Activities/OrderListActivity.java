package com.example.dropapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropapp.Listeners.OnSwipeTouchListener;
import com.example.dropapp.R;

public class OrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        setTitle(R.string.title_activity_order_list);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_order_list);
        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        TextView titleTextView = findViewById(R.id.tv_title);
        titleTextView.setText(R.string.title_activity_order_list);

        // Adapters

        // Listeners

        findViewById(R.id.btn_swipe_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderListActivity.this, TableListActivity.class);
                finish();
                startActivity(intent);
            }
        });

        findViewById(R.id.bg_order_list).setOnTouchListener(new OnSwipeTouchListener(OrderListActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(OrderListActivity.this, "Nothing to do here", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Intent intent = new Intent(OrderListActivity.this, TableListActivity.class);
                finish();
                startActivity(intent);

            }
            public void onSwipeLeft() {
                Toast.makeText(OrderListActivity.this, "Nothing to do here", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(OrderListActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
            }

        });

    }
}

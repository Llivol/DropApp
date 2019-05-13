package com.example.dropapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dropapp.Listeners.OnSwipeTouchListener;
import com.example.dropapp.R;

public class OrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        setTitle(R.string.title_activity_order_list);

        findViewById(R.id.bg_order_list).setOnTouchListener(new OnSwipeTouchListener(OrderListActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(OrderListActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(OrderListActivity.this, "Nothing to do here", Toast.LENGTH_SHORT).show();
            }

        });

    }
}

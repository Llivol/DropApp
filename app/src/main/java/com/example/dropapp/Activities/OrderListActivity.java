package com.example.dropapp.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropapp.Adapters.OrderListAdapter;
import com.example.dropapp.Adapters.TableListAdapter;
import com.example.dropapp.Listeners.OnSwipeTouchListener;
import com.example.dropapp.Models.Order;
import com.example.dropapp.Models.Table;
import com.example.dropapp.R;

public class OrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        setTitle(R.string.title_activity_order_list);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        setCustomActionBar(true, R.string.title_activity_order_list);

        // Adapters

        OrderListAdapter adapter = new OrderListAdapter(OrderListActivity.this, R.layout.item_order_list, getMyApp().getOrders());
        ListView list_view = this.findViewById(R.id.lv_orders);
        list_view.setAdapter(adapter);

        // Listeners

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderListActivity.this, TableListActivity.class);
                finish();
                startActivity(intent);
            }
        });

        findViewById(R.id.lv_orders).setOnTouchListener(new OnSwipeTouchListener(OrderListActivity.this) {
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

    public void expandContent(View view) {

        RelativeLayout row = (RelativeLayout) view.getParent();

        if ( row.getChildAt(2).getVisibility() == View.VISIBLE ) {
            row.getChildAt(1).setVisibility(View.VISIBLE);
            row.getChildAt(2).setVisibility(View.GONE);
            row.getChildAt(3).setVisibility(View.GONE);
            ((ImageButton) row.getChildAt(4)).setImageResource(R.drawable.ic_down_icon_white);
        }
        else {
            row.getChildAt(1).setVisibility(View.GONE);
            row.getChildAt(2).setVisibility(View.VISIBLE);
            row.getChildAt(3).setVisibility(View.VISIBLE);
            ((ImageButton) row.getChildAt(4)).setImageResource(R.drawable.ic_up_icon_white);
        }
    }
}

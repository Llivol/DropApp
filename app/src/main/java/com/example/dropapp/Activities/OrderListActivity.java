package com.example.dropapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dropapp.Adapters.OrderListAdapter;
import com.example.dropapp.Adapters.TableListAdapter;
import com.example.dropapp.ComandaData;
import com.example.dropapp.Listeners.OnSwipeTouchListener;
import com.example.dropapp.Models.Order;
import com.example.dropapp.Models.Table;
import com.example.dropapp.R;
import com.example.dropapp.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        setTitle(R.string.title_activity_order_list);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        setCustomActionBar(true, R.string.title_activity_order_list);

        // TODO: Aqui aniria el getOrders
        getComandes();

        // Adapters

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

    public void notifyTable(final View view) {

        new AlertDialog.Builder(this)
                .setMessage( "Es notificarà a la taula" )
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int tableId = Integer.parseInt(((TextView) ((ViewGroup)view.getParent()).getChildAt(0)).getText().toString().split(" ")[1]);

                        Toast.makeText(OrderListActivity.this, "Notificar taula " + tableId , Toast.LENGTH_SHORT).show();
                        postWarn(tableId);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Nosing
                    }
                })
                .show();
    }

    private void postWarn(int tableId) {
        String routeUrl = Utils.defaultUrl + "warn";

        final int id = tableId;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = routeUrl;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("warn","Response is: "+ response);

                        Intent intent = new Intent();
                        intent.setClass( OrderListActivity.this, OrderListActivity.class );
                        startActivity( intent );

                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("warn", "Error: " + error.toString());
                        Toast.makeText(OrderListActivity.this,
                                "Something went wrong",
                                Toast.LENGTH_LONG);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                String body = "{\"id\": \"" + id + "\" }";

                try {

                    return body == null ? null : body.getBytes( "utf-8" );

                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf( "Unsupported Encoding while trying to get the bytes" );
                    return null;
                }
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void getComandes() {
        String routeUrl = Utils.defaultUrl + "order/get/list";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = routeUrl;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("get_order","Response is: "+ response);
                        ObjectMapper objectMapper = new ObjectMapper();

                        try {
                            List<ComandaData> orderInfo = new ArrayList<>();

                            ArrayList<ComandaData> alComandaData = objectMapper.readValue(
                                    response,
                                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ComandaData.class));

                            for (ComandaData aux :
                                    alComandaData) {
                                orderInfo.add(aux);
                            }

                            OrderListAdapter adapter = new OrderListAdapter(OrderListActivity.this, R.layout.item_order_list, orderInfo);
                            ListView list_view = findViewById(R.id.lv_orders);
                            list_view.setAdapter(adapter);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_table", "Error: " + error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

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

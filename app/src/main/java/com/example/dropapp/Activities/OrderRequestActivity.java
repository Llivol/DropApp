package com.example.dropapp.Activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dropapp.Adapters.OrderRequestAdapter;
import com.example.dropapp.ItemData;
import com.example.dropapp.Models.Item;
import com.example.dropapp.Models.Table;
import com.example.dropapp.R;
import com.example.dropapp.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderRequestActivity extends BaseActivity {

    Table currentTable;
    ArrayList<ItemData> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_request);

        // Get Intent

        currentTable = new Table(
                Integer.parseInt(getIntent().getStringExtra( "Taula" )),
                Long.valueOf(getIntent().getStringExtra( "Score" )),
                getIntent().getStringExtra( "Status" ));

        setCustomActionBar(true, "Taula " + currentTable.getId());

        Button btn_score = findViewById(R.id.btn_score);
        btn_score.setText(currentTable.getScore() + " punts");

        // TODO: Aqui aniria el getItems

        //items = getMyApp().getItems();
        items = new ArrayList<ItemData>();
        getPrices();

        // Listeners

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderRequestActivity.this, TableListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });

        Button btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaComanda(v);
            }
        });

        /* Buida comanda no funciona

        Button btnClean = (Button) findViewById(R.id.btn_reset);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buidaComanda(v);
            }
        });

         */

    }
    /*

        private void buidaComanda(View v) {

        for (Item item :
                items) {
            item.setAmount(0);
            item.setAmountD(0);
        }

        Toast.makeText(OrderRequestActivity.this, "Buida comanda no funciona", Toast.LENGTH_LONG);

        updateCurrentTable();
    }

     */

    public void enviaComanda(final View view) {

        if ( currentTable.getScore() < 0 ) {

            new AlertDialog.Builder(this)
                    .setMessage( "No es pot realitzar la comanda. \nPunts Drop insuficients." )
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Aqui pot fer un reset de la comanda
                            //buidaComanda(view);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

        else if ( comandaIsEmpty() ) {

            new AlertDialog.Builder(this)
                    .setMessage( "No es pot realitzar la comanda. \nComanda buida." )
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

        else {
            new AlertDialog.Builder(this)
                    .setMessage( "Avisi a la taula quan la comanda estigui llesta" )
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //TODO Post comanda
                            String comandaData = parseComanda();

                            postComanda(comandaData);

                            //buidaComanda(view);

                            Intent intent = new Intent();
                            intent.setClass( OrderRequestActivity.this, TableListActivity.class );
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity( intent );

                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
    }

    private boolean comandaIsEmpty() {

        for (ItemData aux :
                items) {
            if (aux.getAcummulatedAmount() > 0) return false;
        }

        return true;
    }

    public void postComanda(final String data) {

        String routeUrl = Utils.defaultUrl + "order/create";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = routeUrl;

        Log.d("post_order", "URL: " + routeUrl);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("post_order", "Response: " + response );

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("post_order", "Error: " + error.toString());
            }
        }
        ) {

            @Override
            public byte[] getBody() throws AuthFailureError {

                byte[] body = new byte[0];
                try {
                    body = data.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e("post_order", "Unable to gets bytes from JSON", e.fillInStackTrace());
                }
                return body;
            }


            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put( "Content-Type", "application/json" );
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void getPrices () {

        String routeUrl = Utils.defaultUrl + "order/prices/get";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = routeUrl;

        Log.d("get_prices", "URL: " + routeUrl);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper objectMapper = new ObjectMapper();

                        try {

                            ArrayList<ItemData> alItemNames = objectMapper.readValue(
                                    response,
                                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ItemData.class));

                            for (ItemData aux :
                                    alItemNames) {
                                items.add( aux );
                            }

                            // Adapters

                            OrderRequestAdapter adapter = new OrderRequestAdapter( OrderRequestActivity.this, R.layout.item_order_request, items);

                            ListView list_view = findViewById(R.id.lv_request);

                            list_view.setAdapter( adapter );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_prices", "Error: " + error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    /*
      Retorna la posició d'un producte dins de items
   */
    private int getArrayPosition( String name )
    {
        for ( ItemData item : items)
        {
            if ( item.getItem().equals( name ) ) return items.indexOf(item);
        }

        return -1;
    }

    /*
        Actualitza la informació de la currentTable
     */
    @SuppressLint("ResourceAsColor")
    public void updateCurrentTable() {

        Button btn_score = findViewById(R.id.btn_score);
        btn_score.setText(currentTable.getScore() + " punts");

        if ( currentTable.getScore() < 0 ) btn_score.setTextColor(R.color.colorSecondayDark);
        else btn_score.setTextColor(R.color.colorPrimaryDark);
    }

    /*
        Afegeix un producte amb descompte drop a la comanda
     */
    public void addItemDrop(View view)
    {
        RelativeLayout row = (RelativeLayout) view.getParent();

        String name = (String)((TextView)row.getChildAt( 0 )).getText();

        //int pos = getArrayPosition( name.toLowerCase() );
        int pos = getArrayPosition( name );

        items.get(pos).setAmountD( items.get(pos).getAmountD() + 1 );

        currentTable.setScore( currentTable.getScore() - items.get(pos).getPointCost() );

        updateCurrentTable();

        ((TextView) row.findViewById( R.id.tv_counter )).setText( "" +  items.get(pos).getAcummulatedAmount() );

        //((TextView) row.getChildAt(3)).setText( "" +  items.get(pos).getAcummulatedAmount() );
    }

    /*
        Afegeix un producte a la comanda
     */
    public void addItem(View view)
    {
        RelativeLayout row = (RelativeLayout) view.getParent();

        String name = (String)((TextView)row.getChildAt( 0 )).getText();

        //int pos = getArrayPosition( name.toLowerCase() );
        int pos = getArrayPosition( name );

        items.get(pos).setAmount( items.get(pos).getAmount() + 1 );

        ((TextView) row.getChildAt(3)).setText( "" +  items.get(pos).getAcummulatedAmount() );
    }

    /*
        Elimina un producte de la comanda
        Primer elimina els productes amb descompte Drop cridant substractItemDrop
     */
    public void substractItem(View view)
    {
        RelativeLayout row = (RelativeLayout) view.getParent();

        String name = (String)((TextView)row.getChildAt( 0 )).getText();

        //int pos = getArrayPosition( name.toLowerCase() );
        int pos = getArrayPosition( name );

        if ( items.get(pos).getAmountD() > 0 ) substractItemDrop(pos);

        else if ( items.get(pos).getAmount() > 0 ) items.get(pos).setAmount( items.get(pos).getAmount() - 1 );

        ((TextView) row.getChildAt(3)).setText( "" +  items.get(pos).getAcummulatedAmount() );

    }

    /*
        Elimina un producte amb descompte Drop de la comanda
     */
    public void substractItemDrop(int pos ) {

        items.get(pos).setAmountD( items.get(pos).getAmountD() - 1 );

        currentTable.setScore( currentTable.getScore() + items.get(pos).getPointCost() );

        updateCurrentTable();
    }

    /*
        Mètode que retorna tota la informació de la comanda en format Json
     */

    private String parseComanda(){

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.createObjectNode();

        ((ObjectNode) rootNode).put( "id", Integer.toString(currentTable.getId()) );
        ((ObjectNode) rootNode).put( "points_spent", getPointsSpent() );

        JsonNode details = mapper.createArrayNode();
        for (ItemData aux :
                items) {
            if (aux.getAcummulatedAmount() > 0) {

                JsonNode listNode = mapper.createObjectNode();
                ((ObjectNode) listNode).put( "item", aux.getItem() );
                ((ObjectNode) listNode).put( "amount", aux.getAmount().toString() );
                ((ObjectNode) listNode).put( "amount_d", aux.getAmountD().toString() );

                ((ArrayNode) details).add(listNode);
            }
        }
        ((ObjectNode) rootNode).set( "details", details );

        try {

            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString( rootNode );

            return jsonString;
        } catch (JsonProcessingException e) {

            e.printStackTrace();
            return "{\"data\": \"something went wrong\" }";
        }
    }

    /*
    Mètode que retorna els punts gastats amb descomptes drop
    */
    private String getPointsSpent() {

        Long pointsSpent = 0L;

        for (ItemData aux :
                items) {
            pointsSpent = pointsSpent + (aux.getAmountD() * aux.getPointCost());
        }

        return pointsSpent.toString();
    }

}

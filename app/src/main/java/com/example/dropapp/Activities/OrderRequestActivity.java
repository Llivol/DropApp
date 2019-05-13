package com.example.dropapp.Activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.dropapp.Adapters.OrderRequestAdapter;
import com.example.dropapp.Models.Item;
import com.example.dropapp.Models.Table;
import com.example.dropapp.R;

import java.util.ArrayList;

public class OrderRequestActivity extends BaseActivity {

    Table currentTable;
    ArrayList<Item> lData;

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

        lData = getMyApp().getItems();

        // Adapters

        OrderRequestAdapter adapter = new OrderRequestAdapter( OrderRequestActivity.this, R.layout.item_order_request, lData );

        ListView list_view = this.findViewById(R.id.lv_request);

        list_view.setAdapter( adapter );

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

        Button btnClean = (Button) findViewById(R.id.btn_reset);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buidaComanda(v);
            }
        });

    }

    private void buidaComanda(View v) {

    }

    public void enviaComanda(View view) {

        if ( currentTable.getScore() < 0 ) {

            new AlertDialog.Builder(this)
                    .setMessage( "No es pot realitzar la comanda. \nPunts Drop insuficients." )
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Aqui pot fer un reset de la comanda
                        }
                    })
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
                    .show();
        }

        else {
            new AlertDialog.Builder(this)
                    .setMessage( "Avisi a la taula quan la comanda estigui llesta" )
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //TODO Post comanda
                            //String comandaData = parseComanda();

                            //postComanda(comandaData);

                            Intent intent = new Intent();
                            intent.setClass( OrderRequestActivity.this, TableListActivity.class );
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity( intent );

                            finish();
                        }
                    })
                    .show();
        }
    }

    private boolean comandaIsEmpty() {

        for (Item aux :
               lData) {
            if (aux.getAcummulatedAmount() > 0) return false;
        }

        return true;
    }

    /*
      Retorna la posició d'un producte dins de lData
   */
    private int getArrayPosition( String name )
    {
        for ( Item item : lData )
        {
            if ( item.getItem().equals( name ) ) return lData.indexOf(item);
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

        lData.get(pos).setAmountD( lData.get(pos).getAmountD() + 1 );

        currentTable.setScore( currentTable.getScore() - lData.get(pos).getPointCost() );

        updateCurrentTable();

        ((TextView) row.findViewById( R.id.tv_counter )).setText( "" +  lData.get(pos).getAcummulatedAmount() );

        //((TextView) row.getChildAt(3)).setText( "" +  lData.get(pos).getAcummulatedAmount() );
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

        lData.get(pos).setAmount( lData.get(pos).getAmount() + 1 );

        ((TextView) row.getChildAt(3)).setText( "" +  lData.get(pos).getAcummulatedAmount() );
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

        if ( lData.get(pos).getAmountD() > 0 ) substractItemDrop(pos);

        else if ( lData.get(pos).getAmount() > 0 ) lData.get(pos).setAmount( lData.get(pos).getAmount() - 1 );

        ((TextView) row.getChildAt(3)).setText( "" +  lData.get(pos).getAcummulatedAmount() );

    }

    /*
        Elimina un producte amb descompte Drop de la comanda
     */
    public void substractItemDrop(int pos ) {

        lData.get(pos).setAmountD( lData.get(pos).getAmountD() - 1 );

        currentTable.setScore( currentTable.getScore() + lData.get(pos).getPointCost() );

        updateCurrentTable();
    }

    /*
        Mètode que retorna tota la informació de la comanda en format Json
     */
    /*
    private String parseComanda(){

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.createObjectNode();

        ((ObjectNode) rootNode).put( "id", currentTable.getId().toString() );
        ((ObjectNode) rootNode).put( "points_spent", getPointsSpent() );

        JsonNode details = mapper.createArrayNode();
        for (ItemJsonData aux :
                lData) {
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
    */
    /*
    Mètode que retorna els punts gastats amb descomptes drop
    */
    private String getPointsSpent() {

        Long pointsSpent = 0L;

        for (Item aux :
                lData) {
            pointsSpent = pointsSpent + (aux.getAmountD() * aux.getPointCost());
        }

        return pointsSpent.toString();
    }

}

package com.example.dropapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropapp.Adapters.TableListAdapter;
import com.example.dropapp.Listeners.OnSwipeTouchListener;
import com.example.dropapp.Models.Table;
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

        setCustomActionBar(false, R.string.title_activity_table_list);

        // Adapter

        ArrayAdapter<Table> adapter = new TableListAdapter(TableListActivity.this, R.layout.item_table_list, getMyApp().getTables());
        ListView list_view = this.findViewById(R.id.lv_tables);
        list_view.setAdapter(adapter);

        // Listeners

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Això encara no fa res, chill", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.btn_forward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableListActivity.this, OrderListActivity.class);
                finish();
                startActivity(intent);
            }
        });

        findViewById(R.id.lv_tables).setOnTouchListener(new OnSwipeTouchListener(TableListActivity.this) {
            public void onSwipeTop() {
                if (hasEndedScrolling(false))  Toast.makeText(TableListActivity.this, "Rock bottom ", Toast.LENGTH_SHORT).show();;
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
                if (hasEndedScrolling(true))  Toast.makeText(TableListActivity.this, "Refresh ", Toast.LENGTH_SHORT).show();;
            }

        });

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Aqui podem afegir activities per a cada espai de la llista
                Intent intent = new Intent();

                intent.putExtra( "Taula", "" + getMyApp().getTables().get(position).getId());
                intent.putExtra( "Score", getMyApp().getTables().get(position).getScore() + "" );
                intent.putExtra( "Status", getMyApp().getTables().get(position).getStatus() );

                intent.setClass( TableListActivity.this, OrderRequestActivity.class );
                startActivity( intent );
            }
        });

    }

    private boolean hasEndedScrolling(boolean top) {

        ListView lv = findViewById(R.id.lv_tables);

        int wantedPosition;

        if (top) wantedPosition = 0;
        else wantedPosition = getMyApp().getTables().size() - 1;

        int firstPosition = lv.getFirstVisiblePosition() - lv.getHeaderViewsCount(); // This is the same as child #0
        int wantedChild = wantedPosition - firstPosition;

        if (wantedChild < 0 || wantedChild >= lv.getChildCount()) return false;

        return true;
    }

    private void firstLaunch() {

        getMyApp().setFirstTime(false);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}

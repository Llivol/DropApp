package com.example.dropapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dropapp.Activities.OrderListActivity;
import com.example.dropapp.Models.Item;
import com.example.dropapp.Models.Order;
import com.example.dropapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Order> {

    Context context;
    int layoutResourceId;
    List<Order> orders = null;

    public OrderListAdapter(Context context, int resource, List<Order> objects)
    {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.orders = objects;
    }

    static class DataHolder {

        TextView tvTable;
        TextView tvItems;
        ListView lvItems;
        Button btnNotify;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {

        DataHolder holder = null;

        if ( convertView == null )
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.tvTable = (TextView)convertView.findViewById(R.id.tv_table);
            holder.tvItems = (TextView)convertView.findViewById(R.id.tv_items);
            holder.lvItems =(ListView) convertView.findViewById(R.id.lv_items);
            holder.btnNotify = convertView.findViewById(R.id.btn_notify);

            convertView.setTag(holder);
        }
        else
        {
            holder = (DataHolder)convertView.getTag();
        }

        Order currentOrder = orders.get(position);
        ItemListAdapter itemListAdapter = new ItemListAdapter(parent.getContext(), R.layout.item_order_item, orders.get(position).getItems());

        holder.tvTable.setText("Taula " + currentOrder.getId());
        holder.tvItems.setText(currentOrder.itemsToString());
        holder.lvItems.setAdapter(itemListAdapter);

        holder.lvItems.setVisibility(View.GONE);
        holder.btnNotify.setVisibility(View.GONE);

        return convertView;
    }
}

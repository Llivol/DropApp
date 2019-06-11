package com.example.dropapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dropapp.DetailData;
import com.example.dropapp.ItemData;
import com.example.dropapp.Models.Item;
import com.example.dropapp.Models.Order;
import com.example.dropapp.R;

import java.util.List;

public class ItemListAdapter extends ArrayAdapter<ItemData> {

    Context context;
    int layoutResourceId;
    List<ItemData> items = null;

    public ItemListAdapter(Context context, int resource, List<ItemData> objects)
    {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.items = objects;
    }

    static class DataHolder {

        TextView tvItemName;
        TextView tvItemPrice;
        TextView tvItemAmount;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent)
    {

        DataHolder holder = null;

        if ( convertView == null )
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.tvItemName = (TextView)convertView.findViewById(R.id.tv_item_name);
            holder.tvItemPrice = (TextView)convertView.findViewById(R.id.tv_item_price);
            holder.tvItemAmount =(TextView)convertView.findViewById(R.id.tv_item_units);

            convertView.setTag(holder);
        }
        else
        {
            holder = (DataHolder)convertView.getTag();
        }

        ItemData currentItem = items.get(position);

        Resources res = context.getResources();
        //String text = res.getString(R.string.welcome_messages, username, mailCount);

        // TODO: Nomes es mostren items sense descompte
        if (currentItem.getAmount() > 0) {

            holder.tvItemName.setText(currentItem.getItem());
            holder.tvItemPrice.setText(res.getString(R.string.format_item_price, currentItem.getPrice()));
            holder.tvItemAmount.setText(res.getString(R.string.format_item_amount, Long.toString(currentItem.getAmount())));
        }
        else {

            holder.tvItemName.setText(currentItem.getItem() + " desc");
            holder.tvItemPrice.setText(res.getString(R.string.format_item_price, currentItem.getDiscPrice()));
            holder.tvItemAmount.setText(res.getString(R.string.format_item_amount, Long.toString(currentItem.getAmountD())));
        }

        return convertView;
    }

}

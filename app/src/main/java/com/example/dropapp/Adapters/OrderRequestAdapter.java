package com.example.dropapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dropapp.ItemData;
import com.example.dropapp.Models.Item;
import com.example.dropapp.R;

import java.util.List;

public class OrderRequestAdapter extends ArrayAdapter<ItemData> {

    Context context;
    int layoutResourceId;
    List<ItemData> items = null;

    public OrderRequestAdapter(Context context, int resource, List<ItemData> objects)
    {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.items = objects;
    }

    /*
        Classe temporal que permet guardar la informació dels items
     */
    static class DataHolder
    {
        TextView tvName;
        TextView tvCounter;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        DataHolder holder = null;

        if ( convertView == null )
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.tvName = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tvCounter = (TextView)convertView.findViewById(R.id.tv_counter);

            convertView.setTag(holder);
        }
        else
        {
            holder = (DataHolder)convertView.getTag();
        }
        ItemData dataItem = items.get(position);

        Log.d( "table_information", dataItem.getItem() );

        //holder.tvName.setText(Utils.toTitleCase(dataItem.getItem()));
        holder.tvName.setText(dataItem.getItem());
        holder.tvCounter.setText(dataItem.getAcummulatedAmount() + "");

        return convertView;
    }
}

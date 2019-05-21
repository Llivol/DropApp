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

import com.example.dropapp.Models.Table;
import com.example.dropapp.R;
import com.example.dropapp.TableData;

import java.util.List;

public class TableListAdapter extends ArrayAdapter<TableData> {

    Context context;
    int layoutResourceId;
    List<TableData> tables = null;

    public TableListAdapter(Context context, int resource, List<TableData> tables)
    {
        super(context, resource, tables);

        this.layoutResourceId = resource;
        this.context = context;
        this.tables = tables;
    }

    static class DataHolder
    {
        TextView tvTable;
        TextView tvScore;
        TextView tvStatus;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Log.d("get_table", Integer.toString(position));

        DataHolder holder = null;

        if ( convertView == null )
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.tvTable = (TextView)convertView.findViewById(R.id.tv_table);
            holder.tvScore = (TextView)convertView.findViewById(R.id.btn_score);
            holder.tvStatus = (TextView)convertView.findViewById(R.id.tv_status);

            convertView.setTag(holder);
        }
        else
        {
            holder = (DataHolder)convertView.getTag();
        }
        TableData tableItem = tables.get(position);

        holder.tvTable.setText("Taula " + tableItem.getId());
        holder.tvScore.setText(tableItem.getPoints() + " punts");
        holder.tvStatus.setText(tableItem.getStatus());

        return convertView;
    }
}

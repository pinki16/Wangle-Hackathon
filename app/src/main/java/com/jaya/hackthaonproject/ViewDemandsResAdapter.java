package com.jaya.hackthaonproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ViewDemandsResAdapter extends ArrayAdapter {
    List<ViewDemandsRes> list=new ArrayList();
    public ViewDemandsResAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(ViewDemandsRes object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        ContactHolders ch;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.view_demands_row,parent,false);
            ch=new ContactHolders();
            ch.resource_type=(TextView)row.findViewById(R.id.resource_type);
            ch.no_of_resources=(TextView)row.findViewById(R.id.no_of_resources);
            ch.completion_time=(TextView)row.findViewById(R.id.completion_time);
            ch.Deadline=(TextView)row.findViewById(R.id.deadline);
            ch.location_id=(TextView)row.findViewById(R.id.location_id);
            ch.date_of_demand=(TextView)row.findViewById(R.id.date_of_demand);
            ch.priority=(TextView)row.findViewById(R.id.priority);
            ch.demand_id=(TextView)row.findViewById(R.id.demand_id);
            row.setTag(ch);
        }
        else
        {
            ch=(ContactHolders)row.getTag();

        }
        ViewDemandsRes contacts=(ViewDemandsRes)this.getItem(position);
        ch.demand_id.setText(contacts.getDemand_id());
        ch.resource_type.setText(contacts.getResource_type());
        ch.no_of_resources.setText(contacts.getNo_of_resources());
        ch.completion_time.setText(contacts.getCompletion_time());
        ch.Deadline.setText(contacts.getDeadline());
        ch.location_id.setText(contacts.getLocation_id());
        ch.date_of_demand.setText(contacts.getDate_of_demand());
        ch.priority.setText(contacts.getPriority());
        return row;
    }
    static class ContactHolders
    {
        TextView resource_type;
        TextView no_of_resources;
        TextView completion_time;
        TextView Deadline;
        TextView location_id;
        TextView date_of_demand;
        TextView priority;
        TextView demand_id;

    }
}


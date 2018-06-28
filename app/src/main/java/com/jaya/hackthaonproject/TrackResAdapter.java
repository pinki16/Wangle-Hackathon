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


public class TrackResAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public TrackResAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(TrackRes object) {
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
            row=layoutInflater.inflate(R.layout.track_row,parent,false);
            ch=new ContactHolders();
            ch.demand_id =(TextView)row.findViewById(R.id.resource_id_track);
            ch.des_id =(TextView)row.findViewById(R.id.resource_type_track);
            ch.no =(TextView)row.findViewById(R.id.location_id_track);
            ch.res_type =(TextView)row.findViewById(R.id.status_track);

            row.setTag(ch);
        }
        else
        {
            ch=(ContactHolders)row.getTag();

        }
        TrackRes contacts=(TrackRes) this.getItem(position);
        ch.demand_id.setText(contacts.getResource_id());
        ch.des_id.setText(contacts.getResource_type());
        ch.no.setText(contacts.getLocation_id());
        ch.res_type.setText(contacts.getStatus());

        return row;
    }
    static class ContactHolders
    {
        TextView demand_id;
        TextView des_id;
        TextView no;
        TextView res_type;


    }
}


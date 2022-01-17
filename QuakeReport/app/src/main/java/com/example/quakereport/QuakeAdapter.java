package com.example.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuakeAdapter extends ArrayAdapter<Quake> {
    public QuakeAdapter(@NonNull Context context, ArrayList<Quake> Quake) {
        super(context, 0, Quake);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Quake currentQuake=getItem(position);
        TextView magView=(TextView)listItemView.findViewById(R.id.mag);
        magView.setText(currentQuake.getQmag());

        TextView placeView=(TextView)listItemView.findViewById(R.id.place);
        placeView.setText(currentQuake.getQplace());

        TextView timeView=(TextView)listItemView.findViewById(R.id.time);
        timeView.setText(currentQuake.getQtime());

        return listItemView;
    }
}

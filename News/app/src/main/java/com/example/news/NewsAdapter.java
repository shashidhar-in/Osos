package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, ArrayList<News> News) {
        super(context, 0, News);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        News currentQuake=getItem(position);
        TextView magView=(TextView)listItemView.findViewById(R.id.title);
        magView.setText(currentQuake.getNtitle());

        TextView timeView=(TextView)listItemView.findViewById(R.id.time);
        timeView.setText(currentQuake.getNtime());

        return listItemView;
    }
}

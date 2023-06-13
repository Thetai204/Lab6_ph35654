package com.example.lab6_ph35654;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Appdeter_lociton extends BaseAdapter {
    private Activity activity;
    private ArrayList<item_coso> list;

    public static final String KEY_SV = "update";
    public static final String KEY_List = "ds";
    public static final String KEY_TEN = "ten";
    public static final String KEY_DIACHI = "diachi";
    public static final String KEY_COSO = "coso";
    public Appdeter_lociton(Activity activity, ArrayList<item_coso> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.itemcoso,parent,false);
        ImageView imageView = convertView.findViewById(R.id.ivAvatar);
        TextView textView = convertView.findViewById(R.id.txtName);

       item_coso itemCoso=list.get(position);
        imageView.setImageResource(itemCoso.getIcon());
        textView.setText(itemCoso.getName());

        return convertView;
    }
}

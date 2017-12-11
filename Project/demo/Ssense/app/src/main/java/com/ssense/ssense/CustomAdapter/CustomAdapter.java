package com.ssense.ssense.CustomAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ssense.ssense.DataModels.SanPham;
import com.ssense.ssense.R;

import java.util.ArrayList;

/**
 * Created by yiile on 12/7/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private ArrayList<SanPham> items;
    private Context context;
    private LayoutInflater inflater;
    public CustomAdapter(Context _context, ArrayList<SanPham> _items){
        inflater     = LayoutInflater.from(_context);
        this.context = _context;
        this.items = _items;
    }

    @Override
    public int getCount() {
        return  items.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SanPham sp = items.get(i);
        View views = view;
        if(views == null)
            views = inflater.inflate(R.layout.customadapter_main, null);
        ImageView imageView = (ImageView) views.findViewById(R.id.picture);
        TextView textName = (TextView) views.findViewById(R.id.txtName);
        TextView textGia = (TextView) views.findViewById(R.id.txtGia);
        TextView textGiaCu = (TextView) views.findViewById(R.id.txtGiaCu);
        TextView textDeal = (TextView) views.findViewById(R.id.txtDeal);

        Log.d("deal", sp.getDeal().toString());
        Log.d("giamoi", sp.getGia().toString());
        Float giaCu  = (sp.getGia() + ((sp.getGia()/100)*sp.getDeal()));

        Log.d("giacu", giaCu.toString());
        Picasso.with(context).load(sp.getHinh()).into(imageView);
        textName.setText(sp.getName());
        textGia.setText(sp.getGia().toString());
        textGiaCu.setText(giaCu.toString());
        textDeal.setText(sp.getDeal().toString());
        return views;
    }
}


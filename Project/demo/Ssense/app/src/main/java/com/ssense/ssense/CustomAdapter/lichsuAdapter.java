package com.ssense.ssense.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssense.ssense.DataModels.lichsu;
import com.ssense.ssense.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Vinh on 07-Dec-17.
 */

public class lichsuAdapter extends BaseAdapter {
    Context context;
    ArrayList<lichsu> products;
    NumberFormat formatter = new DecimalFormat("###,###");

    public lichsuAdapter(Context context, ArrayList<lichsu> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        lichsuAdapter.ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvlichsu, viewGroup, false);
            viewHolder = new lichsuAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (lichsuAdapter.ViewHolder) view.getTag();
        }

        lichsu product = (lichsu) getItem(i);
        viewHolder.tien.setText(product.getTien());
        viewHolder.tinhtrang.setText(product.getTinhtrang());
        viewHolder.diachi.setText("Địa chỉ:  " + product.getDiachi());
        viewHolder.sanpham.setText(product.getSanpham());
        viewHolder.note.setText("Ghi chú:  "+product.getNote());




        return view;
    }


    private class ViewHolder {
        TextView tien, tinhtrang,diachi,sanpham,note;
        ImageView imgProduct;

        public ViewHolder(View view) {
            tien = view.findViewById(R.id.tien);

            tinhtrang = view.findViewById(R.id.tinhtrang);
            diachi = view.findViewById(R.id.diachi);
            sanpham = view.findViewById(R.id.sanpham);
            note = view.findViewById(R.id.note);
        }
    }


    public String formatCurrency(int price) {
        return formatter.format(price).replace(",", ".");
    }
}

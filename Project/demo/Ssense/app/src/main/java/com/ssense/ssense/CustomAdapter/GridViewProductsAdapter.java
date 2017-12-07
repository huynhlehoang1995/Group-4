package com.ssense.ssense.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ssense.ssense.DataModels.SanPham;
import com.ssense.ssense.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by PC on 11/28/2017.
 */

public class GridViewProductsAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> products;
    NumberFormat formatter = new DecimalFormat("###,###");

    public GridViewProductsAdapter(Context context, ArrayList<SanPham> products) {
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
        GridViewProductsAdapter.ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gridview_product, viewGroup, false);
            viewHolder = new GridViewProductsAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (GridViewProductsAdapter.ViewHolder) view.getTag();
        }

        SanPham product = (SanPham) getItem(i);
        viewHolder.productName.setText(product.getName());
        viewHolder.productPrice.setText(product.getGia().toString());
        Picasso.with(view.getContext()).load(product.getHinh()).resize(200, 200)
                .centerCrop().into(viewHolder.imgProduct);


        return view;
    }


    private class ViewHolder {
        TextView productName, productPrice;
        ImageView imgProduct;

        public ViewHolder(View view) {
            productName = view.findViewById(R.id.tvwGirdViewProductName);

            productPrice = view.findViewById(R.id.tvwGridViewPrice);
            imgProduct = view.findViewById(R.id.imgGridViewProduct);
        }
    }


    public String formatCurrency(int price) {
        return formatter.format(price).replace(",", ".");
    }

}

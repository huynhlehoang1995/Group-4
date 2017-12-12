package com.ssense.ssense.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.ssense.ssense.DataModels.SanPhanGioHang;
import com.ssense.ssense.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by PC on 11/30/2017.
 */

public class ListViewWishListAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPhanGioHang> products;
    NumberFormat formatter = new DecimalFormat("###,###");
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    String name;
    int aa;

    public ListViewWishListAdapter(Context context, ArrayList<SanPhanGioHang> products) {
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
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_wishlist_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final SanPhanGioHang product = (SanPhanGioHang) getItem(i);
        viewHolder.productName.setText(product.name);
        name = product.name;
        aa = i;
        viewHolder.productColor.setText(product.mau);
        viewHolder.productSize.setText(product.size);
        viewHolder.productPrice.setText(formatCurrency(product.gia));
        Picasso.with(view.getContext()).load(product.hinh).resize(90, 90)
                .centerCrop().into(viewHolder.imgProduct);

        viewHolder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s[] = (auth.getCurrentUser().getEmail()).toString().split("@");
                data.child("Giohang").child(s[0]).child(product.Key).removeValue();
                products.clear();

            }
        });





        return view;
    }


    private class ViewHolder {
        TextView productName, productColor, productSize, productPrice;
        ImageView imgProduct,xoa;

        public ViewHolder(View view) {
            productName = view.findViewById(R.id.tvwWishListProductName);
            productColor = view.findViewById(R.id.tvwWishListProductColor);
            productSize = view.findViewById(R.id.tvwWishListProductSize);
            productPrice = view.findViewById(R.id.tvwWishListPrice);
            imgProduct = view.findViewById(R.id.imgWishListProduct);
            xoa = view.findViewById(R.id.xoagiahang);
        }
    }


    public String formatCurrency(int price) {
        return formatter.format(price).replace(",", ".");
    }

}

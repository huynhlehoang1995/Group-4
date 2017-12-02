package com.ssense.ssense.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.ActivityChooserView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssense.ssense.DataModels.Product;
import com.ssense.ssense.R;

import java.util.ArrayList;

/**
 * Created by PC on 11/30/2017.
 */

public class ListViewWishListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> products;

    public ListViewWishListAdapter(Context context, ArrayList<Product> products) {
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
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_wishlist_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Product product = (Product) getItem(i);
        viewHolder.productName.setText(product.getName());
        viewHolder.productColor.setText(product.getColor());
        viewHolder.productSize.setText(product.getSize());
        viewHolder.productPrice.setText(product.getPrice()+"");
        viewHolder.imgProduct.setImageResource(R.drawable.hmprod);


        return view;
    }


    private class ViewHolder {
        TextView productName,productColor, productSize, productPrice;
        ImageView imgProduct;

        public ViewHolder(View view) {
            productName = view.findViewById(R.id.tvwWishListProductName);
            productColor = view.findViewById(R.id.tvwWishListProductColor);
            productSize = view.findViewById(R.id.tvwWishListProductSize);
            productPrice = view.findViewById(R.id.tvwWishListPrice);
            imgProduct = view.findViewById(R.id.imgWishListProduct);
        }
    }
}

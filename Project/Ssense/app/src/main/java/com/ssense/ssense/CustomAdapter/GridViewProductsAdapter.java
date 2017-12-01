package com.ssense.ssense.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssense.ssense.DataModels.Product;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by PC on 11/28/2017.
 */

public class GridViewProductsAdapter extends ArrayAdapter<Product> {


    public GridViewProductsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder{
        ImageView imgProduct;
        TextView txtProductName, txtPrice;
        ImageButton imgColor;
    }

    @Nullable
    @Override
    public Product getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final  ViewHolder viewHolder;
        LayoutInflater inflater;




        return super.getView(position, convertView, parent);
    }
}

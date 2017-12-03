package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ssense.ssense.CustomAdapter.ListViewWishListAdapter;
import com.ssense.ssense.DataModels.Product;
import com.ssense.ssense.R;

import java.util.ArrayList;


public class WishListFragment extends Fragment {

    ListView listView;
    TextView txtProductName, txtProductColor, txtIndex, txtSum, txtRegex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_wish_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getView().findViewById(R.id.listview_wishlist);
        txtProductName = getView().findViewById(R.id.txtProductName);
        txtProductColor = getView().findViewById(R.id.txtProductColor);
        txtIndex = getView().findViewById(R.id.txtIndexProduct);
        txtSum = getView().findViewById(R.id.txtSumProduct);
        txtRegex = getView().findViewById(R.id.txtRegex);
        txtProductName.setText("Wish list");
        txtRegex.setText("");
        txtSum.setText("");
        txtProductColor.setText("");
        txtIndex.setText("");

        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            products.add(new Product("asdkjhiw", "asdkwkwk", "asddddddddsdddd", "asdddddd", "sad", 723737, 49));
        }

        ListViewWishListAdapter adapter = new ListViewWishListAdapter(getActivity().getBaseContext(), products);
        listView.setAdapter(adapter);
    }

}

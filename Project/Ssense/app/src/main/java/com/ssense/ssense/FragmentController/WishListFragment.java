package com.ssense.ssense.FragmentController;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ssense.ssense.CustomAdapter.ListViewWishListAdapter;
import com.ssense.ssense.DataController.FirebaseController;
import com.ssense.ssense.DataModels.Product;
import com.ssense.ssense.DataModels.WishListProduct;
import com.ssense.ssense.R;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class WishListFragment extends Fragment {

    String user;
    ListView listView;
    Button btnContinue;

    @SuppressLint("ValidFragment")
    public WishListFragment(String user) {
        if (!user.isEmpty())
            this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wish_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseController firebaseController = new FirebaseController();
        btnContinue = view.findViewById(R.id.btnContinue);
        if (user != null) {
            listView = view.findViewById(R.id.listview_wishlist);
            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConfirmFragment fragment = new ConfirmFragment();
                    getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
                }
            });

            ArrayList<WishListProduct> products = new ArrayList<>();
            for (int i = 0; i < 11; i++) {
                products.add(new WishListProduct("asd", "asdasd", 34234, "asdasdsa", "asdasd", "asdasd", "asd"));
            }

            ListViewWishListAdapter adapter = new ListViewWishListAdapter(getActivity().getBaseContext(), products);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, new ProductInformationFragment()).commit();
                }
            });
        } else {
            btnContinue.setEnabled(false);
            return;
        }

    }


}

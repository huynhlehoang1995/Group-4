package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssense.ssense.ActivityController.LoginActivity;
import com.ssense.ssense.CustomAdapter.ListViewWishListAdapter;
import com.ssense.ssense.DataModels.SanPhanGioHang;
import com.ssense.ssense.R;

import java.util.ArrayList;


public class WishListFragment extends Fragment {

    ListView listView;
    TextView tongtien;
    Button btnContinue;
    String sanpham;

    int i ;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

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
        btnContinue = getView().findViewById(R.id.btnContinue);
        tongtien = getView().findViewById(R.id.tongtien);

        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {
            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle =new Bundle();
                    bundle.putString("tien", String.valueOf(i));
                    bundle.putString("sanpham", sanpham);
                    ConfirmFragment fragment = new ConfirmFragment();
                    fragment.setArguments(bundle);
                    getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
                }
            });


            final ArrayList<SanPhanGioHang> products = new ArrayList<>();
            final ListViewWishListAdapter adapter = new ListViewWishListAdapter(getActivity().getBaseContext(), products);
            listView.setAdapter(adapter);
            String s[] = (auth.getCurrentUser().getEmail()).toString().split("@");
            data.child("Giohang").child(s[0]).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    SanPhanGioHang value = dataSnapshot.getValue(SanPhanGioHang.class);
                    dataSnapshot.getKey();
                    value.Key = dataSnapshot.getKey();
                    products.add(value);
                    Log.d("gia",i+ " ");
                    i = value.gia + i ;
                    String aaa=  "Tong tien: " + i + "  vnd";
                    tongtien.setText(aaa);
                    if (sanpham!=null) {
                        sanpham = value.name + "\n" + sanpham;
                    }else {
                        sanpham = value.name;
                    }

                    adapter.notifyDataSetChanged();



                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });









        }

    }


}

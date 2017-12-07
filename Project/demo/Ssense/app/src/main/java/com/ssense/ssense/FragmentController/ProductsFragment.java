package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssense.ssense.CustomAdapter.GridViewProductsAdapter;
import com.ssense.ssense.DataModels.SanPham;
import com.ssense.ssense.R;

import java.util.ArrayList;
import java.util.Collections;


public class ProductsFragment extends Fragment {
    DatabaseReference data;
    GridView grid;
    GridViewProductsAdapter adapter;
    ArrayList<SanPham> arr =  new ArrayList<>();
    TextView button,tim;
    EditText key;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_products, container, false);
        // Inflate the layout for this fragment
        final String strtext = getArguments().getString("danh muc");
        Toast.makeText(view.getContext(),strtext,Toast.LENGTH_LONG).show();
        grid = view.findViewById(R.id.grvProducts);
        button = view.findViewById(R.id.btnSort);
        tim = view.findViewById(R.id.btnFilter);
        key = view.findViewById(R.id.key);
        adapter = new GridViewProductsAdapter(getActivity().getBaseContext(), arr);
        grid.setAdapter(adapter);
        data = FirebaseDatabase.getInstance().getReference();
        data.child("sanpham").child(strtext).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SanPham value = dataSnapshot.getValue(SanPham.class);
                arr.add(value);
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

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),((SanPham)grid.getItemAtPosition(position)).getName(),Toast.LENGTH_LONG).show();
                Bundle bundle =new Bundle();
                bundle.putString("danhmuc", strtext);
                bundle.putString("name", ((SanPham)grid.getItemAtPosition(position)).getName());

                ProductInformationFragment fragment = new ProductInformationFragment();
                fragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(arr);
                adapter.notifyDataSetChanged();
            }
        });
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b =key.getText()+"";
                if (b.isEmpty())
                {
                    arr.clear();
                    data.child("sanpham").child(strtext).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            SanPham value = dataSnapshot.getValue(SanPham.class);
                            arr.add(value);
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
                }else {

                    int ss = 0  ;
                    SanPham abc = new SanPham();
                    for (SanPham a : arr){
                        if (a.name.equals(key.getText().toString())){
                            abc = a ;
                        }
                    }
                    arr.clear();
                    arr.add(abc);





                    adapter.notifyDataSetChanged();

                }
            }
        });


        return view;
    }


}

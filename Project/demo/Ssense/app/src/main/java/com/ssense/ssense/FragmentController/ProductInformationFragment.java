package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.ssense.ssense.ActivityController.LoginActivity;
import com.ssense.ssense.DataModels.SanPhanGioHang;
import com.ssense.ssense.R;


public class ProductInformationFragment extends Fragment {
    Spinner chonSize, chonMau;
    ImageView hinh;
    ImageButton chuyenhinh, hinh1, hinh2, hinh3;
    TextView ten, gia, deal;
    Button mua;
    DatabaseReference data;
    Integer so = 1;
    FirebaseAuth auth;

    String url, url3, url2, Key, danhmuc,name;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_information, container, false);
        auth = FirebaseAuth.getInstance();


        chonSize = view.findViewById(R.id.chonSize);
        chonMau= view.findViewById(R.id.chonMau);
        hinh= view.findViewById(R.id.hinhChinh);
        hinh1= view.findViewById(R.id.hinh1);
        hinh2= view.findViewById(R.id.hinh2);
        hinh3= view.findViewById(R.id.hinh3);

        ten= view.findViewById(R.id.tenSP);
        deal= view.findViewById(R.id.deal);
        gia = view.findViewById(R.id.gia);
        mua = view.findViewById(R.id.muaNgay);
        final String arr[] = new String[3];
        final String arr1[] = new String[2];
        danhmuc = getArguments().getString("danhmuc");
        name = getArguments().getString("name");




        data = FirebaseDatabase.getInstance().getReference();
        //load trang lan dau
        data.child("sanpham").child(danhmuc).orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Key = dataSnapshot.getKey();
                    ten.setText(snapshot.child("name").getValue(String.class));
                    String size = snapshot.child("size").getValue(String.class);
                    String size1 = snapshot.child("size1").getValue(String.class);
                    String size2 = snapshot.child("size2").getValue(String.class);
                    String mau = snapshot.child("mau").getValue(String.class);
                    String mau1 = snapshot.child("mau1").getValue(String.class);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (
                                    view.getContext(),
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    chonSize.setAdapter(adapter);

                    final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                            (
                                    view.getContext(),
                                    android.R.layout.simple_spinner_item,
                                    arr1
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter1.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    chonMau.setAdapter(adapter1);
                    arr[0] = size;
                    arr[1] = size1;
                    arr[2] = size2;
                    adapter.notifyDataSetChanged();
                    arr1[0] = mau;
                    arr1[1] = mau1;
                    adapter1.notifyDataSetChanged();

                    url2 = snapshot.child("hinh1").getValue(String.class);
                    url3 = snapshot.child("hinh2").getValue(String.class);
                    url = snapshot.child("hinh").getValue(String.class);

                    Log.d("h", url);
                    loadHinh(url);

                    Picasso.with(view.getContext()).load(url).resize(90, 90)
                            .centerCrop().into(hinh1);
                    hinh1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadHinh(url);
                        }
                    });
                    Picasso.with(view.getContext()).load(url2).resize(90, 90)
                            .centerCrop().into(hinh2);
                    hinh2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadHinh(url2);
                        }
                    });
                    Picasso.with(view.getContext()).load(url3).resize(90, 90)
                            .centerCrop().into(hinh3);
                    hinh3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadHinh(url3);
                        }
                    });
                    gia.setText(snapshot.child("gia").getValue(Integer.class).toString());
                    deal.setText(snapshot.child("deal").getValue(Integer.class).toString() + "%");


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() == null) {
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    String s[] = (auth.getCurrentUser().getEmail()).toString().split("@");
                    Log.d("us",s[0]);
                    SanPhanGioHang gh = new SanPhanGioHang();
                    gh.name = ten.getText().toString();
                    gh.gia = Integer.parseInt(gia.getText().toString());
                    gh.mau = chonMau.getSelectedItem().toString();
                    gh.size = chonSize.getSelectedItem().toString();
                    gh.hinh = url;
                    gh.danhmuc = danhmuc;
                    gh.Key = Key;
                    data.child("Giohang").child(s[0]).push().setValue(gh);

                }

            }
        });
        return view;
    }


    public void loadHinh(String url) {
        Picasso.with(view.getContext()).load(url).into(hinh);
    }
    // Inflate the layout for this fragment

}



package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.ssense.ssense.CustomAdapter.CustomAdapter;
import com.ssense.ssense.DataModels.SanPham;
import com.ssense.ssense.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Vinh on 11-Dec-17.
 */

public class manhinhchinhFragment extends Fragment {
    DatabaseReference mData;
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    Animation in, out;
    ImageView imageNoibat1, imageNoibat2, imageNoibat3, imageNoibat4;
    TextView tenNoibat1, tenNoibat2, tenNoibat3, tenNoibat4;
    TextView giaNoibat1, giaNoibat2, giaNoibat3, giaNoibat4;
    ArrayList<SanPham> arr = new ArrayList<>();
    ArrayList<SanPham> arr4 = new ArrayList<>();
    CustomAdapter customAdapter;
    ListView list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgmanhinh, container, false);
        imageNoibat1 = view.findViewById(R.id.imageNoibat1);
        tenNoibat1 = view.findViewById(R.id.tenNoibat1);
        giaNoibat1 = view.findViewById(R.id.giaNoibat1);

        imageNoibat2 = view.findViewById(R.id.imageNoibat2);
        tenNoibat2 = view.findViewById(R.id.tenNoibat2);
        giaNoibat2 = view.findViewById(R.id.giaNoibat2);

        imageNoibat3 = view.findViewById(R.id.imageNoibat3);
        tenNoibat3 = view.findViewById(R.id.tenNoibat3);
        giaNoibat3 = view.findViewById(R.id.giaNoibat3);

        imageNoibat4 = view.findViewById(R.id.imageNoibat4);
        tenNoibat4 = view.findViewById(R.id.tenNoibat4);
        giaNoibat4 = view.findViewById(R.id.giaNoibat4);

        list = view.findViewById(R.id.listV);

        customAdapter = new CustomAdapter(getActivity(), arr4);
        list.setAdapter(customAdapter);



       // mData = FirebaseDatabase.getInstance().getReference();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("sanpham").child("Nam").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SanPham value = dataSnapshot.getValue(SanPham.class);
                arr.add(value);
                if (arr.size() > 4) {
//                    Sản phẩm nổi bật 1
                    String pic1 = arr.get(1).hinh;
                    Picasso.with(getActivity()).load(pic1).into(imageNoibat1);
                    String name1 = arr.get(1).name;
                    tenNoibat1.setText(name1);
                    String gia1 = Float.toString(arr.get(1).gia);
                    giaNoibat1.setText(gia1 + " VND");
                    imageNoibat1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle =new Bundle();
                            bundle.putString("danhmuc", "Nam");
                            bundle.putString("name", tenNoibat1.getText().toString());
                            ProductInformationFragment fragment = new ProductInformationFragment();
                            fragment.setArguments(bundle);
                            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
                        }
                    });
//                    Sản phẩm nổi bật 2
                    String pic2 = arr.get(4).hinh2;
                    Picasso.with(getActivity()).load(pic2).into(imageNoibat2);
                    String name2 = arr.get(4).name;
                    tenNoibat2.setText(name2);
                    String gia2 = Float.toString(arr.get(4).gia);
                    giaNoibat2.setText(gia2 + " VND");
                    imageNoibat2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle =new Bundle();
                            bundle.putString("danhmuc", "Nam");
                            bundle.putString("name", tenNoibat2.getText().toString());
                            ProductInformationFragment fragment = new ProductInformationFragment();
                            fragment.setArguments(bundle);
                            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
                        }
                    });
                    //                    Sản phẩm nổi bật 3
                    String pic3 = arr.get(0).hinh;
                    Picasso.with(getActivity()).load(pic3).into(imageNoibat3);
                    String name3 = arr.get(0).name;
                    tenNoibat3.setText(name3);
                    String gia3 = Float.toString(arr.get(3).gia);
                    giaNoibat3.setText(gia3 + " VND");
                    imageNoibat3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle =new Bundle();
                            bundle.putString("danhmuc", "Nam");
                            bundle.putString("name", tenNoibat3.getText().toString());
                            ProductInformationFragment fragment = new ProductInformationFragment();
                            fragment.setArguments(bundle);
                            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
                        }
                    });
//                    Sản phẩm nổi bật 4
                    String pic4 = arr.get(2).hinh2;
                    Picasso.with(getActivity()).load(pic4).into(imageNoibat4);
                    String name4 = arr.get(2).name;
                    tenNoibat4.setText(name4);
                    String gia4 = Float.toString(arr.get(2).gia);
                    giaNoibat4.setText(gia4 + " VND");
                    imageNoibat4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle =new Bundle();
                            bundle.putString("danhmuc", "Nam");
                            bundle.putString("name", tenNoibat4.getText().toString());
                            ProductInformationFragment fragment = new ProductInformationFragment();
                            fragment.setArguments(bundle);
                            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
                        }
                    });

                }
                //Sắp xếp mảng
                Collections.sort(arr, new Comparator<SanPham>() {
                    @Override
                    public int compare(SanPham sp1, SanPham sp2) {
                        if (sp1.deal < sp2.deal) {
                            return 1;
                        } else {
                            if (sp1.deal == sp2.deal) {
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    }
                });


                if (arr.size() == 4) {
                    for (int i = 0; i < 4; i++)

                        arr4.add(arr.get(i));

                }
                customAdapter.notifyDataSetChanged();



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                SanPham value = dataSnapshot.getValue(SanPham.class);
                arr.add(value);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                SanPham value = dataSnapshot.getValue(SanPham.class);
                arr.add(value);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle =new Bundle();
                bundle.putString("danhmuc", "Nam");
                bundle.putString("name", ((SanPham)list.getItemAtPosition(position)).getName());
                ProductInformationFragment fragment = new ProductInformationFragment();
                fragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();

            }
        });




        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewflip);
        int[] resources = {
                R.mipmap.image_1,
                R.mipmap.image_2,
                R.mipmap.image_3
        };

        // Add all the images to the ViewFlipper
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resources[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mViewFlipper.addView(imageView);
        };


        // Set in/out flipping animations
        in = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        mViewFlipper.setInAnimation(in);
        mViewFlipper.setOutAnimation(out);

        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(3500);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(getActivity(), customGestureDetector);
        return view;

    }


    @Override
    public void onStart() {
        super.onStart();

    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                if (mViewFlipper.isAutoStart()) {

                    in = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
                    out = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
                    mViewFlipper.setInAnimation(in);
                    mViewFlipper.setOutAnimation(out);
                    mViewFlipper.stopFlipping();
                    mViewFlipper.showNext();
                    mViewFlipper.startFlipping();
                    mViewFlipper.setAutoStart(true);

                }

            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                in = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
                out = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
                mViewFlipper.setInAnimation(in);
                mViewFlipper.setOutAnimation(out);
                mViewFlipper.stopFlipping();
                mViewFlipper.showPrevious();
                mViewFlipper.startFlipping();
                mViewFlipper.setAutoStart(true);
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

//        @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mGestureDetector.onTouchEvent(event);
//
//        return super.onTouchEvent(event);
//    }
}


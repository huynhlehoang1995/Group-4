package com.ssense.ssense.ActivityController;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.ssense.ssense.FragmentController.CategoryFragment;
import com.ssense.ssense.FragmentController.CustomerSupportFragment;
import com.ssense.ssense.FragmentController.MySsenseFragment;
import com.ssense.ssense.FragmentController.WishListFragment;
import com.ssense.ssense.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Boolean exit = false;
    String user = "User1";
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
    int[] resources = {
            R.mipmap.image_1,
            R.mipmap.image_2,
            R.mipmap.image_3,
            R.mipmap.image_4,
            R.mipmap.image_5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedItemNavigation(R.id.nav_products);

//        MainScreen-Tri
        imageNoibat1 = (ImageView) findViewById(R.id.imageNoibat1);
        tenNoibat1 = (TextView) findViewById(R.id.tenNoibat1);
        giaNoibat1 = (TextView) findViewById(R.id.giaNoibat1);

        imageNoibat2 = (ImageView) findViewById(R.id.imageNoibat2);
        tenNoibat2 = (TextView) findViewById(R.id.tenNoibat2);
        giaNoibat2 = (TextView) findViewById(R.id.giaNoibat2);

        imageNoibat3 = (ImageView) findViewById(R.id.imageNoibat3);
        tenNoibat3 = (TextView) findViewById(R.id.tenNoibat3);
        giaNoibat3 = (TextView) findViewById(R.id.giaNoibat3);

        imageNoibat4 = (ImageView) findViewById(R.id.imageNoibat4);
        tenNoibat4 = (TextView) findViewById(R.id.tenNoibat4);
        giaNoibat4 = (TextView) findViewById(R.id.giaNoibat4);

        list = (ListView) findViewById(R.id.listV);

        customAdapter = new CustomAdapter(MainActivity.this, arr4);
        list.setAdapter(customAdapter);



        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("sanpham").child("Nam").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SanPham value = dataSnapshot.getValue(SanPham.class);
                arr.add(value);
                if (arr.size() > 4) {
//                    Sản phẩm nổi bật 1
                    String pic1 = arr.get(1).hinh;
                    Picasso.with(getApplicationContext()).load(pic1).into(imageNoibat1);
                    String name1 = arr.get(1).name;
                    tenNoibat1.setText(name1);
                    String gia1 = Float.toString(arr.get(1).gia);
                    giaNoibat1.setText(gia1 + " VND");
//                    Sản phẩm nổi bật 2
                    String pic2 = arr.get(4).hinh2;
                    Picasso.with(getApplicationContext()).load(pic1).into(imageNoibat2);
                    String name2 = arr.get(4).name;
                    tenNoibat2.setText(name2);
                    String gia2 = Float.toString(arr.get(4).gia);
                    giaNoibat2.setText(gia2 + " VND");
                    //                    Sản phẩm nổi bật 3
                    String pic3 = arr.get(0).hinh;
                    Picasso.with(getApplicationContext()).load(pic1).into(imageNoibat3);
                    String name3 = arr.get(0).name;
                    tenNoibat3.setText(name3);
                    String gia3 = Float.toString(arr.get(3).gia);
                    giaNoibat3.setText(gia3 + " VND");
//                    Sản phẩm nổi bật 4
                    String pic4 = arr.get(2).hinh2;
                    Picasso.with(getApplicationContext()).load(pic1).into(imageNoibat4);
                    String name4 = arr.get(2).name;
                    tenNoibat4.setText(name4);
                    String gia4 = Float.toString(arr.get(2).gia);
                    giaNoibat4.setText(gia4 + " VND");

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


        // Get the ViewFlipper
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewflip);

        // Add all the images to the ViewFlipper
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resources[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mViewFlipper.addView(imageView);
        }

        // Set in/out flipping animations
        in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        mViewFlipper.setInAnimation(in);
        mViewFlipper.setOutAnimation(out);

        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(3500);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                if (mViewFlipper.isAutoStart()) {

                    in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
                    out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
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
                in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
                out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            displaySelectedItemNavigation(R.id.nav_wishlist);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        try {
            displaySelectedItemNavigation(id);
        } catch (Exception e) {
            Log.e("Navigation", e.toString());
        }
        return true;
    }


    //Select item on navigation and call fragment into main layout
    public void displaySelectedItemNavigation(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_products:
                fragment = new CategoryFragment();
                break;
            case R.id.nav_ladies:
                fragment = new CategoryFragment();
                break;
            case R.id.nav_men:
                fragment = new CategoryFragment();
                break;
            case R.id.nav_kids:
                fragment = new CategoryFragment();
                break;
            case R.id.nav_magazine:
                Toast.makeText(this, "magazine", Toast.LENGTH_LONG);
                break;
            case R.id.nav_wishlist:
                fragment = new WishListFragment(this.user);
                break;
            case R.id.nav_my_ssense:
                fragment = new MySsenseFragment();
                break;
            case R.id.nav_support:
                fragment = new CustomerSupportFragment();
                break;
            case R.id.nav_find_store:
                Toast.makeText(this, "find store", Toast.LENGTH_LONG);
                break;
            case R.id.nav_newsletter:
                Toast.makeText(this, "News Letter", Toast.LENGTH_LONG);
                break;
        }

        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


}
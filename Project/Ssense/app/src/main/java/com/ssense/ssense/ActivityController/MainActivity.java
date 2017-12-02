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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ssense.ssense.FragmentController.CategoryFragment;
import com.ssense.ssense.FragmentController.CustomerSupportFragment;
import com.ssense.ssense.FragmentController.MySsenseFragment;
import com.ssense.ssense.FragmentController.WishListFragment;
import com.ssense.ssense.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Boolean exit = false;


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
                fragment = new CustomerSupportFragment();
                break;
            case R.id.nav_men:
                fragment = new MySsenseFragment();
                break;
            case R.id.nav_kids:
                fragment = new CategoryFragment();
                break;
            case R.id.nav_magazine:
                Toast.makeText(this, "magazine", Toast.LENGTH_LONG);
                break;
            case R.id.nav_wishlist:
                fragment = new WishListFragment();
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

        if (fragment != null){
            getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


}
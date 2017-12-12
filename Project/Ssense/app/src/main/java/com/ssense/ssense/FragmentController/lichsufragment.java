package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssense.ssense.ActivityController.LoginActivity;
import com.ssense.ssense.CustomAdapter.lichsuAdapter;
import com.ssense.ssense.DataModels.lichsu;
import com.ssense.ssense.R;

import java.util.ArrayList;

/**
 * Created by Vinh on 07-Dec-17.
 */

public class lichsufragment extends Fragment {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    ListView lv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lichsu, container, false);
        lv = view.findViewById(R.id.lvlichsu);


        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {


            final ArrayList<lichsu> products = new ArrayList<>();
            final lichsuAdapter adapter = new lichsuAdapter(getActivity().getBaseContext(),products);
            lv.setAdapter(adapter);
            String s[] = (auth.getCurrentUser().getEmail()).toString().split("@");

            data.child("User").child(s[0]).child("lichsu").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    lichsu value = dataSnapshot.getValue(lichsu.class);
                    Log.d("sss",value.sanpham);
                    products.add(value);
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
        return view;
    }
}

package com.ssense.ssense.DataController;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssense.ssense.DataModels.WishListProduct;

import java.util.ArrayList;

/**
 * Created by PC on 12/7/2017.
 */

public class FirebaseController {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    public ArrayList<WishListProduct> getWishList(String user){
        final ArrayList<WishListProduct> WishList = new ArrayList<>();
        databaseReference.child("Giohang").child(user).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                WishListProduct wishListProduct = dataSnapshot.getValue(WishListProduct.class);
                WishList.add(wishListProduct);
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

        return null;
    }

    public void addOrderForm(){

    }
}

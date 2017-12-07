package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ssense.ssense.ActivityController.LoginActivity;
import com.ssense.ssense.R;


public class MySsenseFragment extends Fragment {
    EditText pass,pass1;
    Button ok;
    EditText diachi,diachi2;
    Button changediachi;
    TextView diachitxt;
    DatabaseReference data;
    FirebaseUser user;
    String s[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_ssense, container, false);
        data = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        pass = view.findViewById(R.id.edtpass);
        pass1 = view.findViewById(R.id.edtpass1);
        ok = view.findViewById(R.id.change);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals(pass1.getText().toString())) {

                    user.updatePassword(pass.getText().toString());
                    Toast.makeText(getActivity(), "Thành công,Đăng nhập lại", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Nhập lại pass không trùng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        diachi = view.findViewById(R.id.diachi);
        changediachi = view.findViewById(R.id.changediachi);
        diachitxt = view.findViewById(R.id.diachitxt);
        s = user.getEmail().split("@");
        data.child("User").child(s[0]).child("diachi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("dd",value);
                diachitxt.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        changediachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = diachi.getText().toString();
                data.child("User").child(s[0]).child("diachi").setValue(a);
            }
        });


        return view;


    }

}

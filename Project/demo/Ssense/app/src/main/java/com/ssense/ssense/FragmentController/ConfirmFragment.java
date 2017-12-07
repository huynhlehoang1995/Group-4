package com.ssense.ssense.FragmentController;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ssense.ssense.DataModels.lichsu;
import com.ssense.ssense.R;

import static com.ssense.ssense.R.layout;


public class ConfirmFragment extends Fragment {
    EditText ten,diachi,note;
    TextView tien,sanpham;
    DatabaseReference data;
    String a,b;
    Button comfirm;
    String s[];

    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_confirm, container, false);
        // Inflate the layout for this fragment

        ten =view.findViewById(R.id.edtName);
        note =view.findViewById(R.id.edtNote);
        diachi = view.findViewById(R.id.edtAddress);
        tien = view.findViewById(R.id.tongtien);
        sanpham = view.findViewById(R.id.sanpham);
        comfirm = view.findViewById(R.id.btnConfirm);
        data = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        a = "Tong tien :  " + getArguments().getString("tien")+ " vnd";
        b = getArguments().getString("sanpham");
        sanpham.setText(b);
        tien.setText(a);
        s = (auth.getCurrentUser().getEmail()).toString().split("@");
        ten.setText(s[0]);
        data.child("User").child(s[0]).child("diachi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                diachi.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu aa =new lichsu();
                aa.ten = ten.getText()+ "";
                aa.diachi=diachi.getText() + "";
                aa.sanpham = sanpham.getText()+"";
                aa.tien = tien.getText()+"";
                aa.tinhtrang = "Đang chờ";
                aa.note = note.getText() + "";
                data.child("User").child(s[0]).child("lichsu").push().setValue(aa);
                data.child("Giohang").child(s[0]).removeValue();
                lichsufragment fragment = new lichsufragment();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
            }
        });



        return view;

    }


}

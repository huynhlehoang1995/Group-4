package com.example.huynh.exchangerate.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynh.exchangerate.R;
import com.example.huynh.exchangerate.model.Money;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText ett9999, ett18k, ettUsd, ettEuro, ettYen;
    private Button btnSave;
    private TextView tvwGraph;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ett9999 = findViewById(R.id.ett_9999);
        ett18k = findViewById(R.id.ett_18k);
        ettUsd = findViewById(R.id.ett_usd);
        ettEuro = findViewById(R.id.ett_euro);
        ettYen = findViewById(R.id.ett_yen);
        btnSave = findViewById(R.id.btn_save);
        tvwGraph = findViewById(R.id.tvw_graph);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("exchange_rate");

        tvwGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Money money = new Money();
                money.au9999 = Long.parseLong(ett9999.getText().toString());
                money.au18k = Long.parseLong(ett18k.getText().toString());
                money.usd = Long.parseLong(ettUsd.getText().toString());
                money.euro = Long.parseLong(ettEuro.getText().toString());
                money.yen = Long.parseLong(ettYen.getText().toString());

                reference.push().setValue(money);

                Toast.makeText(MainActivity.this, "Đã lưu", Toast.LENGTH_SHORT).show();
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Money money = dataSnapshot.getValue(Money.class);

                ett9999.setText(Long.toString(money.au9999));
                ett18k.setText(Long.toString(money.au18k));
                ettUsd.setText(Long.toString(money.usd));
                ettEuro.setText(Long.toString(money.euro));
                ettYen.setText(Long.toString(money.yen));

                GraphActivity.usd.add(money.usd);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Money money = dataSnapshot.getValue(Money.class);

                ett9999.setText(Long.toString(money.au9999));
                ett18k.setText(Long.toString(money.au18k));
                ettUsd.setText(Long.toString(money.usd));
                ettEuro.setText(Long.toString(money.euro));
                ettYen.setText(Long.toString(money.yen));
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

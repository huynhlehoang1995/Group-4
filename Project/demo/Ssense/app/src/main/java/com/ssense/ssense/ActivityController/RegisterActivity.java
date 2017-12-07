package com.ssense.ssense.ActivityController;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssense.ssense.DataModels.User;
import com.ssense.ssense.R;

public class RegisterActivity extends AppCompatActivity {

    TextView tvwLogin;
    Button btnRegister;
    EditText ettEmailReg, ettPasswordReg;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String email = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvwLogin = findViewById(R.id.tvw_login);
        btnRegister = findViewById(R.id.btn_register);
        ettEmailReg = findViewById(R.id.ett_email_reg);
        ettPasswordReg = findViewById(R.id.ett_password_reg);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        tvwLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Register() {

        try {
            email = ettEmailReg.getText().toString();
            password = ettPasswordReg.getText().toString();
        } catch (Exception ex) {

            Toast.makeText(this, ex.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User v = new User();
                            String s[] = email.split("@");
                            v.setName(s[0]);
                            v.setDiachi("sss");

                            reference.child("User").child(s[0]).setValue(v);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.sendEmailVerification();
                            Toast.makeText(RegisterActivity.this, "Đăng kí thành công,Check your email!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

package com.ssense.ssense.ActivityController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssense.ssense.FragmentController.ConnectingFragment;
import com.ssense.ssense.FragmentController.FailConnectFragment;
import com.ssense.ssense.R;

public class ConnectingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_connecting);
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        getFragmentManager().beginTransaction().replace(R.id.statusCheck, new ConnectingFragment()).commit();

                        Thread.sleep(3000);

                        getFragmentManager().beginTransaction().replace(R.id.statusCheck, new FailConnectFragment()).commit();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Intent intent = new Intent(ConnectingActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                }
            };
            thread.start();


    }
}

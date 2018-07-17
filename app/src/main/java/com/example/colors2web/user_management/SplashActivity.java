package com.example.colors2web.user_management;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.colors2web.user_management.Activities.EmployeeActivity;
import com.example.colors2web.user_management.Activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent lint = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(lint);
            }
        }, 2500);
    }
}


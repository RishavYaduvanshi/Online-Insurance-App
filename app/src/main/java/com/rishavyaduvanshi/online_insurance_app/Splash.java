package com.rishavyaduvanshi.online_insurance_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Thread thread =new Thread()
        {
            public void run()
            {
                try {
                    sleep(2000);
                    finish();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(Splash.this, activity_login.class));
                }

            }
        };thread.start();

    }
}
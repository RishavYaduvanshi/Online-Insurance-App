package com.example.insurance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
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
                    startActivity(new Intent(Splash.this,activity_login.class));
                }

            }
        };thread.start();

    }
}
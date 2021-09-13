package com.rishavyaduvanshi.online_insurance_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class claimpage extends AppCompatActivity {
    Button donebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claimpage);
        getSupportActionBar().hide();
        donebtn = findViewById(R.id.donebtn);
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"ThankYou",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
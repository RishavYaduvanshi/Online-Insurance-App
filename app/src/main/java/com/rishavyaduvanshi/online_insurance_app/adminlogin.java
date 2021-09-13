package com.rishavyaduvanshi.online_insurance_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class adminlogin extends AppCompatActivity {
    TextInputEditText adminname,adminpass;
    Button Log;
    TextInputLayout textpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        adminname = findViewById(R.id.adname);
        adminpass = findViewById(R.id.adpass);
        textpass =  findViewById(R.id.textpass);

        Log = findViewById(R.id.adlog);


        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = adminname.getText().toString(),pass = adminpass.getText().toString() ;
                if (name.equals("admin") && pass.equals("pass")) {
                    Toast.makeText(adminlogin.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),admin.class);
                    startActivity(intent);
                    adminname.setText(null);
                    adminpass.setText(null);
                }
                else {
                    textpass.setError("Wrong Password");
                    Toast.makeText(adminlogin.this, "Wrong Password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
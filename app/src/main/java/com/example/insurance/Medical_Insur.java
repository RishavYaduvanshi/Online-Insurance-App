package com.example.insurance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Medical_Insur extends AppCompatActivity {
    DatabaseHelper myDb;
    String year,currentuser,insurType;
    Button save;
    Button paybtn;
    TextView pay;
    EditText mname,mage,mblood;
    int n2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__insur);
        myDb = new DatabaseHelper(this);
        currentuser = getIntent().getStringExtra("curUser");
        insurType = getIntent().getStringExtra("insurType");
        save = findViewById(R.id.btnsavemedi);
        paybtn = findViewById(R.id.paymedi);
        pay = findViewById(R.id.printmedi);
        mname = findViewById(R.id.mname);
        mage = findViewById(R.id.mage);
        mblood = findViewById(R.id.mblood);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mname.getText().toString().isEmpty()||mage.getText().toString().isEmpty()||mblood.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_LONG).show();
                else {
                    saveYear();
                    getPrint();
                }
            }
        });

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Please reopen the app to see updates", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    public void getPrint(){
        pay.setText("You have to pay Rs 5200");
    }
    public void saveYear(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
        Date dateobj = new Date();
        String cd = df.format(dateobj);
        boolean isInserted = myDb.insertYear(1,insurType,currentuser,cd);
        if (isInserted == true) {
            Log.d("insurtype",insurType);
            Log.d("currentuser",currentuser);
            Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
        }
    }
}
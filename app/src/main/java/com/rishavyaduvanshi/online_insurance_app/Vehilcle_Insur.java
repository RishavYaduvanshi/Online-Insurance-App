package com.rishavyaduvanshi.online_insurance_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehilcle_Insur extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatabaseHelper myDb;
    Button save;
    Button paybtn;
    TextView pay;
    String year ;
    int n = 0;
    EditText wheel,vname,mname,bname;
    String currentuser,insurType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        currentuser = getIntent().getStringExtra("curUser");
        insurType = getIntent().getStringExtra("insurType");
        setContentView(R.layout.activity_vehilcle__insur);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        save = findViewById(R.id.btnsave);
        paybtn = findViewById(R.id.payveh);
        pay = findViewById(R.id.print);
        wheel = findViewById(R.id.wheel);
        vname = findViewById(R.id.vname);
        mname = findViewById(R.id.mname);
        bname = findViewById(R.id.bname);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wheel.getText().toString().isEmpty()||vname.getText().toString().isEmpty()||mname.getText().toString().isEmpty()||bname.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_LONG).show();
                else {
                    n++;
                    saveYear();
                    Log.d("clicked", "1");
                    getPrint(year);
                }
            }
        });

            paybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(n>0) {
                        n = 0;
                        Toast.makeText(getApplicationContext(), "Please reopen the app to see updates", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Plese fill the details", Toast.LENGTH_LONG).show();

                    }
                }
            });
    }

    public void getPrint(String year){
        switch(year){
            case "2021": pay.setText("You have to pay Rs 2000");
                break;
            case "2020": pay.setText("You have to pay Rs 1800");
                break;
            case "2019": pay.setText("You have to pay Rs 1600");
                break;
            case "2018": pay.setText("You have to pay Rs 1400");
                break;
            case "2017": pay.setText("You have to pay Rs 1200");
                break;
            case "2016": pay.setText("You have to pay Rs 1000");
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int postion, long l) {
        year = parent.getItemAtPosition(postion).toString();
        //Toast.makeText(parent.getContext(), year, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
            //Toast.makeText(parent.getContext(), "not selected", Toast.LENGTH_SHORT).show();
    }
    public void saveYear(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
        Date dateobj = new Date();
        String cd = df.format(dateobj);
        boolean isInserted = myDb.insertYear(0,insurType,currentuser,cd);
        if (isInserted == true) {
            Log.d("insurtype",insurType);
            Log.d("currentuser",currentuser);
            Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
            Log.d("date",cd);
        }
    }
}

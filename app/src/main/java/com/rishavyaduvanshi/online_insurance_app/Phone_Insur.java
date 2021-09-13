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

public class Phone_Insur extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper myDb;
    String year,currentuser,insurType;
    Button save;
    Button paybtn;
    TextView pay;
    EditText pbrand,pmodel,pname;
    int n1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        currentuser = getIntent().getStringExtra("curUser");
        insurType = getIntent().getStringExtra("insurType");
        setContentView(R.layout.activity_phone__insur);
        Spinner spinner = findViewById(R.id.spinnerphone);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        save = findViewById(R.id.btnsavephone);
        paybtn = findViewById(R.id.payphone);
        pay = findViewById(R.id.printphone);
        pbrand = findViewById(R.id.pbrand);
        pmodel = findViewById(R.id.pmodel);
        pname = findViewById(R.id.pname);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pbrand.getText().toString().isEmpty()||pmodel.getText().toString().isEmpty()||pname.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_LONG).show();
                else {
                    n1++;
                    saveYear();
                    Log.d("clicked", "1");
                    getPrint(year);
                }
            }
        });

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n1>0) {
                    n1 = 0;
                    Toast.makeText(getApplicationContext(), "Please reopen the app to see updates", Toast.LENGTH_LONG).show();
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Plese fill the details", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Toast.makeText(parent.getContext(), "not selected", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int postion, long l) {
        year = parent.getItemAtPosition(postion).toString();
        //Toast.makeText(parent.getContext(), year, Toast.LENGTH_SHORT).show();

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
    public void saveYear(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
        Date dateobj = new Date();
        String cd = df.format(dateobj);
        boolean isInserted = myDb.insertYear(2,insurType,currentuser,cd);
        if (isInserted == true) {
            Log.d("insurtype",insurType);
            Log.d("currentuser",currentuser);
            Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
        }
    }
}
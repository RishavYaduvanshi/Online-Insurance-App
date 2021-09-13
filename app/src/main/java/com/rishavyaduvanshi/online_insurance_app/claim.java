package com.rishavyaduvanshi.online_insurance_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class claim extends AppCompatActivity {
    DatabaseHelper myDb;
    String currentuser ;
    String insurType;
    TextView intype1,intype2,intype3;
    Button vclaimbtn,mclaimbtn,pclaimbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim);
        getSupportActionBar().hide();
        myDb = new DatabaseHelper(this);
        vclaimbtn = findViewById(R.id.vclaimbtn);
        mclaimbtn = findViewById(R.id.mclaimbtn);
        pclaimbtn = findViewById(R.id.pclaimbtn);
        currentuser = getIntent().getStringExtra("curUser");
        insurType = getIntent().getStringExtra("insurType");
        intype1 = findViewById(R.id.intype1);
        intype2 = findViewById(R.id.intype2);
        intype3 = findViewById(R.id.intype3);

        vclaimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), claimpage.class);
                startActivity(intent);
            }
        });
        mclaimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), claimpage.class);
                startActivity(intent);
            }
        });
        pclaimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),claimpage.class);
                startActivity(intent);
            }
        });


        String d1 = myDb.getName1(currentuser);
        if (d1.equals(" ")) {
            intype1.setText("You have not taken Vehicle Insurance");
            vclaimbtn.setVisibility(View.INVISIBLE);

        }
        else{
            String[] ad1 = d1.split("\\s+");
            String[] ed1 = ad1[2].split("/");
            int y1 = Integer.parseInt(ed1[2]);
            y1++;
            String s1 = ad1[0] + " " + ad1[1] + " Will Expire On " + ed1[0] + "/" + ed1[1] + "/" + y1;
            intype1.setText(s1);
        }

        String d2 = myDb.getName2(currentuser);
        if(d2.equals(" ")){
            intype2.setText("You have not taken Medical Insurance");
            mclaimbtn.setVisibility(View.INVISIBLE);

        }
        else{
            String[] ad2 = d2.split("\\s+");
            String[] ed2 = ad2[2].split("/");
            int y2 = Integer.parseInt(ed2[2]);
            y2++;
            String s2 =ad2[0]+" "+ad2[1]+" Will Expire On "+ed2[0]+"/"+ed2[1]+"/"+y2;
            intype2.setText(s2);
        }


        String d3 = myDb.getName3(currentuser);
        if(d3.equals(" ")){
            intype3.setText("You have not taken Phone Insurance");
            pclaimbtn.setVisibility(View.INVISIBLE);
        }
        else{
            String[] ad3 = d3.split("\\s+");
            String[] ed3 = ad3[2].split("/");
            int y3 = Integer.parseInt(ed3[2]);
            y3++;
            String s3 = ad3[0]+" "+ad3[1]+" Will Expire On "+ed3[0]+"/"+ed3[1]+"/"+y3;
            intype3.setText(s3);
        }






    }
}
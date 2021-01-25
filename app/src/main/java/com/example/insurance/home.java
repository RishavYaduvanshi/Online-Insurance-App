package com.example.insurance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {

    String currentuser ;
    String currentpass ;
    Button vehicleInsur,DeleteUser,PersInsur,EditUser,phoneInsur,logout,claim,mydetails;
    TextView showUser,currins;
    DatabaseHelper myDb;
    String currentInur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDb = new DatabaseHelper(this);
        currentuser = getIntent().getStringExtra("USER");
        currentpass = getIntent().getStringExtra("PASSWORD");




        Toast.makeText(home.this,"LOGGED IN AS "+currentuser, Toast.LENGTH_LONG).show();

        vehicleInsur = (Button)findViewById(R.id.vehiclebtn);
        currins = findViewById(R.id.currins);
        PersInsur = (Button)findViewById(R.id.personalbtn);
        DeleteUser = (Button)findViewById(R.id.deluser);
        phoneInsur = (Button)findViewById(R.id.phonebtn);
        showUser = findViewById(R.id.wellcome);
        mydetails = (Button)findViewById(R.id.mydetail);
        EditUser = (Button)findViewById(R.id.edituser);
        logout = findViewById(R.id.logoutbtn);
        claim = findViewById(R.id.claim);
        currentUser(currentuser);
        logout();
        viewData();
        String loggedUser = myDb.getName(currentuser);
        if (loggedUser.length()<9)
            currins.setText("Currently You have no insurance ");
        else
            currins.setText("You have taken "+loggedUser);

        vehicleInsur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Vehilcle_Insur.class);
                intent.putExtra("curUser",currentuser);
                intent.putExtra("insurType","Vehicle Insurance");
                startActivity(intent);
            }
        });

        phoneInsur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Phone_Insur.class);
                intent.putExtra("curUser",currentuser);
                intent.putExtra("insurType","Phone Insurance");
                startActivity(intent);
            }
        });

        PersInsur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Medical_Insur.class);
                intent.putExtra("curUser",currentuser);
                intent.putExtra("insurType","Medical Insurance");
                startActivity(intent);
            }
        });

        claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), claim.class);
                intent.putExtra("curUser",currentuser);
                intent.putExtra("insurType","claim");
                startActivity(intent);
            }
        });

    }
    public void viewData(){
        mydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getUData(currentuser);
                if(res.getCount()==0){
                    //show message
                    showMessage("Error","No Data found");
                    return;
                }
                Log.d("viewdata method",currentuser);
                StringBuffer buffer= new StringBuffer();
                while(res.moveToNext()){
                    //buffer.append("USERID : " +res.getString(0)+"\n");
                    buffer.append("USERNAME : " +res.getString(1)+"\n");
                    buffer.append("PASSWORD : " +res.getString(2)+"\n");
                    buffer.append("INSURANCETYPE1 : \n" + res.getString(3) +"  D.O.I: "+res.getString(6)+ "\n\n");
                    buffer.append("INSURANCETYPE2 : \n" + res.getString(4) +"  D.O.I: "+res.getString(7)+ "\n\n");
                    buffer.append("INSURANCETYPE3 : \n" + res.getString(5) +"  D.O.I: "+res.getString(8)+ "\n\n");
                }
                showMessage("Details",buffer.toString());



            }
        });
    }
    public void currentUser(String name)
    {
        showUser.setText("Welcome "+name);
    }

    public void logout() {
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                SharedPreferences sharedPreferences = getSharedPreferences(activity_login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.apply();

                finish();
            }
        });
    }
    public void showMessage(String title,String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
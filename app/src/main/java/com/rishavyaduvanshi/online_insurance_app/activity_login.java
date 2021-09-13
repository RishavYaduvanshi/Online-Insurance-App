package com.rishavyaduvanshi.online_insurance_app;

//login page

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class activity_login extends AppCompatActivity {
    TextInputEditText username ,password;
    TextInputLayout pass1;
    private int count = 0;
    public static final String keepUser ="name";
    public static final String keepPass ="pass";
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    Button btnlogin,createAccount,adminLogin;

    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseHelper(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        username = findViewById(R.id.userlog);
        password =findViewById(R.id.passlog);
        btnlogin = findViewById(R.id.login);
        createAccount = findViewById(R.id.createbtn);
        pass1 = findViewById(R.id.pass);
        adminLogin = findViewById(R.id.adminbtn);

        autoLogin(sharedpreferences.getString(keepUser,""),sharedpreferences.getString(keepPass,""));
        check();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==3){
                    Toast.makeText(getApplicationContext(),"You Are Now Developer",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), adminlogin.class);
                    startActivity(intent);
                    count = 0;
                }
                count++;
            }
        });

    }
        public void check(){
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = username.getText().toString();
                    String pass = password.getText().toString();


                    if (name.equals("")||pass.equals(""))//repass.equals("")
                        Toast.makeText(activity_login.this,"Enter All details correctly",Toast.LENGTH_LONG).show();
                    else {

                        if (pass!=null) {//pass.equals(repass)
                            boolean checkUser = myDb.checkUserPass(name, pass);
                            if (checkUser == false) {
                                Toast.makeText(activity_login.this, "User Does Not Exist", Toast.LENGTH_LONG).show();
                                pass1.setError("User Does Not Exist");
                            }


                            else{
                                Toast.makeText(activity_login.this, "Login Successful", Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(keepUser, name);
                                editor.putString(keepPass, pass);
                                editor.apply();
                                Intent intent = new Intent(getApplicationContext(),home.class);

                                intent.putExtra("USER",name);
                                intent.putExtra("PASSWORD",pass);
                                username.setText(null);
                                password.setText(null);
                                startActivity(intent);

                            }
                        }
                    }

                }
            });

        }
    public void autoLogin(String name,String pass){
        String user= sharedpreferences.getString(keepUser,"");
        if(user.equals("")){
            Toast.makeText(getApplicationContext(),"No logged In User",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), home.class);

            intent.putExtra("USER",name);
            intent.putExtra("PASSWORD",pass);

            startActivity(intent);

        }
    }




}

        //db = new DatabaseHelper(this);











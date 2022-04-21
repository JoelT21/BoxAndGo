package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Login extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "EXTRA MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        EditText views on the login page
         */
        EditText user = findViewById(R.id.textView_Name);
        EditText password = findViewById(R.id.textView_password);
        Button btn = findViewById(R.id.button3);

        /*
        Login when submit button is clicked
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userCode = user.getText().toString();
                String pCode = password.getText().toString();

                try {
                    byte[] hashed = messageDigest(pCode);

                    //Get hashed password from database
                    DBClass db = new DBClass(Login.this, "Info");
                    String getAll=db.selectAll("username","Info");
                    Log.d("SelectAll","===Retrieve"+getAll);
                    String condition = "username="+'"'+userCode+'"';
                    Log.d("The condition======", "===="+condition);
                    byte[] dbVersion = db.selectPasscode("password", "Info",condition);
Log.d("GetfromDB","===getDb=="+dbVersion);
                    Log.d("GetfromApp","===getpwdApp=="+hashed);

                    //compare passwords
                    if(Arrays.equals(hashed,dbVersion)){
                        Log.d("Login","===password match========================");

                        SharedPreferences sp = getSharedPreferences("Username", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putString("username", userCode);
                        edit.commit();

                        Intent pass = new Intent(Login.this, WelcomePage.class);
                        pass.putExtra(EXTRA_MESSAGE, userCode);
                        startActivity(pass);
                    }else{
                        Log.d("Login","===password mismatch==========================");

                        password.setError("The password entered does not match your username!");
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }



            }
        });
    }


    /*
  MessageDigest hashes the user password
   */
    public byte[] messageDigest(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(s.getBytes(StandardCharsets.UTF_8));
    }
}
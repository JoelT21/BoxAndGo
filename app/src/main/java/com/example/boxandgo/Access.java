package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Access extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        TextView login = (TextView) findViewById(R.id.login);
        TextView signup = (TextView) findViewById(R.id.signup);

        //Send to login page onclick
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Access.this, Login.class));
            }
        });


        //send to signup page onclick
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity( new Intent(Access.this, SignUp.class));
            }
        });
    }
}
package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomePage extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "EXTRA MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Intent in = getIntent();
        String name = in.getStringExtra(Login.EXTRA_MESSAGE);

        DBClass db = new DBClass(WelcomePage.this, "Info");
        String condition =  "username="+'"'+name+'"';
        String username = db.selectVal("name", "Info", condition);

        TextView msg = (TextView) findViewById(R.id.textViewWelcome);
        msg.setText("Welcome " + username + " !");

        //switch to new intent after fade in
        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(WelcomePage.this, Homepage.class));
                finish();
            }
        }, 2000 );

    }
}
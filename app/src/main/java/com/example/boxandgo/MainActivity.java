package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Logo fade in
        ImageView logo = (ImageView) findViewById(R.id.imageView);
        logo.animate().alpha(1f).setDuration(5000);

        //switch to new intent after fade in
        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(MainActivity.this, Access.class));
                finish();
            }
        }, 6000 );
    }


}
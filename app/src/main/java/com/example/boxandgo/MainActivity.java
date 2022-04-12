package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logo = (ImageView) findViewById(R.id.imageView);
        logo.animate().alpha(1f).setDuration(5000);

//        Intent in  = new Intent(MainActivity.this, Access.class);
//        startActivity(in);
    }
}
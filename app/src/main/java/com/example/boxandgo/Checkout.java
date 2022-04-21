package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //Back button
        ImageView img = findViewById(R.id.imageView2);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Checkout.this, Homepage.class);
                startActivity(intent);
            }
        });
        /*
        Text fields
         */

        EditText name = findViewById(R.id.editextCheckFirst);
        EditText card = findViewById(R.id.editextCheckSecond);
        EditText expDate = findViewById(R.id.editextCheckThird);
        EditText cvv = findViewById(R.id.editextCheckFourth);
        EditText addy = findViewById(R.id.editextCheckFifth);
        EditText state = findViewById(R.id.editextCheckSixth);
        EditText date = findViewById(R.id.editextCheckSeventh);
        Button submit = findViewById(R.id.buttonCheck);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_name = name.getText().toString();
                String s_card = card.getText().toString();
                String s_expDate = expDate.getText().toString();
                String s_cvv = cvv.getText().toString();
                String s_addy = addy.getText().toString();
                String s_state = state.getText().toString();
                String s_date = date.getText().toString();

                if(s_name != "" && s_card != "" && s_expDate != "" && s_cvv != "" && s_addy != "" && s_state != "" && s_date != ""){
                    Intent home = new Intent(Checkout.this, Homepage.class);
                    startActivity(home);
                }

            }
        });




    }
}
package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        requestAppPermissions();

        ImageView shoe = findViewById(R.id.imageView3);

        SharedPreferences sp = getSharedPreferences("BuyShoe", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        boolean buy1 = sp.getBoolean("buy1", false);
        boolean buy2 = sp.getBoolean("buy2", false);
        boolean buy3 = sp.getBoolean("buy3", false);
        if(buy1){
            shoe.setImageResource(R.drawable.yeezy_jpg);
            edit.clear();
        }
        else if(buy2){
            shoe.setImageResource(R.drawable.jordan_3_jpy);
            edit.clear();
        }
        else if(buy3){
            shoe.setImageResource(R.drawable.jordan_jpg);
            edit.clear();
        }

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
                Log.d("+++++INSIDE","++++++++");
                String s_name = name.getText().toString();
                String s_card = card.getText().toString();
                String s_expDate = expDate.getText().toString();
                String s_cvv = cvv.getText().toString();
                String s_addy = addy.getText().toString();
                String s_state = state.getText().toString();
                String s_date = date.getText().toString();

                try {
                    //if(s_name != "" && s_card != "" && s_expDate != "" && s_cvv != "" && s_addy != "" && s_state != "" && s_date != ""){
                        writeFile("data.txt", s_name, s_card,s_expDate,s_cvv,s_addy,s_state, s_date);
                        Log.d("Submit", "=======");
                        Intent home = new Intent(Checkout.this, Homepage.class);
                        startActivity(home);
                    //}

                } catch (JSONException e){
                    e.printStackTrace();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Purchase Successful!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestAppPermissions() {
        //  if (hasReadPermissions() && hasWritePermissions()) {
        if (hasWritePermissions()) {

            return;
        }
        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 0);
    }

    public void writeFile(String filename,String name,String card, String expDate, String cvv, String addy, String state, String date) throws JSONException, FileNotFoundException {
        /*
        What is JSONException?
        Thrown to indicate a problem with the JSON API. Such problems include:

            Attempts to parse or construct malformed documents
            Use of null as a name
            Use of numeric types not available to JSON, such as NaNs or infinities.
            Lookups using an out of range index or nonexistent name
            Type mismatches on lookups
         */
        String myDir = Environment.getExternalStorageDirectory() +"/"+filename; //creating a file in the internal storage/Documents folder on phone.
        Log.d("PrintDir","====="+myDir);
        File file = new File(myDir);    //creating a file object
        JSONObject actJSON = new JSONObject();   //create a JSONObject
        actJSON.put("Name",name);
        actJSON.put("Card",card);
        actJSON.put("ExpDate",expDate);
        actJSON.put("cvv", cvv);
        actJSON.put("addy", addy);
        actJSON.put("state",state);
        actJSON.put("date",date);

        //Write to the file and store in internal storage
        FileOutputStream fOut = new FileOutputStream(file, true); //create a file output stream for writing data to file
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);  //converts character stream into byte stream
        try {
            myOutWriter.append(actJSON.toString() + "\n");  //write JSONObject to file
            myOutWriter.close();
            fOut.close();
        }
        catch (Exception e){
            e.printStackTrace();  //to handle exceptions and errors.
        }

    }
}
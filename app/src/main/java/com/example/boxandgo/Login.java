package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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

        requestAppPermissions();
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
                    //if(s_name != "" && s_card != "" && s_expDate != "" && s_cvv != "" && s_addy != "" && s_state != "" && s_date != ""){
                    writeFile("logindata.txt", userCode, pCode);
                    //}
                } catch (JSONException e){
                    e.printStackTrace();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

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

    public void writeFile(String filename,String username,String password) throws JSONException, FileNotFoundException {
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
        actJSON.put("Username",username);
        actJSON.put("Password",password);


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
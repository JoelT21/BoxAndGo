package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         /*
        Fields on the user-registration pages
         */
        EditText name = findViewById(R.id.textViewSignUpName);
        EditText email = findViewById(R.id.emailtxt);
        EditText date_of_birth = findViewById(R.id.dobtxt);
        EditText username = findViewById(R.id.unametxt);
        EditText passcode = findViewById(R.id.pwdtxt);

        Button submit = findViewById(R.id.button2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();
                String mail = email.getText().toString();
                String date_o_b = date_of_birth.getText().toString();
                String un = username.getText().toString();
                String s = passcode.getText().toString();


                if((n != "") && (mail != "") && (date_o_b != "") && (un != "") && (s != "")){

                    boolean validUsername = un.length() >= 5;
                    boolean validPassword = s.length() >= 5;

                    //Hash password
                    if(validPassword && validUsername){
                        byte[]  hashed = new byte[0];
                        try {
                            hashed = messageDigest(s);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }

                        DBClass db = new DBClass(SignUp.this, "Info");
                        //insert data into database
                        db.addInfo(n,un,date_o_b,mail,hashed);
                      //  db.addInfo(n,mail,date_o_b,un,hashed);

                        Intent in = new Intent(SignUp.this, Login.class);
                        startActivity(in);

                    }
                    else{
                        passcode.setError("Length:5 or more");
                        username.setError("Length:5 or more");
                    }

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
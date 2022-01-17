package com.example.osos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //declaring SharedPreferences
    SharedPreferences sharedPreferences;

    public static final String filename="login";
    public static final String Username="Username";
    public static final String Password="Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        //declaring inputfields and button
        EditText mUsername= findViewById(R.id.inputUserName);
        EditText mPassword= findViewById(R.id.inputPassword);
        Button mButton=findViewById(R.id.Login_btn);

        //invloking shared preference object to check if the user is already signed in
        sharedPreferences =getSharedPreferences(filename, Context.MODE_PRIVATE);


        //Validating the username with sharedpreferecne file
        if(sharedPreferences.contains(Username)){
            //if contains navigating to main activity
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        //if the user is not exiting in the sharedpreference file

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String username =mUsername.getText().toString();
               String password =mPassword.getText().toString();

               if(username.equals("123")&&password.equals("123")){
                   SharedPreferences.Editor editor=sharedPreferences.edit();
                   editor.putString(Username,username);
                   editor.putString(Password,password);
                   editor.apply();

                   Toast.makeText(getApplicationContext(),"Successfully LoggedIn",Toast.LENGTH_SHORT).show();

                   Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
                   finish();
               }
               else {
                   //if the user credentials are not invalid , setting username and password field to null and setting error
                   mUsername.setError("invalid username");
                   mUsername.setText(null);
                   mUsername.requestFocus();
                   mPassword.setError("invalid password");
                   mPassword.setText(null);





               }
            }
        });



    }
}
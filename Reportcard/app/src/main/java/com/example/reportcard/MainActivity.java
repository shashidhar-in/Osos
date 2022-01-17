package com.example.reportcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void report(View view) {
        Intent intent=new Intent(this,MarksActivity.class);
        startActivity(intent);
        Toast.makeText(this,"sign in successful!",Toast.LENGTH_SHORT).show();
        finish();

    }
}
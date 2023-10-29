package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_info,btn_hike,btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        link();
        event();
    }

    private void event() {
        Intent intent;
        intent = new Intent(this,AllHikeAcivity.class);
        btn_hike.setOnClickListener(view -> {
            startActivity(intent);
        });
    }

    private void link() {
        btn_add = findViewById(R.id.btn_addHike);
        btn_hike= findViewById(R.id.btn_hike);
        btn_info = findViewById(R.id.btn_information);
    }
}
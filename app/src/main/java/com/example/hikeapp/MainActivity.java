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
        Intent intent, intent2, intent3;
        intent = new Intent(this,AllHikeAcivity.class);
        intent2 = new Intent(this,AddHikeActivity.class);
        intent3 = new Intent(this,InformationActivity.class);

        btn_hike.setOnClickListener(view -> {
            startActivity(intent);
        });
        btn_add.setOnClickListener(view -> {
            startActivity(intent2);
        });
        btn_info.setOnClickListener(view -> {
            startActivity(intent3);
        });
    }

    private void link() {
        btn_add = findViewById(R.id.addHike);
        btn_hike= findViewById(R.id.btn_hike);
        btn_info = findViewById(R.id.btn_information);
    }
}
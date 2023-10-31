package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        Intent intent, intent1, intent2;
        intent = new Intent(this,AllHikeAcivity.class);
        btn_hike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        intent1 = new Intent(this,AddHikeActivity.class);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
        intent2 = new Intent(this, Information.class);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });
    }

    private void link() {
        btn_add = findViewById(R.id.btn_addHike);
        btn_hike= findViewById(R.id.btn_hike);
        btn_info = findViewById(R.id.btn_information);
    }
}
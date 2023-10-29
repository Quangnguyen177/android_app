package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information2);
        link();
        event();
    }
    private void event() {
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void link() {
        btnBack = findViewById(R.id.btn_back);
    }
}
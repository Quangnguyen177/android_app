package com.example.hikeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Information extends AppCompatActivity {

    ImageView btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information2);
        link();
        eventBack();
    }

    private void eventBack() {
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void link() {
        btnBack = findViewById(R.id.btn_back);
    }
}
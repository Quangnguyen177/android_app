package com.example.hikeapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AllHikeAcivity extends AppCompatActivity {
    ImageView btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allhike);
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

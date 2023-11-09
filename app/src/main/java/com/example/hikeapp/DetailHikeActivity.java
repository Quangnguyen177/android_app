package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailHikeActivity extends AppCompatActivity {
    Context context;
    TextView dt_hikename, dt_location, dt_date, dt_packingSpinner, dt_lengthOfHike, dt_difficulty, dt_description;
    Button btn_editHike,btn_addObservation,btn_viewAllOb;
    ImageView btnBack;
    ArrayList<Hike> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hike);
        link();
        eventBack();
        btn_viewAllOb = findViewById(R.id.btn_viewAllOb);
        btn_editHike = findViewById(R.id.btn_editHike);
        btn_addObservation = findViewById(R.id.btn_addObservation);
        dt_hikename = findViewById(R.id.dt_hikename);
        dt_location = findViewById(R.id.dt_observationTime);
        dt_date = findViewById(R.id.dt_additionComment);
        dt_packingSpinner = findViewById(R.id.dt_packingSpinner);
        dt_lengthOfHike = findViewById(R.id.dt_lengthOfHike);
        dt_difficulty = findViewById(R.id.dt_difficulty);
        dt_description = findViewById(R.id.dt_description);

        String idHike = getIntent().getStringExtra("idHike");
        String name = getIntent().getStringExtra("hikeName");
        String location = getIntent().getStringExtra("location");
        String date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");
        String lengthOfHike = getIntent().getStringExtra("lengthOfHike");
        String packingSpinner = getIntent().getStringExtra("packingSpinner");
        String difficulty = getIntent().getStringExtra("difficulty");

        dt_hikename.setText(name);
        dt_location.setText(location);
        dt_date.setText(date);
        dt_description.setText(description);
        dt_lengthOfHike.setText(lengthOfHike);
        dt_packingSpinner.setText(packingSpinner);
        dt_difficulty.setText(difficulty);

        btn_editHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(DetailHikeActivity.this, UpdateHikeActivity.class);
                    intent.putExtra("idHike", idHike);
                    intent.putExtra("hikeName", name);
                    intent.putExtra("location", location);
                    intent.putExtra("date", date);
                    intent.putExtra("description", description);
                    intent.putExtra("lengthOfHike", lengthOfHike);
                    intent.putExtra("packingSpinner", packingSpinner);
                    intent.putExtra("difficulty", difficulty);
                    DetailHikeActivity.this.startActivity(intent);
                } catch (Exception e) {
                    Log.e("onClickEdit: ", e.toString());
                }
            }
        });
        btn_addObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(DetailHikeActivity.this, ObservationActivity.class);
                    intent.putExtra("idHike", idHike);
                    DetailHikeActivity.this.startActivity(intent);
                }catch (Exception e){
                    Log.e("onClickEdit: ", e.toString());
                }
            }
        });
        btn_viewAllOb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailHikeActivity.this, AllObservationActivity.class);
                intent.putExtra("idHike", idHike);
                DetailHikeActivity.this.startActivity(intent);
            }
        });


    }
    private void eventBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void link() {
        btnBack = findViewById(R.id.btn_back);
    }

}
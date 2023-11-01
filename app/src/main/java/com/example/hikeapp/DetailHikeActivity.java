package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailHikeActivity extends AppCompatActivity {
    Context context;
    TextView dt_hikename, dt_location, dt_date, dt_packingSpinner, dt_lengthOfHike, dt_difficulty, dt_description;
    Button btn_editHike;
    ArrayList<Hike> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hike);

        btn_editHike = findViewById(R.id.btn_editHike);
        dt_hikename = findViewById(R.id.dt_hikename);
        dt_location = findViewById(R.id.ed_location);
        dt_date = findViewById(R.id.ed_date);
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
                    Intent intent = new Intent(DetailHikeActivity.this, UpdateHike.class);
                    Log.d( "onClick: ", idHike);
                    intent.putExtra("idHike", idHike);
                    Log.d( "onClick: ", name);
                    intent.putExtra("hikeName", name);
                    Log.d( "onClick: ", location);
                    intent.putExtra("location", location);
                    Log.d( "onClick: ", date);
                    intent.putExtra("date", date);
                    Log.d( "onClick: ", description);
                    intent.putExtra("description", description);
                    Log.d( "onClick: ", lengthOfHike);
                    intent.putExtra("lengthOfHike", lengthOfHike);
                    Log.d( "onClick: ", packingSpinner);
                    intent.putExtra("packingSpinner", packingSpinner);
                    Log.d( "onClick: ", difficulty);
                    intent.putExtra("difficulty", difficulty);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Log.e("onClickEdit: ", e.toString());

                }
            }
        });


    }

}
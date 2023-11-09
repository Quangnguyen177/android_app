package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class AllObservationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText searchOb;
    ArrayList<Observation> myOb;
    DBHelper dbHelper;
    ImageView btnBack, imgOb;
    ObAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_observation);
        String obIdHike = getIntent().getStringExtra("idHike");
        recyclerView = (RecyclerView) findViewById(R.id.rcv_dataOb);
        dbHelper = new DBHelper(this);
        //tao list view
        myOb = new ArrayList<>();
        Cursor cursor = dbHelper.getObbyHike(obIdHike);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Observation observation = new Observation(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            myOb.add(observation);
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("error",myOb.size()+ "");
        myadapter = new ObAdapter(myOb,this, obIdHike);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllObservationActivity.this));
        link();
        eventBack();
    }
    private void eventBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllObservationActivity.this, DetailHikeActivity.class);

            }
        });
    }

    private void link() {
        btnBack = findViewById(R.id.btn_back);
    }
}
package com.example.hikeapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddHikeActivity extends AppCompatActivity {

    //khai bao cac bien giao dien
    EditText hikeName,location,date,description,lengthOfHike;
    Button addHike;
    Spinner packing_spinner;
    RadioGroup difficulty;

    //khai bao array
    ArrayList<String> myList;

    //khai bao database
    SQLiteDatabase mydatabase;
     DBHelper dbHelper;
    ImageView btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hike);
        btnBack = findViewById(R.id.btn_back);
        linkBack();
        eventBack();
        String[] spinnerData = {"True", "False"};
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,spinnerData);
        dbHelper = new DBHelper(this);

        //tham chieu id cho cac bien giao dien
        hikeName = findViewById(R.id.hikeName);
        location =findViewById(R.id.location);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);
        lengthOfHike = findViewById(R.id.lengthOfHike);
        addHike = findViewById(R.id.addHike);
        packing_spinner = findViewById(R.id.packing_spinner);
        difficulty = findViewById(R.id.rg_difficulty);
        packing_spinner.setAdapter(myadapter);

        addHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hike_name = hikeName.getText().toString().trim();
                String _location = location.getText().toString().trim();
                String _date = date.getText().toString().trim();
                String _description = description.getText().toString().trim();
                String _lengthOfHike = lengthOfHike.getText().toString().trim();
                String _packing_spinner = packing_spinner.getSelectedItem().toString();
                int _difficulty = difficulty.getCheckedRadioButtonId();
                RadioButton checkedButton = findViewById(_difficulty);
                String difficulty1 = checkedButton.getText().toString().trim();
                //(String name, String location, String date, String description, String lengthOfHike, String packing_spinner, String difficulty )
                dbHelper.insert_hike(hike_name,_location,_date,_description,_lengthOfHike,_packing_spinner,difficulty1);
            }
        });
    }
    private void eventBack() {
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void linkBack() {
        btnBack = findViewById(R.id.btn_back);
    }


    

}
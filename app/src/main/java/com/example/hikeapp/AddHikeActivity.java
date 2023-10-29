package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddHikeActivity extends AppCompatActivity {
    ImageView btnBack;
    EditText hikeName,location,date,description,lengthOfHike;
    Button addHike;
    Spinner packing_spinner;
    RadioGroup rg_difficulty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hike);
        hikeName = findViewById(R.id.hikeName);
        location = findViewById(R.id.location);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);
        lengthOfHike = findViewById(R.id.lengthOfHike);
        addHike = findViewById(R.id.addHike);
        packing_spinner = findViewById(R.id.packing_spinner);
        rg_difficulty = findViewById(R.id.rg_difficulty);
        String hike_name = hikeName.getText().toString().trim();
        String _location = location.getText().toString().trim();
        String _date = date.getText().toString().trim();
        String _description = description.getText().toString().trim();
        String _lengthOfHike = lengthOfHike.getText().toString().trim();
        String _packing_spinner = packing_spinner.getSelectedItem().toString();
        int _difficulty = rg_difficulty.getCheckedRadioButtonId();
        RadioButton checkedButton = findViewById(_difficulty);

        addHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void link() {
        btnBack = findViewById(R.id.btn_back);
    }

}
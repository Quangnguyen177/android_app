package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateHike extends AppCompatActivity {

    EditText ed_hikeName,ed_location,ed_date,ed_lengthOfHike,ed_description;
    Spinner ed_packingSpinner;
    Button ed_saveHike;
    RadioGroup ed_difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hike);

        String[] spinnerData = {"True", "False"};
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,spinnerData);
        myadapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        ed_packingSpinner.setAdapter(myadapter);

         ed_hikeName = findViewById(R.id.ed_hikeName);
         ed_location = findViewById(R.id.ed_location);
         ed_date = findViewById(R.id.ed_date);
         ed_packingSpinner = findViewById(R.id.ed_packingSpinner);
         ed_lengthOfHike = findViewById(R.id.ed_lengthOfHike);
         ed_difficulty = findViewById(R.id.ed_difficulty);
         ed_description = findViewById(R.id.ed_description);
         ed_saveHike = findViewById(R.id.ed_saveHike);



        String idHike = getIntent().getStringExtra("idHike");
        String name = getIntent().getStringExtra("hikeName");
        String location = getIntent().getStringExtra("location");
        String date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");
        String lengthOfHike = getIntent().getStringExtra("lengthOfHike");
        String packingSpinner = getIntent().getStringExtra("packingSpinner");
        String difficulty = getIntent().getStringExtra("difficulty");

        ed_hikeName.setText(name);
        ed_location.setText(location);
        ed_date.setText(date);
        ed_description.setText(description);
        ed_lengthOfHike.setText(lengthOfHike);
        if(packingSpinner.equals("True")) ed_packingSpinner.setSelection(0);
        else ed_packingSpinner.setSelection(1);
        RadioButton checkedButton;
        switch (difficulty){
            case "Easy":
                checkedButton = findViewById(R.id.rb_easy);
                checkedButton.setChecked(true);
                break;
            case "Normal":
                checkedButton = findViewById(R.id.rb_normal);
                checkedButton.setChecked(true);
                break;
            case "Hard":
                checkedButton = findViewById(R.id.rb_hard);
                checkedButton.setChecked(true);
        }
        Toast.makeText(UpdateHike.this ,"Update successfully", Toast.LENGTH_SHORT).show();

    }
}
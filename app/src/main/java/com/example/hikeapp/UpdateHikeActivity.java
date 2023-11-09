package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateHikeActivity extends AppCompatActivity {

    EditText ed_hikeName,ed_location,ed_date,ed_lengthOfHike,ed_description;
    ImageView btnBack;
    Spinner ed_packingSpinner;
    Button ed_saveHike;
    RadioGroup ed_difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hike);
        link();
        eventBack();

        try{
            ed_hikeName = findViewById(R.id.dt_observationName);
            ed_location = findViewById(R.id.dt_observationTime);
            ed_date = findViewById(R.id.dt_additionComment);
            ed_packingSpinner = findViewById(R.id.ed_packingSpinner);
            ed_lengthOfHike = findViewById(R.id.ed_lengthOfHike);
            ed_difficulty = findViewById(R.id.ed_difficulty);
            ed_description = findViewById(R.id.ed_description);
            ed_saveHike = findViewById(R.id.btn_addHike);
            String[] spinnerData = {"True", "False"};
            ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,spinnerData);
            myadapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            ed_packingSpinner.setAdapter(myadapter);



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


            ed_saveHike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBHelper dbHelper = new DBHelper(UpdateHikeActivity.this);
                    String idHike = getIntent().getStringExtra("idHike");
                    String hike_name = ed_hikeName.getText().toString().trim();
                    String _location = ed_location.getText().toString().trim();
                    String _date = ed_date.getText().toString().trim();
                    String _description = ed_description.getText().toString().trim();
                    String _lengthOfHike = ed_lengthOfHike.getText().toString().trim();
                    String _packing_spinner =ed_packingSpinner.getSelectedItem().toString();
                    int _difficulty = ed_difficulty.getCheckedRadioButtonId();
                    RadioButton checkedButton = findViewById(_difficulty);
                    String difficulty1 = checkedButton.getText().toString().trim();
                    dbHelper.editHikes(idHike,hike_name,_location,_date,_description,_lengthOfHike,_packing_spinner,difficulty1);
                    Toast.makeText(UpdateHikeActivity.this ,"Update successfully", Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch (Exception e){
            Log.e("onClickEdit: ", e.toString());
        }


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
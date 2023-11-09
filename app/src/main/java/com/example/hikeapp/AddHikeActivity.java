package com.example.hikeapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        String[] spinnerData = {"True", "False"};
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,spinnerData);
        dbHelper = new DBHelper(this);
        //tham chieu id cho cac bien giao dien
        hikeName = findViewById(R.id.dt_observationName);
        location =findViewById(R.id.dt_observationTime);
        date = findViewById(R.id.dt_additionComment);
        description = findViewById(R.id.ed_description);
        lengthOfHike = findViewById(R.id.ed_lengthOfHike);
        addHike = findViewById(R.id.btn_addHike);
        packing_spinner = findViewById(R.id.ed_packingSpinner);
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
                if (TextUtils.isEmpty(hike_name)) {
                    hikeName.setError("Hike name is required");
                    return; // Dừng lại và không thực hiện thêm dữ liệu
                }

                if (TextUtils.isEmpty(_location)) {
                    location.setError("Location is required");
                    return;
                }

                if (TextUtils.isEmpty(_date)) {
                    date.setError("Date is required");
                    return;
                }

                if (TextUtils.isEmpty(_lengthOfHike)) {
                    lengthOfHike.setError("Length of Hike is required");
                    return;
                }
                String _packing_spinner = packing_spinner.getSelectedItem().toString();
                int _difficulty = difficulty.getCheckedRadioButtonId();
                RadioButton checkedButton = findViewById(_difficulty);
                String difficulty1 = checkedButton.getText().toString().trim();
                dbHelper.insert_hike(hike_name, _location, _date, _description, _lengthOfHike, _packing_spinner, difficulty1);

            }
        });
    }
//    private void eventBack() {
//        btnBack.setOnClickListener(view -> {
//            onBackPressed();
//        });
//    }
//
//
//
//    private void linkBack() {
//        btnBack = findViewById(R.id.btn_back);
//    }


    

}
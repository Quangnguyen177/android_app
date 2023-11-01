package com.example.hikeapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllHikeAcivity extends AppCompatActivity {
    Context context;
    DBHelper dbHelper;
    ImageView btnBack;
    RecyclerView recyclerView;
    ArrayList<Hike> myList;
    HikeAdapter myadapter;
    EditText searchHike;
    SQLiteDatabase mydatabase;
    Button btn_detailHike,btn_editHike,btn_deleteHike;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allhike);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_data);
        link();
        eventBack();
        //tao list view
        searchHike = findViewById(R.id.searchHike);

        myList = new ArrayList<>();
        dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.getAllHikes();
        cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Hike hike = new Hike(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7)
                    );
                    myList.add(hike);
                    cursor.moveToNext();
                }
                cursor.close();
        Log.i("error",myList.size()+ "");
        myadapter = new HikeAdapter(myList,this);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllHikeAcivity.this));
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

//    @Override
//    public void onClickItem(int position) {
//        Intent intent = new Intent(AllHikeAcivity.this, Hike.class);
//        intent.putExtra("hikeName", myList.get(position).getHikeName());
//        intent.putExtra("location", myList.get(position).getLocation());
//        intent.putExtra("date", myList.get(position).getDate());
//        intent.putExtra("description", myList.get(position).getDescription());
//        intent.putExtra("lengthOfHike", myList.get(position).getLengthOfHike());
//        intent.putExtra("packingSpinner", myList.get(position).getPacking_spinner());
//        intent.putExtra("difficulty", myList.get(position).getDifficulty());
//        startActivity(intent);
//    }
}

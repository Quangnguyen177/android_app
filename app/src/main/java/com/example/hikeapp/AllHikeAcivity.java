package com.example.hikeapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    DBHelper dbHelper;

    ImageView btnBack;
    Button btn_deleteAllHike;
    RecyclerView recyclerView;
    ArrayList<Hike> myList;
    HikeAdapter myadapter;
    EditText txt_searchHike;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allhike);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_data);
        btn_deleteAllHike = findViewById(R.id.btn_deleteAllHike);
        dbHelper = new DBHelper(this);
//        link();
//        eventBack();
        //tao list view
        myList = new ArrayList<>();
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
        txt_searchHike = findViewById(R.id.txt_searchHike);
        txt_searchHike.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {
                    Cursor cursor = dbHelper.searchHike(charSequence.toString());
                    myList.clear();
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
                    myadapter.notifyDataSetChanged();
                    cursor.close();

                }catch (Exception e){
                    Log.e("onTextChanged: ", e.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_deleteAllHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dbHelper.deleleAll();
                    Intent intent = new Intent(AllHikeAcivity.this, AllHikeAcivity.class);
                    startActivity(intent);
            }
        });
//        loadData();
    }
//    private void eventBack() {
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//    }
//
//    private void link() {
//        btnBack = findViewById(R.id.btn_back);
//    }
//    @Override
//    protected void onPostResume() {
//    super.onPostResume();
//    loadData();
//}
//    private void loadData() {
//        Intent intent = getIntent();
//        String hikeId = intent.getStringExtra("hikeId");
//        myadapter = new HikeAdapter( myList,this );
//        recyclerView.setAdapter(myadapter);
//
//    }

}

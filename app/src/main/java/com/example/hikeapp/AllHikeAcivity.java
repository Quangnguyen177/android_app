package com.example.hikeapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
        waitTask wt = new waitTask();
        wt.execute();
    }
    public void updateUIHikes(ArrayList<Hike> hikes){
        Log.d("inside", "updateUI");
//        myadapter = new HikeAdapter(hikes,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(myadapter);
    }
    class waitTask extends AsyncTask<Void,Void,Void>{
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(AllHikeAcivity.this);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            updateUIHikes(myList);
            pd.dismiss();
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

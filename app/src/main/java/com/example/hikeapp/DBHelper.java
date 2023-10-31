package com.example.hikeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    SQLiteDatabase sqLiteDatabase;
    public DBHelper(Context context) {
        super(context, "hike.db", null, 1);
        this.context = context;
       sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            String sql = "CREATE TABLE IF NOT EXISTS  tblHike(idHike INTEGER PRIMARY KEY AUTOINCREMENT,  hikeName TEXT," +
                    "location TEXT," +
                    "date TEXT," +
                    "description TEXT," +
                    "lengthOfHike TEXT," +
                    "packingSpinner TEXT," +
                    "difficulty TEXT)";
            sqLiteDatabase.execSQL(sql);
        }
        catch (Exception e){
            Log.e("Error", "Table was existed");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Hike.TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public long insert_hike(String name, String location, String date, String description, String lengthOfHike, String packing_spinner, String difficulty ){
        ContentValues myvalue = new ContentValues();
        myvalue.put(Hike.COLUMN_NAME, name);
        myvalue.put(Hike.COLUMN_LOCATION, location);
        myvalue.put(Hike.COLUMN_DATE, date);
        myvalue.put(Hike.COLUMN_DESCRIPTION, description);
        myvalue.put(Hike.COLUMN_LENGTHOFHIKE, lengthOfHike);
        myvalue.put(Hike.COLUMN_PACKINGAVAILABLE, packing_spinner);
        myvalue.put(Hike.COLUMN_DIFFICULTY,difficulty);
        String msg = "";

        long insertHike = sqLiteDatabase.insert("tblHike", null, myvalue);
        if( insertHike == -1){
            msg="fail to insert database";
        }
        else{
            msg="insert succesfully ";
        }
        Toast.makeText(context ,msg, Toast.LENGTH_SHORT).show();
        return insertHike;
    }
    public Cursor getAllHikes(){
        String[] column = new String[]{Hike.COLUMN_ID,Hike.COLUMN_NAME,Hike.COLUMN_LOCATION,Hike.COLUMN_DATE,Hike.COLUMN_DESCRIPTION,Hike.COLUMN_LENGTHOFHIKE,Hike.COLUMN_PACKINGAVAILABLE,Hike.COLUMN_DIFFICULTY};
        Cursor cursor = sqLiteDatabase.query("tblHike", null,null,null,null,null,null );
        return cursor;
    }


}

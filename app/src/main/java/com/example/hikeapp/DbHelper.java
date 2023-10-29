package com.example.hikeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context,  "HikeApp"  , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Hike.TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Hike.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public long insert_hike(String name, String location, String date, String description, String lengthOfHike, String packing_spinner, String difficulty ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vl = new ContentValues();
        vl.put(Hike.COLUMN_NAME, name );
        vl.put(Hike.COLUMN_LOCATION, location);
        vl.put(Hike.COLUMN_DATE, date);
        vl.put(Hike.COLUMN_LENGTHOFHIKE, lengthOfHike);
        vl.put(Hike.COLUMN_DESCRIPTION, description);
        vl.put(Hike.COLUMN_DIFFICULTY, difficulty);
        long id = db.insert(Hike.TABLE_NAME, null, vl);
        db.close();
        return id;
    }

}

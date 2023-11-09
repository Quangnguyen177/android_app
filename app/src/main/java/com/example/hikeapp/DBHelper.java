package com.example.hikeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

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
            String sql = "CREATE TABLE IF NOT EXISTS  tblHike(" + "idHike"  +  " INTEGER PRIMARY KEY AUTOINCREMENT,  hikeName TEXT," +
                    "location TEXT," +
                    "date TEXT," +
                    "description TEXT," +
                    "lengthOfHike TEXT," +
                    "packingSpinner TEXT," +
                    "difficulty TEXT)";
            sqLiteDatabase.execSQL(sql);

        String sql1 = "CREATE TABLE IF NOT EXISTS tblObservation ( " +
                "idOb INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idHike INTEGER, " + // Cột khóa ngoại liên kết với bảng tblHike
                "observationName TEXT, " +
                "observationTime TEXT, " +
                "additionComment TEXT, " +
                "imageOb TEXT, " +
                "FOREIGN KEY (idHike) REFERENCES tblHike(idHike) ON DELETE CASCADE " + // Khóa ngoại
                ")";
            sqLiteDatabase.execSQL(sql1);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Hike.TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Observation.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insert_Ob(String observationName, String observationTime, String additionComment, String obIdHike, String imageOb ){
        ContentValues myvalue = new ContentValues();
        myvalue.put(Observation.COLUMN_OBNAME, observationName);
        myvalue.put(Observation.COLUMN_OBTIME, observationTime);
        myvalue.put(Observation.COLUMN_COMMENT, additionComment);
        myvalue.put(Observation.COLUMN_OBHIKEID, obIdHike);
        myvalue.put(Observation.COLUMN_IMAGEOB, imageOb);
        String msg = "";
        long insertOb = sqLiteDatabase.insert("tblObservation", null, myvalue);
        if(insertOb == -1){
            msg = "fail to insert database";
        }
        else{
            msg = "insert successful";
        }
        Toast.makeText(context ,msg, Toast.LENGTH_SHORT).show();
        return  insertOb;
    }

    public Cursor getObbyHike(String hikeId){
        String query = "SELECT * FROM " +Observation.TABLE_NAME+ " WHERE " +Observation.COLUMN_OBHIKEID + " = "  + hikeId;
        Cursor cursor = null;
        if(sqLiteDatabase != null){
            cursor = sqLiteDatabase.rawQuery(query,null);
        }
        return cursor;
    }
    public void deleteOb(String idOb){
        String selection = Observation.COLUMN_OBID+ "=?";
        sqLiteDatabase.delete("tblObservation",selection, new String[]{idOb} );
    }
    public void deleleAll(){
        String query = "DELETE FROM " + Hike.TABLE_NAME;
        sqLiteDatabase.execSQL(query);
    }
    public void editObs(String idOb, String obName, String obTime, String addCmt, String imageOb){
        ContentValues myvalue = new ContentValues();
        myvalue.put(Observation.COLUMN_OBNAME,obName);
        myvalue.put(Observation.COLUMN_OBTIME,obTime);
        myvalue.put(Observation.COLUMN_COMMENT,addCmt);
        myvalue.put(Observation.COLUMN_IMAGEOB, imageOb);
        long editObs = sqLiteDatabase.update("tblObservation", myvalue, Observation.COLUMN_OBID+ "=?", new String[]{idOb});
        String msg = "";
        if( editObs == -1){
            msg="fail to insert database";
        }
        else{
            msg="insert succesfully ";
        }
        Toast.makeText(context ,msg, Toast.LENGTH_SHORT).show();
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
    public void editHikes(String idHike, String name, String location, String date, String description, String lengthOfHike, String packing_spinner, String difficulty){
        ContentValues myvalue = new ContentValues();
        myvalue.put(Hike.COLUMN_NAME, name);
        myvalue.put(Hike.COLUMN_LOCATION, location);
        myvalue.put(Hike.COLUMN_DATE, date);
        myvalue.put(Hike.COLUMN_DESCRIPTION, description);
        myvalue.put(Hike.COLUMN_LENGTHOFHIKE, lengthOfHike);
        myvalue.put(Hike.COLUMN_PACKINGAVAILABLE, packing_spinner);
        myvalue.put(Hike.COLUMN_DIFFICULTY,difficulty);
        long editHikes =sqLiteDatabase.update("tblhike", myvalue, Hike.COLUMN_ID+ "=?", new String[]{idHike});
        String msg = "";
        if( editHikes == -1){
            msg="fail to insert database";
        }
        else{
            msg="insert succesfully ";
        }
        Toast.makeText(context ,msg, Toast.LENGTH_SHORT).show();
    }

     public void deleteHike(String idHike){
        String selection = Hike.COLUMN_ID+ "=?";
        sqLiteDatabase.delete("tblhike",selection, new String[]{idHike} );
    }
    public Cursor searchHike(String searchString){
        Cursor result;
        result = sqLiteDatabase.rawQuery("SELECT * FROM tblHike" + " WHERE hikeName Like '%" + searchString+ "%'", null );
        return result;
    }



}

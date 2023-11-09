package com.example.hikeapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Observation {
    public String observationName;
    public String observationTime;
    public String additionComment;
    public String imageOb;
    public String idOb;
    public String obIdHike;

    public static String TABLE_NAME = "tblObservation";
    public static String COLUMN_OBID = "idOb";
    public static String COLUMN_OBHIKEID = "idHike";
    public static String COLUMN_OBNAME = "observationName";
    public static String COLUMN_OBTIME = "observationTime";
    public static String COLUMN_COMMENT = "additionComment";
    public static String COLUMN_IMAGEOB = "imageOb";

    public String getObIdHike() {
        return obIdHike;
    }

    public void setObIdHike(String obIdHike) {
        this.obIdHike = obIdHike;
    }

    public String getImageOb() {
        return imageOb;
    }

    public void setImageOb(String imageOb) {
        this.imageOb = imageOb;
    }

    public String getIdOb() {
        return idOb;
    }

    public void setIdOb(String idOb) {
        this.idOb = idOb;
    }

    public String getObservationName() {
        return observationName;
    }

    public void setObservationName(String observationName) {
        this.observationName = observationName;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public String getAdditionComment() {
        return additionComment;
    }

    public void setAdditionComment(String additionComment) {
        this.additionComment = additionComment;
    }

    public Observation(String idOb,String obIdHike, String observationName, String observationTime, String additionComment,String imageOb) {
        this.idOb = idOb;
        this.observationName = observationName;
        this.observationTime = observationTime;
        this.additionComment = additionComment;
        this.obIdHike = obIdHike;
        this.imageOb = imageOb;
    }
}

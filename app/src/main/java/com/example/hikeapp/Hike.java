package com.example.hikeapp;

public class Hike {

    public static String TABLE_NAME= "tblHike";
    public static String COLUMN_ID = "idHike";
    public static String COLUMN_NAME = "hikeName";
    public static String COLUMN_LOCATION = "location";
    public static String COLUMN_DATE = "date";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_LENGTHOFHIKE = "lengthOfHike";
    public static String COLUMN_PACKINGAVAILABLE ="packingSpinner";
    public static String COLUMN_DIFFICULTY = "difficulty";


    private String idHike;
    private String hikeName;
    private String location;
    private String date;
    private String description;
    private String lengthOfHike;
    private String packing_spinner;
    private String difficulty;

    public String getIdHike() {
        return idHike;
    }
    public String getHikeName() {
        return hikeName;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLengthOfHike() {
        return lengthOfHike;
    }

    public String getPacking_spinner() {
        return packing_spinner;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLengthOfHike(String lengthOfHike) {
        this.lengthOfHike = lengthOfHike;
    }

    public void setPacking_spinner(String packing_spinner) {
        this.packing_spinner = packing_spinner;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setIdHike(String idHike) {
        this.idHike = idHike;
    }

    public Hike(String idHike, String hikeName, String location, String date, String description, String lengthOfHike, String packing_spinner, String difficulty) {
        this.idHike = idHike;
        this.hikeName = hikeName;
        this.location = location;
        this.date = date;
        this.description = description;
        this.lengthOfHike = lengthOfHike;
        this.packing_spinner = packing_spinner;
        this.difficulty = difficulty;
    }
}

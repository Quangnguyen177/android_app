package com.example.hikeapp;


public class Hike {
    public static String TABLE_NAME = "hike";
    public static String COLUMN_ID = "hikeid";
    public static String COLUMN_NAME = "hikeName";
    public static String COLUMN_LOCATION = "location";
    public static String COLUMN_DATE = "date";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_LENGTHOFHIKE = "lengthOfHike";
    public static String COLUMN_PACKINGAVAILABLE ="packing_spinner";
    public static String COLUMN_DIFFICULTY = "difficulty";
    public static String TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            TABLE_NAME,COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION, COLUMN_DATE, COLUMN_DESCRIPTION, COLUMN_LENGTHOFHIKE,
            COLUMN_PACKINGAVAILABLE,COLUMN_DIFFICULTY );
    private String hikeName;

    private String location;
    private String date;
    private String description;
    private String lengthOfHike;
    private String packing_spinner;
    private String difficulty;




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

    public String isPacking_spinner() {
        return packing_spinner;
    }

    public String getDifficultyRadioGroup() {
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

    public void setDifficultyRadioGroup(String difficultyRadioGroup) {
        this.difficulty = difficultyRadioGroup;
    }

    public Hike(String hikeName, String location, String date, String description, String lengthOfHike, String packing_spinner, String difficultyRadioGroup) {
        this.hikeName = hikeName;
        this.location = location;
        this.date = date;
        this.description = description;
        this.lengthOfHike = lengthOfHike;
        this.packing_spinner = packing_spinner;
        this.difficulty = difficultyRadioGroup;
    }

}


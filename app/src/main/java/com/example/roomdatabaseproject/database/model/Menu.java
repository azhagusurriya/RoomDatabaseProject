package com.example.roomdatabaseproject.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblMenu")
public class Menu {
    /* Entity represents a table like structure for the Room Database.
       @Entity annotation is used to represent the same.
       @PrimaryKey is a mandatory annotation.
       These are some of the commonly used annotations.
       Menu class depicts the tblMenu table in the Room Database.
    */

    @PrimaryKey
    public int Id;
    public String Title;
    public String Description;
    public String Image;
    public Float Ratings;
    public int Price;

}

package com.example.roomdatabaseproject.database.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblCart")
public class Cart {

     /* Entity represents a table like structure for the Room Database.
       @Entity annotation is used to represent the same.
       The @PrimaryKey, @ForeignKey and @Embedded are some additional annotation used.
       These are some of the commonly used annotations.
       Cart class depicts the tblCart table in the Room Database.
    */

    @PrimaryKey(autoGenerate = true)
    public int CartId;
    @ForeignKey(entity = Menu.class, parentColumns = "Id", childColumns = "ItemId", onDelete = ForeignKey.CASCADE)
    public int ItemId;
    @Embedded
    public Menu Item;
    public int Quantity;
    public int TotalPrice;
}

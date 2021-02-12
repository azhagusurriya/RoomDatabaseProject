package com.example.roomdatabaseproject.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabaseproject.database.dao.CartDao;
import com.example.roomdatabaseproject.database.dao.MenuDao;
import com.example.roomdatabaseproject.database.model.Cart;
import com.example.roomdatabaseproject.database.model.Menu;

@Database(entities = {Menu.class, Cart.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    /* @Database annotation is used to represent the class a Database.
     * In this class we have build a database with name dbRoom.
     * This class helps to create a single instance of the class or a single entry point to database.
     */

    private static MyDatabase db;
    /*The database version needs to be upgraded whenever there are changes done in the tables i.e Entity classes.*/

    /*These are the abstract methods which will be used by Repository to access the DAO*/
    public abstract MenuDao menuDao();
    public abstract CartDao cartDao();

    public static MyDatabase getDatabase(Context context){
        if(db == null){

            /* Here we are initializing the db variable. We have used .allowMainThreadQueries to handle the
             * data manipulation in Main Thread.
             */
            db = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "dbRoom")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

}

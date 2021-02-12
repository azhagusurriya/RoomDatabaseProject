package com.example.roomdatabaseproject.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabaseproject.database.model.Menu;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MenuDao extends BaseDao<Menu>{

    /* DAO is a interface defined to write the room queries
     * The annotation @Dao is used for the representation.
     * The @Query, @Insert, @Update, and so on are some more annotations that are commonly used.
     * We are using cart dao to handle the queries related to the Menu Room Table
     */

    /*Query to find all the records from the table.*/
    @Query("SELECT * FROM tblMenu ORDER BY ID asc")
    List<Menu> getList();

    /*Query to insert multiple records at once.*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMenuList(ArrayList<Menu> m);

    /*Query to fetch single record.*/
    @Query("SELECT * FROM tblMenu WHERE Id = :id")
    Menu getMenuItem(int id);

    @Insert
    @Override
    void insert(Menu menu);

    @Update
    @Override
    void update(Menu menu);

    @Delete
    @Override
    void delete(Menu menu);



}

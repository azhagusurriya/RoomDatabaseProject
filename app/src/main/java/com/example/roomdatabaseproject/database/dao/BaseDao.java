package com.example.roomdatabaseproject.database.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BaseDao<T> {


    /* Base DAO is a base interface for all the other DAO interfaces in the project.
     * Reason to create this is all the DAO will have insert, update and delete common.
     * Thus, we can inherit it in other interfaces.
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T t);

    @Delete
    void delete(T t);

    @Update
    void update(T t);


}

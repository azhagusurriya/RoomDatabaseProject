package com.example.roomdatabaseproject.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabaseproject.database.model.Cart;

import java.util.List;

@Dao
public interface CartDao extends BaseDao<Cart> {

    /* DAO is a interface defined to write the room queries
     * The annotation @Dao is used for the representation.
     * The @Query, @Insert, @Update, and so on are some more annotations that are commonly used.
     * We are using cart dao to handle the queries related to the Cart Room Table
     */

    /*We are specifically using the LiveData just so that we can know when the Cart is updated.*/
    @Query("SELECT * FROM tblCart")
    LiveData<List<Cart>> getCartItems();

    @Query("SELECT COUNT(*) FROM tblCart")
    int getCartSize();

    /*Query to insert single record.*/
    @Query("SELECT SUM(TotalPrice) FROM tblCart")
    int getCartTotalPrice();

    /*Query to fetch single record.*/
    @Query("SELECT * FROM tblCart WHERE CartId = :id")
    Cart getCartItem(int id);

    @Insert
    @Override
    void insert(Cart cart);

    @Delete
    @Override
    void delete(Cart cart);

    @Update
    @Override
    void update(Cart cart);

}

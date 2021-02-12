package com.example.roomdatabaseproject.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomdatabaseproject.database.MyDatabase;
import com.example.roomdatabaseproject.database.dao.CartDao;
import com.example.roomdatabaseproject.database.model.Cart;

import java.util.List;

public class CartRepository {
    /*Repositories are the single entry point for the database along with the DAO*/
    /*LiveData is used to observe the changes made on the Cart.*/


    private LiveData<List<Cart>> cartItems;
    private MyDatabase db;
    private CartDao cartDao;

    public CartRepository(Application application){
        db = MyDatabase.getDatabase(application);
        cartDao = db.cartDao();
        cartItems = cartDao.getCartItems();
    }

    public LiveData<List<Cart>> getCartItems(){ return cartItems; };

    public int getCartSize(){ return cartDao.getCartSize(); };

    public int getCartTotalPrice(){ return cartDao.getCartTotalPrice(); };

    public Cart getCartItem(int id){ return cartDao.getCartItem(id); };

    public void insertCartItem(Cart cart){ cartDao.insert(cart);}

    public void updateCartItem(Cart cart){ cartDao.update(cart);}

    public void deleteCartItem(Cart cart){ cartDao.delete(cart);}

}

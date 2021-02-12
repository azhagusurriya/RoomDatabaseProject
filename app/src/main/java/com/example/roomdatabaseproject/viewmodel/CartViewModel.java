package com.example.roomdatabaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdatabaseproject.database.model.Cart;
import com.example.roomdatabaseproject.repository.CartRepository;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    /* Use of ViewModel is in the recommended architecture of the Room Database.
     * It helps to observe if there are any changes done against the table.
     */

    private LiveData<List<Cart>> allCartItems;
    private CartRepository cartRepository;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepository(application);
        allCartItems = cartRepository.getCartItems();
    }

    public LiveData<List<Cart>> getAllCartItems(){ return cartRepository.getCartItems();}

    public int getCartSize(){ return cartRepository.getCartSize(); };

    public int getCartTotalPrice(){ return cartRepository.getCartTotalPrice(); };

    public Cart getCartItem(int id){ return cartRepository.getCartItem(id); };

    public void insertCartItem(Cart cart){ cartRepository.insertCartItem(cart); }

    public void updateCartItem(Cart cart){ cartRepository.updateCartItem(cart);}

    public void deleteCartItem(Cart cart){ cartRepository.deleteCartItem(cart);}
}

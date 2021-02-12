package com.example.roomdatabaseproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.roomdatabaseproject.R;
import com.example.roomdatabaseproject.adapter.MenuAdapter;
import com.example.roomdatabaseproject.database.model.Cart;
import com.example.roomdatabaseproject.database.model.Menu;
import com.example.roomdatabaseproject.repository.MenuRepository;
import com.example.roomdatabaseproject.utils.Utils;
import com.example.roomdatabaseproject.viewmodel.CartViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Menu> menuItems;
    private String LOG = "Room";
    private RecyclerView recyclerview;
    private MenuAdapter adapter;
    private MenuRepository myRepo;
    private Button btnCart;
    private CartViewModel model;
    private List<Cart> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        btnCart = findViewById(R.id.btnCart);


        menuItems = new ArrayList<>();
        myRepo = new MenuRepository(getApplication());
        model = new CartViewModel(getApplication());


        /* If there is no data present in the data. It will load the data from the JSON file.
         * It will also insert everything in the Menu table.
         * Else it will load the data from the database.
         */
        if(myRepo.getItems().size() == 0){
            String menuData = Utils.getAssetJsonData(getApplicationContext());
            Type type = new TypeToken<ArrayList<Menu>>(){}.getType();
            menuItems = new Gson().fromJson(menuData, type);
            myRepo.insertMenu(menuItems);
            Log.i(LOG, "Inserted the items.");
        }else{
            menuItems.addAll(myRepo.getItems());
            Log.i(LOG, "Loaded the items.");
        }

        Log.i(LOG, menuItems.toString());

        adapter = new MenuAdapter(menuItems, this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });


        /*Observing the changes on the Cart.*/
        model.getAllCartItems().observe(this, carts -> {
            btnCart.setText("Cart : "+model.getCartSize());
        });



    }
}
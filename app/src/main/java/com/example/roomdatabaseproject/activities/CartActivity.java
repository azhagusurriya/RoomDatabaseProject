package com.example.roomdatabaseproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.roomdatabaseproject.R;
import com.example.roomdatabaseproject.adapter.CartAdapter;
import com.example.roomdatabaseproject.database.model.Cart;
import com.example.roomdatabaseproject.viewmodel.CartViewModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private String LOG = "Room";
    private TextView tvSubTotal;
    private CartViewModel model;
    private ArrayList<Cart> cartItems = new ArrayList<>();
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        model = new CartViewModel(getApplication());
        helper = new ItemTouchHelper(simpleCallback);

        tvSubTotal = findViewById(R.id.tvSubTotal);
        recyclerView = findViewById(R.id.recyclerview);

        adapter = new CartAdapter(this, cartItems, model);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        helper.attachToRecyclerView(recyclerView);

        /*Observing the changes on the Cart.*/
        model.getAllCartItems().observe(this, carts -> {
            cartItems.removeAll(cartItems);
            cartItems.addAll(model.getAllCartItems().getValue());
            adapter.notifyDataSetChanged();

            tvSubTotal.setText("$"+model.getCartTotalPrice());
        });

    }

    /*Helper to detect the swipe gesture on the recyclerview*/
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            model.deleteCartItem(cartItems.get(viewHolder.getAdapterPosition()));
        }
    };
}
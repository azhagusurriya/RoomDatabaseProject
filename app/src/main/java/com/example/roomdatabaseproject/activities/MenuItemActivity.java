package com.example.roomdatabaseproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabaseproject.R;
import com.example.roomdatabaseproject.database.model.Cart;
import com.example.roomdatabaseproject.database.model.Menu;
import com.example.roomdatabaseproject.repository.MenuRepository;
import com.example.roomdatabaseproject.viewmodel.CartViewModel;
import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    private String LOG = "Room";
    private Menu item;
    private TextView name, desc, price, qty;
    private ImageView img;
    private RatingBar ratingBar;
    private Button btnAdd;
    private ImageButton btnRemoveCart, btnAddCart;
    private LinearLayout qtyLayout;
    private MenuRepository menuRepository;
    private CartViewModel model;
    private Cart cartItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);


        int id = getIntent().getIntExtra("Item", 0);
        Log.i(LOG, "Id is " + id);

        model = new CartViewModel(getApplication());
        menuRepository = new MenuRepository(getApplication());

        name = findViewById(R.id.tvName);
        qty = findViewById(R.id.tvQty);
        desc = findViewById(R.id.tvDescription);
        price = findViewById(R.id.tvPrice);
        img = findViewById(R.id.img);
        ratingBar = findViewById(R.id.ratingBar);
        qtyLayout = findViewById(R.id.qtyLayout);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnRemoveCart = findViewById(R.id.btnRemoveCart);
        btnAdd = findViewById(R.id.btnAdd);
        qtyLayout.setVisibility(View.GONE);

    /* We are checking if the Item is added to the cart or not.*/
        if(model.getCartItem(id)!=null)

    {
        cartItem = model.getCartItem(id);
        item = cartItem.Item;
        setData(true);
    }else

    {
        item = menuRepository.getMenuItem(id);
        setData(false);
    }

    /*Observing the changes on the Cart.*/
        model.getAllCartItems().observe(this,carts ->
    {
        Toast.makeText(this, "Cart is updated.", Toast.LENGTH_SHORT).show();
    });

        btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
        Cart c = new Cart();
        c.ItemId = item.Id;
        c.Item = item;
        c.Quantity = 1;
        c.TotalPrice = item.Price;

        model.insertCartItem(c);
        btnAdd.setVisibility(View.GONE);
        qtyLayout.setVisibility(View.VISIBLE);
        cartItem = c;
    }
    });

        /*Updating the quantity against the item in the cart.*/
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItem.Quantity += 1;
                qty.setText(String.valueOf(cartItem.Quantity));
                cartItem.TotalPrice = cartItem.Quantity * item.Price;
                price.setText("$"+cartItem.TotalPrice);
                model.updateCartItem(cartItem);
            }
        });

        btnRemoveCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartItem.Quantity > 1){
                    cartItem.Quantity -= 1;
                    qty.setText(String.valueOf(cartItem.Quantity));
                    cartItem.TotalPrice = cartItem.Quantity * item.Price;
                    price.setText("$"+cartItem.TotalPrice);
                    model.updateCartItem(cartItem);
                }
            }
        });

}

    private void setData(Boolean isAdded) {
        name.setText(item.Title);
        desc.setText(item.Description);
        price.setText("$"+item.Price);
        qty.setText("1");
        Picasso.get().load(item.Image).into(img);
        ratingBar.setRating(item.Ratings);

        if(isAdded){
            qtyLayout.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
          //  price.setText("$"+cartItem.TotalPrice);
           // qty.setText(String.valueOf(cartItem.Quantity));
        }
    }
}
package com.example.roomdatabaseproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaseproject.R;
import com.example.roomdatabaseproject.database.model.Cart;
import com.example.roomdatabaseproject.database.model.Menu;
import com.example.roomdatabaseproject.viewmodel.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Cart> cartArrayList;
    private CartViewModel model;

    public CartAdapter(Context context, ArrayList<Cart> cartArrayList, CartViewModel model) {
        this.context = context;
        this.cartArrayList = cartArrayList;
        this.model = model;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        Menu item = cartArrayList.get(position).Item;
        Cart cartItem = cartArrayList.get(position);
        holder.name.setText(item.Title);
        holder.price.setText("$"+cartItem.TotalPrice);
        holder.qty.setText(cartItem.Quantity+"");
        Picasso.get().load(item.Image)
                .resize(90, 90)
                .placeholder(R.drawable.placeholder)
                .into(holder.img);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItem.Quantity += 1;
                holder.qty.setText(String.valueOf(cartItem.Quantity));
                cartItem.TotalPrice = cartItem.Quantity * item.Price;
                holder.price.setText("$"+cartItem.TotalPrice);
                model.updateCartItem(cartItem);
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartItem.Quantity > 1){
                    cartItem.Quantity -= 1;
                    holder.qty.setText(String.valueOf(cartItem.Quantity));
                    cartItem.TotalPrice = cartItem.Quantity * item.Price;
                    holder.price.setText("$"+cartItem.TotalPrice);
                    model.updateCartItem(cartItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name,  price, qty;
        private ImageView img;
        private RelativeLayout layout;
        private ImageButton add, remove;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            img = itemView.findViewById(R.id.ivMenuItem);
            qty = itemView.findViewById(R.id.tvQty);
            layout = itemView.findViewById(R.id.layout);
            add = itemView.findViewById(R.id.btnAdd);
            remove = itemView.findViewById(R.id.btnRemove);
        }
    }
}

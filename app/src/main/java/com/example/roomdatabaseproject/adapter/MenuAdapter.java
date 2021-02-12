package com.example.roomdatabaseproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaseproject.R;
import com.example.roomdatabaseproject.activities.MenuItemActivity;
import com.example.roomdatabaseproject.database.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private ArrayList<Menu> menuItem;
    private Context context;

    public MenuAdapter(ArrayList<Menu> menuItem, Context context){
        this.menuItem = menuItem;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {

        Menu item = menuItem.get(position);
        holder.title.setText(item.Title);
        holder.desc.setText(item.Description);
        holder.price.setText("$"+item.Price);

        Picasso.get()
                .load(item.Image)
                .placeholder(R.drawable.placeholder)
                .into(holder.img);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MenuItemActivity.class);
                i.putExtra("Item", item.Id);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return menuItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, desc, price;
        private ImageView img;
        private RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            desc = itemView.findViewById(R.id.tvDescription);
            price = itemView.findViewById(R.id.tvPrice);
            img = itemView.findViewById(R.id.img);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}

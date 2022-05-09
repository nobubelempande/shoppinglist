package com.viii.app.theeight;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList inventory_item_id, inventory_item_name, inventory_item_category, inventory_item_quantity, inventory_item_price, inventory_item_expiry;

    InventoryAdapter(Activity activity, Context context, ArrayList inventory_item_id, ArrayList inventory_item_name, ArrayList inventory_item_category,
                  ArrayList inventory_item_quantity, ArrayList inventory_item_price, ArrayList inventory_item_expiry){
        this.activity = activity;
        this.context = context;
        this.inventory_item_id = inventory_item_id;
        this.inventory_item_name= inventory_item_name;
        this.inventory_item_category = inventory_item_category;
        this.inventory_item_quantity = inventory_item_quantity;
        this.inventory_item_price = inventory_item_price;
        this.inventory_item_expiry = inventory_item_expiry;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_inventory_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryAdapter.MyViewHolder holder, final int position) {
        holder.inventory_item_id_txt.setText(String.valueOf(inventory_item_id.get(position)));
        holder.inventory_item_name_txt.setText(String.valueOf(inventory_item_name.get(position)));
        holder.inventory_item_category_txt.setText(String.valueOf(inventory_item_category.get(position)));
        holder.inventory_item_quantity_txt.setText(String.valueOf(inventory_item_quantity.get(position)));
        holder.inventory_item_price_txt.setText(String.valueOf(inventory_item_price.get(position)));
        holder.inventory_item_expiry_txt.setText(String.valueOf(inventory_item_expiry.get(position)));
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView inventory_item_id_txt, inventory_item_name_txt, inventory_item_category_txt, inventory_item_quantity_txt, inventory_item_price_txt,inventory_item_expiry_txt;
        LinearLayout mainInventoryLayout;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            inventory_item_id_txt = itemView.findViewById(R.id.inventory_item_id_txt);
            inventory_item_name_txt = itemView.findViewById(R.id.inventory_item_name_txt);
            inventory_item_category_txt = itemView.findViewById(R.id.inventory_item_category_txt);
            inventory_item_quantity_txt = itemView.findViewById(R.id.inventory_item_quantity_txt);
            inventory_item_price_txt = itemView.findViewById(R.id.inventory_item_price_txt);
            inventory_item_expiry_txt = itemView.findViewById(R.id.inventory_item_date_txt);
            mainInventoryLayout = itemView.findViewById(R.id.mainInventoryLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainInventoryLayout.setAnimation(translate_anim);
        }
    }

    @Override
    public int getItemCount() {
        return inventory_item_id.size();
    }
}

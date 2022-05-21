package com.viiishoppinglistapp.doit.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.AddNewItem;
import com.viiishoppinglistapp.doit.HomeActivity_old;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.AddShoppingListItemsActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.List;

public class AddingItemsAdapter extends RecyclerView.Adapter<AddingItemsAdapter.ViewHolder> {

    private List<modelItem> allItems;
    private AddShoppingListItemsActivity activity;

    private DatabaseHandler db;

    //constructor
    public AddingItemsAdapter(DatabaseHandler db, AddShoppingListItemsActivity activity){
        this.db = db;
        this.activity = activity;
    }

    public Context getContext() {
        return activity;
    }

    //viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemName, tvItemQty, tvItemType;

        ViewHolder(View view){
            super(view);
            tvItemName = view.findViewById(R.id.tvItemName_new);
            tvItemQty = view.findViewById(R.id.tvItemQty_new);
            tvItemType = view.findViewById(R.id.tvItemType_new);
        }
    }

    //viewHolder methods
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item_layout, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        db.openDatabase();
        modelItem currItem = allItems.get(position);

        holder.tvItemName.setText(currItem.getItemName());
        holder.tvItemQty.setText(" x" + currItem.getItemQty());
        holder.tvItemType.setText(currItem.getItemType());

    }

    public int getItemCount(){
        return allItems.size();
    }

    //setters
    public void setAllItems(List<modelItem> itemsList){
        this.allItems = itemsList;
        notifyDataSetChanged();
    }

    //methods
    public void deleteItem(int position){
        modelItem currItem = allItems.get(position);
        db.deleteItem(currItem.getItemID());
        allItems.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        modelItem currItem = allItems.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", currItem.getItemID());
        bundle.putString("name", currItem.getItemName());
        bundle.putInt("qty", currItem.getItemQty());
        bundle.putString("type", currItem.getItemType());
        //bundle.putDouble("price", currItem.getItemPrice());
        //bundle.putString("doe", currItem.getItemDOE());

        Log.d(HomeActivity_old.TAG, "--Adapter--> item details captured. ItemID : " + currItem.getItemID() + " ****");

        bundle.putString("listName", currItem.getListName());

        AddNewItem fragment = new AddNewItem();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewItem.TAG);

        Log.d(HomeActivity_old.TAG, "--Adapter--> list name captured. Opening Fragment AddNewItem");


    }

}

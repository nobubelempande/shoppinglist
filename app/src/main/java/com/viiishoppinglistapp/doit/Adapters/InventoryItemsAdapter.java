package com.viiishoppinglistapp.doit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.Fragments.fragmentInventoryItems;
import com.viiishoppinglistapp.doit.InventoryActivity;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabsInventoryActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.List;


public class InventoryItemsAdapter extends RecyclerView.Adapter<InventoryItemsAdapter.ViewHolder> {

    private List<modelItem> allInventoryItems;
    private TabsInventoryActivity activity;

    private DatabaseHandler db;

    //constructor
    public InventoryItemsAdapter(DatabaseHandler db, TabsInventoryActivity activity){
        this.db = db;
        this.activity = activity;
    }

    public Context getContext() {
        return activity;
    }

    //viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemName, tvItemQty, tvItemPrice, tvItemDOE;

        ViewHolder(View view){
            super(view);
            tvItemName = view.findViewById(R.id.tvItemName_inventory);
            tvItemQty = view.findViewById(R.id.tvItemQty_inventory);
            tvItemPrice = view.findViewById(R.id.tvItemPrice_inventory);
            tvItemDOE = view.findViewById(R.id.tvItemDOE_inventory);
        }
    }

    //viewHolder methods
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_items_layout, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        modelItem currItem = allInventoryItems.get(position);
        String prc  = String.format("%.2f", currItem.getItemPrice());

        holder.tvItemName.setText(currItem.getItemName());
        holder.tvItemQty.setText(" x" + currItem.getItemQty());
        holder.tvItemPrice.setText("R" + prc);
        holder.tvItemDOE.setText("Expires: \n " + currItem.getItemDOE());

    }

    public int getItemCount(){
        return allInventoryItems.size();
    }

    //setters
    public void setAllInventoryItems(List<modelItem> itemsList){
        this.allInventoryItems = itemsList;
        notifyDataSetChanged();
    }

    //methods
    public void deleteItem(int position){
        modelItem currItem = allInventoryItems.get(position);
        db.deleteInventoryItem(currItem.getItemName());
        allInventoryItems.remove(position);
        notifyItemRemoved(position);
    }

}

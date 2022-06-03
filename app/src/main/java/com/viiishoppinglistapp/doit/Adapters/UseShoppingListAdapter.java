package com.viiishoppinglistapp.doit.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.AddNewInventoryItem;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.UseShoppingListActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.List;

public class UseShoppingListAdapter extends RecyclerView.Adapter<UseShoppingListAdapter.ViewHolder> {

    private List<modelItem> currShoppingList;
    private UseShoppingListActivity activity;

    private DatabaseHandler db;

    //todo use ID

    //constructor
    public UseShoppingListAdapter(DatabaseHandler db, UseShoppingListActivity activity){
        this.db = db;
        this.activity = activity;
    }

    public Context getContext() {
        return activity;
    }

    //viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemQty, tvItemType;
        CheckBox cbItemName;

        ViewHolder(View view){
            super(view);
            cbItemName = view.findViewById(R.id.cbItemName_useList);
            tvItemQty = view.findViewById(R.id.tvItemQty_useList);
            tvItemType = view.findViewById(R.id.tvItemType_useList);
        }
    }

    //viewHolder methods
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout_using_shopping_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(final ViewHolder holder, int position){
        final modelItem currItem = currShoppingList.get(position);

        holder.cbItemName.setText(currItem.getItemName());
        holder.cbItemName.setChecked(currItem.isChecked());
        holder.tvItemType.setText("Type: " + currItem.getItemType());
        holder.tvItemQty.setText(" x" + currItem.getItemQty());

        holder.cbItemName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //adds item to inventory and then deletes it
                if(holder.cbItemName.isChecked()){
                    //checked
                    currItem.setChecked(1);
                    db.updateItem(currItem);

                    Bundle bundle = new Bundle();
                    bundle.putInt("itemID", currItem.getItemID());
                    bundle.putString("itemName", currItem.getItemName());

                    AddNewInventoryItem i = new AddNewInventoryItem();
                    i.newInstance();
                    i.setArguments(bundle);
                    i.show(activity.getSupportFragmentManager(), AddNewInventoryItem.TAG);
                }
                else{
                    //unChecked
                    currItem.setChecked(0);
                    db.updateItem(currItem);
                    //db.deleteInventoryItem(currItem.getItemName());
                }

            }
        });
    }

    public int getItemCount(){
        return currShoppingList.size();
    }

    //setters
    public void setAllShoppingListItems(List<modelItem> itemsList){
        this.currShoppingList = itemsList;
        notifyDataSetChanged();
    }

}
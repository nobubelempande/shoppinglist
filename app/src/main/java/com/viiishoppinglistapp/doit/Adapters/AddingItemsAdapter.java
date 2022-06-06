package com.viiishoppinglistapp.doit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.Activities.AddShoppingListItemsActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.List;

public class AddingItemsAdapter extends RecyclerView.Adapter<AddingItemsAdapter.ViewHolder> {

    private static final String TAG = "ADD ITEMS ADAPTER";
    private List<modelItem> allItems;
    private modelShoppingList currShoppingList;
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

    public AddShoppingListItemsActivity getActivity() {
        return activity;
    }

    public List<modelItem> getAllItems() {
        return  allItems;
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout_shopping_list_item, parent, false);

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
    public void setAllItems(List<modelItem> itemsList, modelShoppingList currShoppingList){
        this.allItems = itemsList;
        this.currShoppingList = currShoppingList;
        notifyDataSetChanged();
    }

    //methods
    public void deleteItem(int position){
        modelItem currItem = allItems.get(position);
        db.deleteItem(currItem.getItemID());
        allItems.remove(position);
        notifyItemRemoved(position);
    }

    public modelShoppingList getCurrShoppingList(){
        return currShoppingList;
    }

}

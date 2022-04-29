package net.penguincoders.doit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.penguincoders.doit.Model.mItems;
import net.penguincoders.doit.R;
import net.penguincoders.doit.Utils.DatabaseHandler;
import net.penguincoders.doit.itemAddingActivity;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private DatabaseHandler db;
    private itemAddingActivity activity;

    private List<mItems> allItems;

    public ItemAdapter(DatabaseHandler db, itemAddingActivity activity) {
        this.db = db;
        this.activity = activity;
    }

    public Context getContext() {
        return activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //responsible for inflating views
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);      //layout with cardViews
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        db.openDatabase();

        setUpItems(holder, position);
    }

    @Override
    public int getItemCount() {
        //total number of items to be shown
        // = number of items in list (classList)
        //----> return todoList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //set bundle // currModel
        //curr items view

        CheckBox item;
        TextView quantity;
        TextView category;


        //constructor

        ViewHolder(View view) {     //use bundle? use currModel
            super(view);

            //get currModel // bundle for global use
            item = view.findViewById(R.id.itemCheckBox);
            category = view.findViewById(R.id.category);
            quantity = view.findViewById(R.id.itemQuan);
        }
    }

    public void setUpItems(final ItemAdapter.ViewHolder holder, int position){}
}

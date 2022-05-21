package com.viiishoppinglistapp.doit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.HomeActivity_old;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.List;

public class UsedShoppingListsAdapter extends RecyclerView.Adapter<UsedShoppingListsAdapter.ViewHolder>{

    private DatabaseHandler db;
    private HomeActivity_old activity;


    //models
    private List<modelShoppingList> allShoppingLists;

    //constructor
    public UsedShoppingListsAdapter(DatabaseHandler db, HomeActivity_old activity) {
        this.db = db;
        this.activity = activity;

    }

    public void setAllUsedShoppingLists(List<modelShoppingList> allLists) {
        this.allShoppingLists = allLists;
        notifyDataSetChanged();     //updates recycler view
    }

    public Context getContext() {
        return activity;
    }

    @Override
    public int getItemCount() {
        //total number of items to be shown
        // = number of items in list (classList)
        //----> return todoList.size();
        return allShoppingLists.size();
    }

    //required

    //ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCurrShoppingList, tvUseDate;
        RelativeLayout lyt;

        //constructor
        //toDo different constructor
        ViewHolder(View view) {
            super(view);

            tvCurrShoppingList = view.findViewById(R.id.tv);
            tvUseDate = view.findViewById(R.id.tv);
            lyt = view.findViewById(R.id.lyt);
        }

    }


    @NonNull
    @Override
    public UsedShoppingListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //responsible for inflating views

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout_used_shopping_list, parent, false);      //layout with cardViews
        return new UsedShoppingListsAdapter.ViewHolder(itemView);
    }

    @Override   //onCreate for layout
    public void onBindViewHolder(@NonNull final UsedShoppingListsAdapter.ViewHolder holder, int position) {
        //method called when item is added to task layout
        db.openDatabase();
        //setting up item details to show on checkbox

        //---SavedShoppingLists
        setupUsedShoppingList(holder, position);

    }

}

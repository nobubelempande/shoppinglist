package com.viiishoppinglistapp.doit.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.AddNewShoppingList;
import com.viiishoppinglistapp.doit.AddShoppingListItemsActivity;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedHomeActivity;
import com.viiishoppinglistapp.doit.UseShoppingListActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.List;

public class UsedShoppingListsAdapter extends RecyclerView.Adapter<UsedShoppingListsAdapter.ViewHolder>{

    private DatabaseHandler db;
    private TabbedHomeActivity activity;


    //models
    private List<modelShoppingList> allShoppingLists;

    //constructor
    public UsedShoppingListsAdapter(DatabaseHandler db, TabbedHomeActivity activity) {
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

        TextView tvName, tvUseDate;
        RelativeLayout lyt;

        //constructor
        ViewHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tvUsedListName_layout);
            tvUseDate = view.findViewById(R.id.tvUsedListUseDate_layout);
            lyt = view.findViewById(R.id.lytUsedShoppingList);
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
        setupUsedShoppingLists(holder, position);

    }

    //Functions :

    public void setupUsedShoppingLists(final UsedShoppingListsAdapter.ViewHolder holder, int position){
        final modelShoppingList currList = allShoppingLists.get(position);
        final String listName = currList.getListName();
        final String useDate = currList.getUseDate();

        holder.tvName.setText(listName);
        holder.tvUseDate.setText(useDate);
        holder.lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_used_shopping_list);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                //button functions
                Button btnViewItems = dialog.findViewById(R.id.btnViewItems_usedList);
                Button btnToCurrentLists = dialog.findViewById(R.id.btnToCurrent_usedList);

                btnViewItems.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //goto new page
                        Bundle bundle = new Bundle();
                        bundle.putString("list_name", listName);
                        Intent I = new Intent(getContext(), AddShoppingListItemsActivity.class);
                        I.putExtras(bundle);
                        getContext().startActivity(I);

                        //Toast.makeText(getContext(), "opening shopping list: " + listName, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btnToCurrentLists.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //change to unused
                        currList.setToUnused();
                        db.updateShoppingList(currList);
                        //notify dataChange
                        activity.handleDialogClose(dialog);

                        //Toast.makeText(getContext(), "opening shopping list: " + listName, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


    }

    public void deleteShoppingList(int position) {
        modelShoppingList currList = allShoppingLists.get(position);
        db.deleteShoppingList(currList.getListID());
        allShoppingLists.remove(position);
        notifyItemRemoved(position);
    }

    public void editShoppingList(int position) {
        modelShoppingList currList = allShoppingLists.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", currList.getListID());
        bundle.putString("name", currList.getListName());
        bundle.putString("useDate", currList.getUseDate());
        AddNewShoppingList fragment = new AddNewShoppingList();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewShoppingList.TAG);
    }


}

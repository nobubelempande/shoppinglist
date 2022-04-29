package net.penguincoders.doit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.penguincoders.doit.AddNewTask;
import net.penguincoders.doit.MainActivity;
import net.penguincoders.doit.Model.mShoppingList;
import net.penguincoders.doit.R;
import net.penguincoders.doit.Utils.DatabaseHandler;
import net.penguincoders.doit.activitySelectedShoppingList;
import net.penguincoders.doit.itemAddingActivity;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private DatabaseHandler db;
    private MainActivity activity;

    //own
    private List<mShoppingList> allShoppingLists;


    //constructor
    public ToDoAdapter(DatabaseHandler db, MainActivity activity) {
        this.db = db;
        this.activity = activity;
    }


    //required

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //responsible for inflating views
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);      //layout with cardViews
        return new ViewHolder(itemView);
    }

    @Override   //onCreate for layout
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //method called when item is added to task layout
        db.openDatabase();
        //setting up item details to show on checkbox

        //---Lists
        setUpListItem(holder, position);

    }


    //ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //set bundle // currModel
        //curr items view

        CheckBox task;


        //constructor

        ViewHolder(View view) {     //use bundle? use currModel
            super(view);

            //get currModel // bundle for global use
            task = view.findViewById(R.id.todoCheckBox);
        }
    }



    //Lists :

    public void setAllShoppingLists(List<mShoppingList> allLists) {
        this.allShoppingLists = allLists;
        notifyDataSetChanged();
    }

    public void setUpListItem(final ViewHolder holder, int position){

        final mShoppingList currList = allShoppingLists.get(position);
        final String listName = currList.getList_name();

        holder.task.setText(listName);
        //toDO use textViews
        // ----view properties----
        holder.task.setChecked(false);
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //own -- goto new page?
                    Bundle bundle = new Bundle();
                   bundle.putString("list_name", listName);
                    Intent I = new Intent(getContext(), itemAddingActivity.class);
                    I.putExtras(bundle);
                    getContext().startActivity(I);
                    Toast.makeText(getContext(), "opening shopping list: " + listName, Toast.LENGTH_SHORT).show();

                    //db.updateStatus(item.getId(), 1);
                } else {
                    //db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public void deleteShoppingList(int position) {
        mShoppingList currList = allShoppingLists.get(position);
        db.deleteShoppingList(currList.getList_id());
        allShoppingLists.remove(position);
        notifyItemRemoved(position);
    }

    public void editShoppingList(int position) {
        mShoppingList currList = allShoppingLists.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", currList.getList_id());
        bundle.putString("name", currList.getList_name());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

}

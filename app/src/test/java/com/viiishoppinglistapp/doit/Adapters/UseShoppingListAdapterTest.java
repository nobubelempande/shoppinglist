package com.viiishoppinglistapp.doit.Adapters;

import static org.junit.Assert.*;

import android.content.Context;

import com.viiishoppinglistapp.doit.Activities.UseShoppingListActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import org.junit.Test;

public class UseShoppingListAdapterTest {

    @Test
    public void getContext() {
        Context context=new UseShoppingListActivity();
        UseShoppingListActivity activity=new UseShoppingListActivity();
        DatabaseHandler db= new DatabaseHandler(context);
        UseShoppingListAdapter m= new UseShoppingListAdapter(db,activity);
        assertEquals(activity,m.getContext());
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {

    }

    @Test
    public void getItemCount() {
    }

    @Test
    public void setAllShoppingListItems() {
    }

    @Test
    public void deleteShoppingListItem() {
    }
}
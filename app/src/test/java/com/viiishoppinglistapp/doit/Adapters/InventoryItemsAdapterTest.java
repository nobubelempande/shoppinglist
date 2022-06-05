package com.viiishoppinglistapp.doit.Adapters;

import static org.junit.Assert.*;

import android.content.Context;

import com.viiishoppinglistapp.doit.AddShoppingListItemsActivity;

import com.viiishoppinglistapp.doit.TabbedInventoryActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import org.junit.Test;



public class InventoryItemsAdapterTest {

    @Test
    public void getContext() {
        Context context= new AddShoppingListItemsActivity();
        DatabaseHandler db= new DatabaseHandler(context);
        assertNotNull(db);
        TabbedInventoryActivity activity=new TabbedInventoryActivity();
        InventoryItemsAdapter m=new InventoryItemsAdapter(db,activity);
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
    public void setAllInventoryItems() {
    }

    @Test
    public void deleteItem() {
    }
}
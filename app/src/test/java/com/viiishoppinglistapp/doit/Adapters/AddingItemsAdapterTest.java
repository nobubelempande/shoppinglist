package com.viiishoppinglistapp.doit.Adapters;



import android.content.Context;

import com.viiishoppinglistapp.doit.AddShoppingListItemsActivity;

import com.viiishoppinglistapp.doit.Model.Model;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import junit.framework.TestCase;


import org.junit.Before;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;


@RunWith(JUnit4.class)
public class AddingItemsAdapterTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }



    @Test
    public void getContext() {
        Context context= new AddShoppingListItemsActivity();
        DatabaseHandler db= new DatabaseHandler(context);
        assertNotNull(db);
        AddShoppingListItemsActivity activity=new AddShoppingListItemsActivity();
        AddingItemsAdapter m=new AddingItemsAdapter(db,activity);
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
    public void setAllItems() {
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void editItem() {
    }
    @Test
    public void getActivity(){
        Context context= new AddShoppingListItemsActivity();
        DatabaseHandler db= new DatabaseHandler(context);
        assertNotNull(db);
        AddShoppingListItemsActivity activity=new AddShoppingListItemsActivity();
        AddingItemsAdapter m=new AddingItemsAdapter(db,activity);
        assertEquals(activity,m.getActivity());
    }
    @Test
    public void getAllitems(){


    }
}
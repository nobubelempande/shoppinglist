package com.viiishoppinglistapp.doit.Adapters;

import static org.junit.Assert.*;

import android.content.Context;

import com.viiishoppinglistapp.doit.TabbedHomeActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class UsedShoppingListsAdapterTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void setAllUsedShoppingLists() {
    }

    @Test
    public void getContext() {
        Context context=new TabbedHomeActivity();
        TabbedHomeActivity activity=new TabbedHomeActivity();
        DatabaseHandler db= new DatabaseHandler(context);

        UsedShoppingListsAdapter m= new UsedShoppingListsAdapter(db,activity);
        assertEquals(activity,m.getContext());
    }

    @Test
    public void getItemCount() {
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void setupUsedShoppingLists() {
    }

    @Test
    public void deleteShoppingList() {
    }

    @Test
    public void editShoppingList() {
    }
}
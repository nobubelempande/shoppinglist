package com.viiishoppinglistapp.doit.Adapters;

import android.content.Context;

import com.viiishoppinglistapp.doit.Activities.TabbedHomeActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class UnusedShoppingListsAdapterTest extends TestCase {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void setAllUnusedShoppingLists() {
    }

    @Test
    public void getContext() {
        Context context= new TabbedHomeActivity();
        DatabaseHandler db= new DatabaseHandler(context);
        assertNotNull(db);

        TabbedHomeActivity activity=new TabbedHomeActivity();
        UnusedShoppingListsAdapter m=new UnusedShoppingListsAdapter(db,activity);
        assertEquals(activity,m.getContext());
    }

    @Ignore
    public void getItemCount() {
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void setupUnusedShoppingLists() {
    }

    @Test
    public void deleteShoppingList() {
    }

    @Test
    public void editShoppingList() {
    }
}
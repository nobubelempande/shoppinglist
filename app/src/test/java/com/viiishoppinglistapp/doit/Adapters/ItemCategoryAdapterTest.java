package com.viiishoppinglistapp.doit.Adapters;



import android.app.Activity;
import android.content.Context;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
@RunWith(JUnit4.class)
public class ItemCategoryAdapterTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void getItemCount() {
        Context context=new Activity();

        Activity activity=new Activity();
        ArrayList<Integer> item_quantity=new ArrayList<>();
        ArrayList<Integer> item_id = new ArrayList<>();
        ArrayList<String> item_name=new ArrayList<>();
        ArrayList<String> item_category=new ArrayList<>();
        int i=0;
        item_id.add(i);
        ItemCategoryAdapter m= new ItemCategoryAdapter(activity,context,item_id,item_name,item_category,item_quantity);
        assertEquals(1,m.getItemCount());
    }
}
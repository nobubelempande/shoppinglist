package com.viiishoppinglistapp.doit.ui.inventory;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.viiishoppinglistapp.doit.Activities.TabbedInventoryActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class InventorySectionsPagerAdapterTest extends TestCase {
    @Before
    public void setUp() throws Exception{
        super.setUp();
    }
    @Test
    public void getItem() {
    }

    @Test
    public void getPageTitle() {
    }

    @Test
    public void getCount() {
        Context context=new TabbedInventoryActivity();
        TabbedInventoryActivity s=new TabbedInventoryActivity();
        FragmentManager p= s.getSupportFragmentManager();
        InventorySectionsPagerAdapter m=new InventorySectionsPagerAdapter(context,p,s);
        m.getCount();
        assertEquals(2,m.getCount());

    }

    @Test
    public void getItemPosition() {
    }
}
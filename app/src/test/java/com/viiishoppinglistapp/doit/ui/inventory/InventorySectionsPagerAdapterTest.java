package com.viiishoppinglistapp.doit.ui.inventory;

import static org.junit.Assert.assertNotEquals;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.viiishoppinglistapp.doit.Activities.TabbedInventoryActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Ignore;
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
        Context context=new TabbedInventoryActivity();
        TabbedInventoryActivity s=new TabbedInventoryActivity();
        FragmentManager p= s.getSupportFragmentManager();
        InventorySectionsPagerAdapter m=new InventorySectionsPagerAdapter(context,p,s);
        m.getItem(0);
        assertNotNull(m.getItem(0));

    }

    @Ignore("To do")
    public void getPageTitle() {
        Context context=new TabbedInventoryActivity();
        TabbedInventoryActivity s=new TabbedInventoryActivity();
        FragmentManager p= s.getSupportFragmentManager();
        InventorySectionsPagerAdapter m=new InventorySectionsPagerAdapter(context,p,s);
        m.getPageTitle(0);
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
        Context context=new TabbedInventoryActivity();
        TabbedInventoryActivity s=new TabbedInventoryActivity();
        FragmentManager p= s.getSupportFragmentManager();
        InventorySectionsPagerAdapter m=new InventorySectionsPagerAdapter(context,p,s);
        Object r= new Object();
        m.getItemPosition(r);
        assertNotEquals(0,m.getItemPosition(r));
    }
}
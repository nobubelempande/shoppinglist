package com.viiishoppinglistapp.doit.ui.home;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.viiishoppinglistapp.doit.Activities.TabbedHomeActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HomeSectionsPagerAdapterTest extends TestCase {
    @Before
    public void setUp() throws Exception{
        super.setUp();
    }

    @Test
    public void getItem() {
        Context context=new TabbedHomeActivity();
        TabbedHomeActivity s=new TabbedHomeActivity();
        FragmentManager p= s.getSupportFragmentManager();
        HomeSectionsPagerAdapter m=new HomeSectionsPagerAdapter(context,p,s);
        m.getItem(0);
        assertNotNull(m.getItem(0));
    }

    @Ignore("To do")
    public void getPageTitle() {
        Context context=new TabbedHomeActivity();
        TabbedHomeActivity s=new TabbedHomeActivity();
        FragmentManager p= s.getSupportFragmentManager();
        HomeSectionsPagerAdapter m=new HomeSectionsPagerAdapter(context,p,s);
        m.getPageTitle(0);
    }

    @Test
    public void getCount() {
        Context context=new TabbedHomeActivity();

        TabbedHomeActivity s=new TabbedHomeActivity();
        FragmentManager p= s.getSupportFragmentManager();
        HomeSectionsPagerAdapter m=new HomeSectionsPagerAdapter(context,p,s);
        m.getCount();
        assertEquals(2,m.getCount());
    }
}
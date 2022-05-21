package com.viiishoppinglistapp.doit.Model;



import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class modelItemTest extends TestCase {

    modelItem mi=new modelItem("Default");
    public void setUp() throws Exception {
        super.setUp();
    }



    @Test
    public void testGetItemID() {
        int itemid=1;
        modelItem m=new modelItem("Default");
        m.setItemID(itemid);
        assertEquals(itemid,m.getItemID());

    }


    @Test
    public void testSetItemID() {
        int itemid=1;
        modelItem m=new modelItem("Default");
        m.setItemID(itemid);
        assertEquals(m.getItemID(), itemid);

    }

    public void tearDown() {
    }
}
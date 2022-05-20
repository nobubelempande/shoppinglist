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
        mi.setItemID(itemid);
        assertEquals(itemid,mi.getItemID());

    }

    public void testGetItemQty() {
    }

    public void testGetItemName() {
    }

    public void testGetListName() {
    }

    public void testGetItemType() {
    }

    public void testGetItemPrice() {
    }

    public void testGetItemDOE() {
    }

    public void testIsUsed() {
    }

    public void testSetItemID() {

    }

    public void testSetItemQty() {
    }

    public void testSetItemName() {
    }

    public void testSetListName() {
    }

    public void testSetItemType() {
    }

    public void testSetItemPrice() {
    }

    public void testSetItemDOE() {
    }

    public void testSetUsed() {
    }
    public void tearDown() {
    }
}
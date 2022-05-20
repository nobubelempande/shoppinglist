package com.viiishoppinglistapp.doit.Model;

import junit.framework.TestCase;



public class modelItemTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }
    String name="Padisho";
    modelItem mi=new modelItem(name);
    int itemid=1;


    public void testGetItemID() {
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
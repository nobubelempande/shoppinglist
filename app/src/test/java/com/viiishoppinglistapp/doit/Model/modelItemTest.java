package com.viiishoppinglistapp.doit.Model;

import junit.framework.TestCase;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class modelItemTest extends TestCase {

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
    public void testGetItemQty() {
        int itemqty=1;
        modelItem m=new modelItem("Default");
        m.setItemQty(itemqty);
        assertEquals(itemqty,m.getItemQty());
    }

    @Test
    public void testGetItemName() {
        String itemName="padisho";
        modelItem m=new modelItem("Default");
        m.setItemName(itemName);
        assertEquals(itemName,m.getItemName());
    }

    @Test
    public void testGetListName() {
        String listName="padisho";
        modelItem m=new modelItem("Default");
        m.setListName(listName);
        assertEquals(listName,m.getListName());
    }
    @Test
    public void testGetItemType() {
        String itemType="food";
        modelItem m=new modelItem("Default");
        m.setItemType(itemType);
        assertEquals(itemType,m.getItemType());
    }

    @Test
    public void testGetItemPrice() {
        double itemPrice=1.0;
        modelItem m=new modelItem("Default");
        m.setItemPrice(itemPrice);
        assertEquals(itemPrice,m.getItemPrice());
    }

    @Test
    public void testGetItemDOE() {
        String itemDOE="padisho";
        modelItem m=new modelItem("Default");
        m.setItemDOE(itemDOE);
        assertEquals(itemDOE,m.getItemDOE());
    }

    @Ignore
    public void testIsUsed() {
        int used=0;
        boolean use=false;
        modelItem s=new modelItem("Default");
        //s.setUsed(used);
        //assertThat(s.isUsed()).isEqualTo(use);
    }

    @Test
    public void testSetItemID() {
        int itemid=1;
        modelItem m=new modelItem("Default");
        m.setItemID(itemid);
        assertEquals(m.getItemID(), itemid);

    }

    @Test
    public void testSetItemQty() {
        int itemqty=1;
        modelItem m=new modelItem("Default");
        m.setItemQty(itemqty);
        assertEquals(m.getItemQty(), itemqty);
    }

    @Test
    public void testSetItemName() {
        String itemName="Padisho";
        modelItem m=new modelItem("Default");
        m.setItemName(itemName);
        assertEquals(m.getItemName(), itemName);
    }

    @Test
    public void testSetListName() {
        String listName="Padisho";
        modelItem m=new modelItem("Default");
        m.setListName(listName);
        assertEquals(m.getListName(), listName);
    }

    @Test
    public void testSetItemType() {
        String itemType="food";
        modelItem m=new modelItem("Default");
        m.setItemType(itemType);
        assertEquals(m.getItemType(), itemType);
    }

    @Test
    public void testSetItemPrice() {
        double itemPrice=1.0;
        modelItem m=new modelItem("Default");
        m.setItemPrice(itemPrice);
        assertEquals(m.getItemPrice(), itemPrice);
    }

    @Test
    public void testSetItemDOE() {
        String itemDOE="Padisho";
        modelItem m=new modelItem("Default");
        m.setItemDOE(itemDOE);
        assertEquals(m.getItemDOE(), itemDOE);

    }

    @Ignore
    public void testSetUsed() {
        int used =0;
        modelItem s=new modelItem("Default");
        //s.setUsed(used);
        //assertFalse(s.isUsed());
    }

}
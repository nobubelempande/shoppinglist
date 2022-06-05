package com.viiishoppinglistapp.doit.Model;

import junit.framework.TestCase;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.google.common.truth.Truth.assertThat;

import com.viiishoppinglistapp.doit.Utils.DateHandler;

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
        modelItem d=new modelItem(1);
        d.setItemID(itemid);

        assertEquals(itemid,d.getItemID());

    }

    @Test
    public void testGetItemQty() {
        int itemqty=1;
        modelItem m=new modelItem("Default");
        m.setItemQty(itemqty);
        assertEquals(itemqty,m.getItemQty());
        modelItem d=new modelItem(1);
        d.setItemQty(itemqty);
        assertEquals(itemqty,d.getItemQty());

    }

    @Test
    public void testGetItemName() {
        String itemName="padisho";
        modelItem m=new modelItem("Default");
        m.setItemName(itemName);
        assertEquals(itemName,m.getItemName());
        modelItem d=new modelItem(1);
        d.setItemName(itemName);
        assertEquals(itemName,d.getItemName());

    }

    @Test
    public void testGetListID() {
        int listID =0;
        modelItem m=new modelItem("Default");
        m.setShoppingListID(listID);
        assertEquals(listID, m.getListID());
        modelItem d=new modelItem(1);
        d.setShoppingListID(listID);
        assertEquals(listID, d.getListID());

    }
    @Test
    public void testGetItemType() {
        String itemType="food";
        modelItem m=new modelItem("Default");
        m.setItemType(itemType);
        assertEquals(itemType,m.getItemType());
        modelItem d=new modelItem(1);
        d.setItemType(itemType);
        assertEquals(itemType,d.getItemType());

    }

    @Test
    public void testGetItemPrice() {
        double itemPrice=1.0;
        modelItem m=new modelItem("Default");
        m.setItemPrice(itemPrice);
        assertEquals(itemPrice,m.getItemPrice());
        modelItem d=new modelItem(1);
        d.setItemPrice(itemPrice);
        assertEquals(itemPrice,d.getItemPrice());

    }

    @Test
    public void testGetItemDOE() {
        String itemDOE="padisho";
        modelItem m=new modelItem("Default");
        m.setItemDOE(itemDOE);
        assertEquals(itemDOE,m.getItemDOE());
        modelItem d=new modelItem(1);
        d.setItemDOE(itemDOE);
        assertEquals(itemDOE,d.getItemDOE());

    }



    @Test
    public void testSetItemID() {
        int itemid=1;
        modelItem m=new modelItem("Default");
        m.setItemID(itemid);
        assertEquals(m.getItemID(), itemid);
        modelItem d=new modelItem(1);
        d.setItemID(itemid);
        assertEquals(d.getItemID(), itemid);

    }

    @Test
    public void testSetItemQty() {
        int itemqty=1;
        modelItem m=new modelItem("Default");
        m.setItemQty(itemqty);
        assertEquals(m.getItemQty(), itemqty);
        modelItem d=new modelItem(1);
        d.setItemQty(itemqty);
        assertEquals(d.getItemQty(), itemqty);
    }

    @Test
    public void testSetItemName() {
        String itemName="Padisho";
        modelItem m=new modelItem("Default");
        m.setItemName(itemName);
        assertEquals(m.getItemName(), itemName);
        modelItem d=new modelItem(1);
        d.setItemName(itemName);
        assertEquals(d.getItemName(), itemName);
    }

    @Test
    public void testSetListID() {
        int listID = 0;
        modelItem m=new modelItem("Default");
        m.setShoppingListID(listID);
        assertEquals(m.getListID(), listID);
        modelItem d=new modelItem(1);
        d.setShoppingListID(listID);
        assertEquals(d.getListID(), listID);
    }

    @Test
    public void testSetItemType() {
        String itemType="food";
        modelItem m=new modelItem("Default");
        m.setItemType(itemType);
        assertEquals(m.getItemType(), itemType);
        modelItem d=new modelItem(1);
        d.setItemType(itemType);
        assertEquals(d.getItemType(), itemType);
    }

    @Test
    public void testSetItemPrice() {
        double itemPrice=1.0;
        modelItem m=new modelItem("Default");
        m.setItemPrice(itemPrice);
        assertEquals(m.getItemPrice(), itemPrice);
        modelItem d=new modelItem(1);
        d.setItemPrice(itemPrice);
        assertEquals(d.getItemPrice(), itemPrice);
    }

    @Test
    public void testSetItemDOE() {
        String itemDOE="Padisho";
        modelItem m=new modelItem("Default");
        m.setItemDOE(itemDOE);
        assertEquals(m.getItemDOE(), itemDOE);
        modelItem d=new modelItem(1);
        d.setItemDOE(itemDOE);
        assertEquals(d.getItemDOE(), itemDOE);

    }
    @Test
    public void testSetItemDOE1() {
        String itemDOE="Padisho";
        DateHandler date=new DateHandler();
        modelItem m=new modelItem("Default");
        m.setItemDOE(itemDOE);
        boolean s= itemDOE.equals(date.getNoDate());
        boolean t= m.getItemDOE().equals("N/A");
        assertEquals(s,t);
        assertEquals(m.getItemDOE(), itemDOE);
        modelItem d=new modelItem(1);
        d.setItemDOE(itemDOE);
        assertEquals(d.getItemDOE(), itemDOE);
        boolean r= d.getItemDOE().equals("N/A");
        assertEquals(s,r);

    }


    @Test
    public void testIsChecked() {
        int used = 0;
        modelItem s = new modelItem("Default");
        s.setChecked(used);
        assertThat(s.isChecked()).isEqualTo(false);
        modelItem d=new modelItem(1);
        d.setChecked(used);
        assertThat(d.isChecked()).isEqualTo(false);
    }
    @Test
    public void testIsChecked1() {
        int used = 1;
        modelItem s = new modelItem("Default");
        s.setChecked(used);
        assertThat(s.isChecked()).isEqualTo(true);
        modelItem d=new modelItem(1);
        d.setChecked(used);
        assertThat(d.isChecked()).isEqualTo(true);
    }
    @Test
    public void testSetChecked() {
        int used = 0;
        modelItem s = new modelItem("Default");
        s.setChecked(used);
        assertFalse(s.isChecked());
        modelItem d=new modelItem(1);
        d.setChecked(used);
        assertFalse(d.isChecked());
    }

    @Test
    public void testSetChecked1() {
        int used = 1;
        modelItem s = new modelItem("Default");
        s.setChecked(used);
        assertTrue(s.isChecked());
        modelItem d=new modelItem(1);
        d.setChecked(used);
        assertTrue(d.isChecked());
    }

}
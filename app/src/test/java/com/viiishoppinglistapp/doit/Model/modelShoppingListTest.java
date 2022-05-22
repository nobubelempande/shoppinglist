package com.viiishoppinglistapp.doit.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class modelShoppingListTest {

    @Test
    public void testGetListName() {
        String name= "padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setListName(name);
        assertNotNull(m.getListName());
        assertEquals(name, m.getListName());
    }

    @Test
    public void testGetListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(ID, m.getListID());
    }

    @Test
    public void testGetUsed() {
        int list= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(list);
        assertEquals(list, m.getUsed());
    }

    @Test
    public void testGetUseDate() {
        String name= "padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(name);
        assertNotNull(m.getUseDate());
        assertEquals(name, m.getUseDate());
    }

    @Test
    public void testGetListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem> read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);

    }

    @Test
    public void testSetListName() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setListName(itemName);
        assertEquals(m.getListName(), itemName);
    }


    @Test
    public void testSetListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(m.getListID(), ID);
    }

    @Test
    public void testSetListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem>read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);

    }

    @Test
    public void testSetUseDate() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(itemName);
        assertEquals(m.getUseDate(), itemName);
    }

    @Test
    public void testSetUsed() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(ID);
        assertEquals(m.getUsed(), ID);
    }

    @Test
    public void testSetToUsed() {
        int used= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setToUsed();
        m.setUsed(used);
        assertEquals(m.getUsed(), used);
    }

    @Test
    public void testSetToUnused() {
    }
}


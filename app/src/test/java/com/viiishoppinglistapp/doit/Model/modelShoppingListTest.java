package com.viiishoppinglistapp.doit.Model;

import junit.framework.TestCase;

import java.util.ArrayList;


public class modelShoppingListTest extends TestCase {

    public void testGetListName() {
        String name= "padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setListName(name);
        assertNotNull(m.getListName());
        assertEquals(name, m.getListName());
    }

    public void testGetListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(ID, m.getListID());
    }

    public void testGetUsed() {
        int list= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(list);
        assertEquals(list, m.getUsed());
    }

    public void testGetUseDate() {
        String name= "padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(name);
        assertNotNull(m.getUseDate());
        assertEquals(name, m.getUseDate());
    }

    public void testGetListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem>read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);

    }

    public void testSetListName() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setListName(itemName);
        assertEquals(m.getListName(), itemName);
    }


    public void testSetListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(m.getListID(), ID);
    }

    public void testSetListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem>read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);

    }

    public void testSetUseDate() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(itemName);
        assertEquals(m.getUseDate(), itemName);
    }

    public void testSetUsed() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(ID);
        assertEquals(m.getUsed(), ID);
    }

    public void testSetToUsed() {
        int used= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setToUsed();
        m.setUsed(used);
        assertEquals(m.getUsed(), used);
    }

    public void testSetToUnused() {
    }
}
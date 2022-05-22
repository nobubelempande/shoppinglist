package com.viiishoppinglistapp.doit.Model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import junit.framework.TestCase;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class modelShoppingListTest extends TestCase{
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void getListName() {
        String name= "padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setListName(name);
        assertNotNull(m.getListName());
        assertEquals(name, m.getListName());
    }

    @Test
    public void getListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(ID, m.getListID());
    }

    @Test
    public void getUsed() {
        int list= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(list);
        assertEquals(list, m.getUsed());
    }

    @Test
    public void getUseDate() {
        String name= "padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(name);
        assertNotNull(m.getUseDate());
        assertEquals(name, m.getUseDate());
    }

    @Test
    public void getListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem> read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);
    }

    @Test
    public void setListName() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setListName(itemName);
        assertEquals(m.getListName(), itemName);
    }

    @Test
    public void setListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(m.getListID(), ID);
    }

    @Test
    public void setListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem>read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);
    }

    @Test
    public void setUseDate() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(itemName);
        assertEquals(m.getUseDate(), itemName);
    }

    @Test
    public void setUsed() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(ID);
        assertEquals(m.getUsed(), ID);
    }

    @Test
    public void setToUsed() {
        int used= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setToUsed();
        m.setUsed(used);
        assertEquals(m.getUsed(), used);
    }

    @Test
    public void setToUnused() {
    }
}
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
        modelShoppingList d = new modelShoppingList();
        d.setListName(name);
        assertNotNull(d.getListName());
        assertEquals(name, d.getListName());

    }

    @Test
    public void getListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(ID, m.getListID());
        modelShoppingList d = new modelShoppingList();
        d.setListID(ID);
        assertEquals(ID, d.getListID());
    }

    @Test
    public void getUsed() {
        int list= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(list);
        assertEquals(list, m.getUsed());
        modelShoppingList d = new modelShoppingList();
        d.setUsed(list);
        assertEquals(list, d.getUsed());
    }

    @Test
    public void getUseDate() {
        String name= "padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(name);
        assertNotNull(m.getUseDate());
        assertEquals(name, m.getUseDate());
        modelShoppingList d = new modelShoppingList();
        d.setUseDate(name);
        assertNotNull(d.getUseDate());
        assertEquals(name, d.getUseDate());
    }

    @Test
    public void getListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem> read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);
        modelShoppingList d = new modelShoppingList();
        d.setListItems(read);
        assertEquals(d.getListItems(),read);
    }

    @Test
    public void setListName() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setListName(itemName);
        assertEquals(m.getListName(), itemName);
        modelShoppingList d = new modelShoppingList();
        d.setListName(itemName);
        assertEquals(d.getListName(), itemName);
    }

    @Test
    public void setListID() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setListID(ID);
        assertEquals(m.getListID(), ID);
        modelShoppingList d = new modelShoppingList();
        d.setListID(ID);
        assertEquals(d.getListID(), ID);
    }

    @Test
    public void setListItems() {
        modelItem m=new modelItem("Default");
        ArrayList<modelItem>read=new ArrayList<>();
        read.add(m);
        modelShoppingList s= new modelShoppingList("Default");
        s.setListItems(read);
        assertEquals(s.getListItems(),read);
        modelShoppingList d = new modelShoppingList();
        d.setListItems(read);
        assertEquals(d.getListItems(),read);
    }

    @Test
    public void setUseDate() {
        String itemName = "Padisho";
        modelShoppingList m = new modelShoppingList("Default");
        m.setUseDate(itemName);
        assertEquals(m.getUseDate(), itemName);
        modelShoppingList d = new modelShoppingList();
        d.setUseDate(itemName);
        assertEquals(d.getUseDate(), itemName);
    }

    @Test
    public void setUsed() {
        int ID= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setUsed(ID);
        assertEquals(m.getUsed(), ID);
        modelShoppingList d = new modelShoppingList();
        d.setUsed(ID);
        assertEquals(d.getUsed(), ID);
    }

    @Test
    public void setToUsed() {
        int used= 1;
        modelShoppingList m = new modelShoppingList("Default");
        m.setToUsed();
        m.setUsed(used);
        assertEquals(m.getUsed(), used);
        modelShoppingList d = new modelShoppingList();

    }

    @Test
    public void setToUnused() {
    }
}
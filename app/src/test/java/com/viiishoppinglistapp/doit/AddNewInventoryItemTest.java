package com.viiishoppinglistapp.doit;
import org.junit.Test;

import static org.junit.Assert.*;

import com.viiishoppinglistapp.doit.Activities.AddNewInventoryItem;

public class AddNewInventoryItemTest {



    /*public void testAddItemToInventoryWhenItemIsNotNull() {

        modelItem item = new modelItem("item");
        DatabaseHandler db = mock(DatabaseHandler.class);
        AddNewInventoryItem addNewInventoryItem = new AddNewInventoryItem();
        addNewInventoryItem.db = db;

        addNewInventoryItem.addItemToInventory(item);

        verify(db).insertInventoryItem(item);
    }*/

    @Test

    public void testAddItemToInventoryWhenItemIsNullThenThrowsException() {

        AddNewInventoryItem addNewInventoryItem = new AddNewInventoryItem();
        assertThrows(NullPointerException.class, () -> {
            addNewInventoryItem.addItemToInventory(null);

        });
    }

    @Test
       public void testNewInstanceShouldReturnANewInstanceOfAddNewInventoryItem() {

        AddNewInventoryItem addNewInventoryItem = AddNewInventoryItem.newInstance();
        assertNotNull(addNewInventoryItem);
    }
}
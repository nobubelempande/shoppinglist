package com.viiishoppinglistapp.doit;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddNewInventoryItemTest {

    @Test
   // @DisplayName("Should return a new instance of AddNewInventoryItem")
    public void testNewInstanceShouldReturnANewInstanceOfAddNewInventoryItem() {

        AddNewInventoryItem addNewInventoryItem = AddNewInventoryItem.newInstance();
        assertNotNull(addNewInventoryItem);
    }

}
package com.viiishoppinglistapp.doit.Testingfiles;

import android.widget.EditText;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AddShoppingItemsTest {
    AddShoppingItems ageCalculator = new AddShoppingItems();

    @Test
    public void calculateAgeTest(){
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age,"21");

    }

  /*  @Test
    public void usernameContainsThree(){

        assertEquals("", assertThrows());
    }*/

}
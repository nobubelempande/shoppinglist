package com.viiishoppinglistapp.doit.Testingfiles;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UnusedShoppingListAdapterTest {
    UnusedShoppingListAdapter ageCalculator = new UnusedShoppingListAdapter();

    @Test
    public void calculateAgeTest(){
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age,"21");

    }




}
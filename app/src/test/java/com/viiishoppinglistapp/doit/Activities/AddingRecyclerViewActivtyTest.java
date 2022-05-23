package com.viiishoppinglistapp.doit.Activities;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddingRecyclerViewActivtyTest {
    AddingRecyclerViewActivty ageCalculator = new AddingRecyclerViewActivty();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
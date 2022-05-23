package com.viiishoppinglistapp.doit.Activities;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddingFragmentSheetActivityTest {
    AddingViewModelActivity ageCalculator = new AddingViewModelActivity();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
package com.viiishoppinglistapp.doit.Activities;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddingFloatingActionButtonActivityTest {
    AddingFloatingActionButtonActivity ageCalculator = new AddingFloatingActionButtonActivity();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
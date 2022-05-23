package com.viiishoppinglistapp.doit.Unit;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardViewCreateTest {
    CardViewCreate ageCalculator = new CardViewCreate();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
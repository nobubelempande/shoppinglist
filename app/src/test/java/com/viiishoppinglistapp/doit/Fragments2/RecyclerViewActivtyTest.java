package com.viiishoppinglistapp.doit.Fragments2;

import static org.junit.Assert.*;

import org.junit.Test;

public class RecyclerViewActivtyTest {
    RecyclerViewActivty ageCalculator = new RecyclerViewActivty();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }
}
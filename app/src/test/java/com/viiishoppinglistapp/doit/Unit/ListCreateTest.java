package com.viiishoppinglistapp.doit.Unit;

import static org.junit.Assert.*;

import com.viiishoppinglistapp.doit.Interface.FloatingActionBarInterface;

import org.junit.Test;

public class ListCreateTest {
    ListCreate ageCalculator = new ListCreate();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }
}
package com.viiishoppinglistapp.doit.Unit;

import static org.junit.Assert.*;

import com.viiishoppinglistapp.doit.Interface.ButtonSaveInterface;

import org.junit.Test;

public class ItemCreateTest {
    ItemCreate ageCalculator = new ItemCreate();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
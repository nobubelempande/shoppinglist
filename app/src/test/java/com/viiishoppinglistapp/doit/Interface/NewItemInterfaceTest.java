package com.viiishoppinglistapp.doit.Interface;

import static org.junit.Assert.*;

import org.junit.Test;

public class NewItemInterfaceTest {
    NewItemInterface ageCalculator = new NewItemInterface();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
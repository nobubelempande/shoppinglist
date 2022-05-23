package com.viiishoppinglistapp.doit.Unit;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextViewCreateTest {
    TextViewCreate ageCalculator = new TextViewCreate();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
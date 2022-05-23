package com.viiishoppinglistapp.doit.Lists;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExportListTest {

    ExportList ageCalculator = new ExportList();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");
    }

}
package com.viiishoppinglistapp.doit.Lists;

import static org.junit.Assert.*;

import org.junit.Test;

public class TransferTest {

    Transfer ageCalculator = new Transfer();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");
    }

}
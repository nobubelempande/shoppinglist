package com.viiishoppinglistapp.doit.Testingfiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgeCalculatorTest {
    AgeCalculator ageCalculator = new AgeCalculator();

    @Test
    public void calculateAgeTest(){
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age,"21");

    }
}
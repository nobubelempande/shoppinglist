package com.viiishoppinglistapp.doit.Interface;

import static org.junit.Assert.*;

import com.viiishoppinglistapp.doit.Activities.AddingFragmentSheetActivity;

import org.junit.Test;

public class SplashInterfaceTest {
    SplashInterface ageCalculator = new SplashInterface();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }
}
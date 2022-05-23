package com.viiishoppinglistapp.doit.Interface;

import static org.junit.Assert.*;

import com.viiishoppinglistapp.doit.Activities.AddingClickedListActivity;

import org.junit.Test;

public class HomeInterfaceTest {
    HomeInterface ageCalculator = new HomeInterface();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
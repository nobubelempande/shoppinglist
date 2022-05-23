package com.viiishoppinglistapp.doit.Fragments2;

import static org.junit.Assert.*;

import com.viiishoppinglistapp.doit.Unit.ButtonCreate;

import org.junit.Test;

public class FragmentActionBarTest {
    FragmentActionBar ageCalculator = new FragmentActionBar();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
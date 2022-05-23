package com.viiishoppinglistapp.doit.ShopActivities;

import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ListActivityTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    ListActivity ageCalculator = new ListActivity();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }
    //


}
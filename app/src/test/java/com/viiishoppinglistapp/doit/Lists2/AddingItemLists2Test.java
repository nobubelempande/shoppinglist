package com.viiishoppinglistapp.doit.Lists2;

import static org.junit.Assert.*;

        import org.junit.Test;

public class AddingItemLists2Test {
    AddingItemLists2 ageCalculator = new AddingItemLists2();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
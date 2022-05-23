package com.viiishoppinglistapp.doit.Lists2;

import static org.junit.Assert.*;

        import org.junit.Test;

public class AddingUsedLists2Test  {
    AddingUsedLists2 ageCalculator = new AddingUsedLists2();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
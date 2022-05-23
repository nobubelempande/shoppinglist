package com.viiishoppinglistapp.doit.Interfaces2;

import static org.junit.Assert.*;

        import org.junit.Test;

public class AddingButtonIterfacesTest  {
    AddingButtonIterfaces ageCalculator = new AddingButtonIterfaces();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
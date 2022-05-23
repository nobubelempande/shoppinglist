package com.viiishoppinglistapp.doit.Interfaces2;

import static org.junit.Assert.*;

        import org.junit.Test;

public class AddingCalenderInterfacesTest  {
    AddingCalenderInterfaces ageCalculator = new AddingCalenderInterfaces();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
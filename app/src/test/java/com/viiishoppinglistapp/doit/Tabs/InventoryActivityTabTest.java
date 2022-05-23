package com.viiishoppinglistapp.doit.Tabs;


        import static org.junit.Assert.*;

        import org.junit.Test;

public class InventoryActivityTabTest  {
    InventoryActivityTab ageCalculator = new InventoryActivityTab();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
package com.viiishoppinglistapp.doit.Helper;

        import static org.junit.Assert.assertEquals;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.junit.runners.JUnit4;

public class InventoryItemHelperTest {
    InventoryItemHelper ageCalculator = new InventoryItemHelper();

    @Test
    public void calculateAgeTest(){
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age,"21");

    }


}
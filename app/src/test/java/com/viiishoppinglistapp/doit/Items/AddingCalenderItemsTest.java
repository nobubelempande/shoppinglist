package com.viiishoppinglistapp.doit.Items;

        import static org.junit.Assert.assertEquals;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.junit.runners.JUnit4;

public class AddingCalenderItemsTest {
    AddingCalenderItems ageCalculator = new AddingCalenderItems();

    @Test
    public void calculateAgeTest(){
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age,"21");

    }


}
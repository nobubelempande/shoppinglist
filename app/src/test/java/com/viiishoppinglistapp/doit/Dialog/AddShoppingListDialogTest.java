package com.viiishoppinglistapp.doit.Dialog;
        import static org.junit.Assert.*;

        import org.junit.Test;

public class AddShoppingListDialogTest {
    AddShoppingListDialog ageCalculator = new AddShoppingListDialog();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
package com.viiishoppinglistapp.doit.Dialog;
        import static org.junit.Assert.*;

        import org.junit.Test;

public class UserDialogTest {
    UserDialog ageCalculator = new UserDialog();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
package com.viiishoppinglistapp.doit.ShopActivities;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditShopTest {

    EditShop ageCalculator = new EditShop();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }

}
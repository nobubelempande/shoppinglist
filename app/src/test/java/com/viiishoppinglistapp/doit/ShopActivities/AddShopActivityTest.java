package com.viiishoppinglistapp.doit.ShopActivities;

import static org.junit.Assert.*;

import com.viiishoppinglistapp.doit.Activities.AddingButtonActivity;

import org.junit.Test;

public class AddShopActivityTest {

    AddShopActivity ageCalculator = new AddShopActivity();

    @Test
    public void calculateAgeTest() {
        String age = ageCalculator.calculateAge("31-03-2000");

        assertEquals(age, "21");

    }
    //
}
package com.viiishoppinglistapp.doit.Testingfiles;
import junit.framework.TestCase;

import org.junit.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
@RunWith(JUnit4.class)

public class UserTest {
User use=new User();
    @Test
    public void calculateAge() {
        String age = use.calculateAge("31-03-2000");

        assertEquals(age,"21");

    }

}
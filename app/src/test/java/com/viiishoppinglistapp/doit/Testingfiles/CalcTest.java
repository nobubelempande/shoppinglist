package com.viiishoppinglistapp.doit.Testingfiles;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
@RunWith(JUnit4.class)
public class CalcTest {
    Calc calc= new Calc();
    @Test
    public void calculateAge() {
                   String age = calc.calculateAge("31-03-2000");

            assertEquals(age,"21");

        }
    }

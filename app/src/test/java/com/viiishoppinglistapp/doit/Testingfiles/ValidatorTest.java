package com.viiishoppinglistapp.doit.Testingfiles;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ValidatorTest {

    @Test
    public void isInputValid(){
        String a = "Nobubele Mpande";
        Boolean b = new Validator(a).isinputString();
        assertThat(b).isEqualTo(true);
    }
}
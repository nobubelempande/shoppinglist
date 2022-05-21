package com.viiishoppinglistapp.doit;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)

public class Checking {


    @Test
    public void isInputValid(){
        String a = "Katleho";
        Boolean b = new Validator(a).isinputString();
        assertThat(b).isEqualTo(true);
    }


}

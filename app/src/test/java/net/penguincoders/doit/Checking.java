package net.penguincoders.doit;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;


@RunWith(JUnit4.class)

public class Checking {

    @Test
    public void isinputValid(){
        String a = "Katleho";
        Boolean b = new Validator(a).isinputString();
        assertThat(b).isEqualTo(true);
    }

    @Test
    public void invalidInput(){
        Integer c = 5;
        Boolean d = new ValidatorInt(c).isinputInteger();
        assertThat(d).isEqualTo(true);
    }

}

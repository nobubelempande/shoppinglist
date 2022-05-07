package net.penguincoders.doit;

import junit.framework.TestCase;

import net.penguincoders.doit.Utils.DatabaseHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)

public class MainActivityTest extends TestCase{
    MainActivity mainActivity=new MainActivity();

    private DatabaseHandler db;

    @Before
    public void setup(){

    }

    @Test public void testTrueIsTrue() throws Exception {
        assertEquals(true, true);
    }




}
package com.viiishoppinglistapp.doit.Model;

import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ModelTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }
    @Test
    public void getTitle() {
        String itemName="padisho";
        Model m=new Model("Default","3/05/2022","00:12");
        m.setTitle(itemName);
        assertEquals(itemName,m.getTitle());
    }

    @Test
    public void setTitle() {
    }

    @Test
    public void getDate() {
    }

    @Test
    public void setDate() {
    }

    @Test
    public void getTime() {
    }

    @Test
    public void setTime() {
    }
}
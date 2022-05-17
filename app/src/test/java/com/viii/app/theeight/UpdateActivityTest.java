package com.viii.app.theeight;

import static org.junit.Assert.*;

import org.junit.Test;

public class UpdateActivityTest {
    @Test
    public void test(){
        String s= "one";
        assertEquals("one",s);
    }
    @Test
    public void test1(){
        String s="one";
        assertNotNull(UpdateActivity.class.toString());
    }
    @Test
    public void test2(){
        String b= UpdateActivity.class.getName();
        assertNotNull(b);
    }

}
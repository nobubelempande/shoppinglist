package com.viiishoppinglistapp.doit.Model;

import junit.framework.TestCase;

public class ModelTest extends TestCase {

    public void testGetTitle() {
        String title="padisho";
        Model m=new Model("Default","02/02/2022","09:00");
        m.setTitle(title);
        assertEquals(title,m.getTitle());
    }

    public void testSetTitle() {
        String title="Padisho";
        Model m=new Model("Default","02/02/2022","09:00");
        m.setTitle(title);
        assertEquals(m.getTitle(), title);

    }

    public void testGetDate() {
        String Date="02/02/2022";

        Model m=new Model ("Default","02/02/2022","09:00");
        m.setDate(Date);
        assertEquals(Date,m.getDate());

    }
    public void testSetDate() {
        String Date="02/02/2022";
        Model m=new Model ("Default","02/02/2022","09:00");
        m.setDate(Date);
        assertEquals(Date,m.getDate());

    }


    public void testGetTime() {
        String itemDOE="09:00";
        Model m=new Model ("Default","02/02/2022","09:00");
        m.setTime(itemDOE);
        assertEquals(itemDOE,m.getTime());

    }

    public void testSetTime() {
        String itemDOE="09:00";
        Model m=new Model ("Default","02/02/2022","09:00");
        m.setTime(itemDOE);
        assertEquals(itemDOE,m.getTime());

    }

}
package com.viiishoppinglistapp.doit.Utils;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import org.junit.After;
import org.junit.Before;


import org.junit.Before;


@RunWith(JUnit4.class)

public class DatabaseHandlerTest {
private DatabaseHandler db;

    @Before
    public void setUp(){
        db = new DatabaseHandler(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }
    @Test
    public void insertShoppingList() {


    }
}
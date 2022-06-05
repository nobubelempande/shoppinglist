package com.viiishoppinglistapp.doit.Adapters;



import com.viiishoppinglistapp.doit.Model.Model;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
@RunWith(JUnit4.class)
public class myAdapterTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void getItemCount() {
        Model m=new Model();

        ArrayList<Model> dataholder = new ArrayList<>();
        dataholder.add(m);
        myAdapter b=new myAdapter(dataholder);
        int i=b.getItemCount();
        assertEquals(1,i);
    }
}
package net.penguincoders.doit;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class activitySelectedShoppingListTest {
    activitySelectedShoppingList Ss=new activitySelectedShoppingList();
    @Test
    public void test(){
        String s=Ss.toString();
        assertNotNull(s);
    }

}
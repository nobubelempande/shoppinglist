package com.viiishoppinglistapp.doit;

import android.content.Context;
import android.util.Log;

import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

public class Validator {
    String a;
    DatabaseHandler db;

    //constructors
    public Validator(DatabaseHandler db){
        this.db = db;
    }
    public Validator(String b){
        a = b;
    }

    public boolean isinputString(){
        if(a instanceof String){
            return true;
        }else{
            return false;
        }
    }

    //shopping lists
    public boolean isShoppingListNameValid(String name){
        Log.d(MainActivity.TAG, "Validator:: list obtained.");
        if (name.equals("")){
            return false;
        }

        return true;
    }

    //items

    //inventory items

    public int add(){
        return 2;
    }
}

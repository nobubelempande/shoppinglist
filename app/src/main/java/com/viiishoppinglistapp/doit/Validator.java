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
        if (name.equals("")){
            return false;
        }
        //toDo check for existing list
        return true;
    }

    //items
    public boolean isItemTypeSelected(String type){
        Log.d(MainActivity.TAG, "Validator:: item type obtained.");
        if(type.equals("") || type.equals("--") || type.equals(null)){
            return false;
        }
        return true;
    }

    public boolean isItemQtyNotEmpty(String qty){
        if(qty.equals("")){
            return false;
        }
        return true;
    }

    public boolean isItemNameNotEmpty(String name){
        if(name.equals("") || name.equals(null)){
            return false;
        }
        return true;
    }

    //inventory items

    public int add(){
        return 2;
    }
}

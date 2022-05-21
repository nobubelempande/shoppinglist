package com.viiishoppinglistapp.doit.Utils;

import android.util.Log;

import com.viiishoppinglistapp.doit.HomeActivity_old;

public class Validation {
    DatabaseHandler db;

    //constructors
    public Validation(DatabaseHandler db){
        this.db = db;
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
        Log.d(HomeActivity_old.TAG, "Validator:: item type obtained.");
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
    public boolean isItemPriceEmpty(String text){
        if(text.equals("") || text.equals(null)){
            return true;
        }
        return false;
    }
}

package com.viiishoppinglistapp.doit.Utils;

public class Validation {
    public static String TAG = "VIII";
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
        return true;
    }

    //items
    public boolean isItemTypeSelected(String type){
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

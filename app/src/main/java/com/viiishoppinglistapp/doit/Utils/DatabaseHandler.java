package com.viiishoppinglistapp.doit.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.viiishoppinglistapp.doit.HomeActivity_old;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "ListDatabase_2";

    private SQLiteDatabase db;

    //own
    private final String TAG = "VIII-App";
    private static final String TABLE_ShoppingLists = "shoppingLists";
    private static final String TABLE_Items = "items";
    private static final String TABLE_Inventory = "inventory";

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //ShoppingLists class

        final String ID = "list_id";
        final String NAME = "list_name";
        final String USE_DATE = "list_useDate";
        final String USED = "list_used";        //0 = unused; 1 = used


        final String CREATE_ShoppingLists_TABLE = "CREATE TABLE " + TABLE_ShoppingLists + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                USE_DATE + " TEXT, " +
                USED + " INTEGER" +
                ")";
        db.execSQL(CREATE_ShoppingLists_TABLE);


        //items class

        final String itemID = "item_id";
        final String itemNAME = "item_name";
        final String itemQTY = "item_qty";
        final String itemType = "item_type";
        final String itemShoppingList = "item_listName";
        final String itemPrice = "item_price";
        final String itemDOE = "item_doe";
        final String itemUsed = "item_used";

        final String CREATE_Items_TABLE = "CREATE TABLE " + TABLE_Items + "(" +
                itemID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                itemNAME + " TEXT, " +
                itemQTY + " INTEGER, " +
                itemType + " TEXT, " +
                itemShoppingList + " TEXT, " +
                itemPrice + " DOUBLE, " +
                itemDOE + " TEXT, " +
                itemUsed + " INTEGER" +
                ")";
        db.execSQL(CREATE_Items_TABLE);

        //Inventory class

        //toDo add inventory_id


        final String CREATE_Inventory_TABLE = "CREATE TABLE " + TABLE_Inventory + "(" +
                itemID + " INTEGER PRIMARY KEY, " +
                itemNAME + " TEXT, " +
                itemQTY + " INTEGER, " +
                itemType + " TEXT, " +
                itemShoppingList + " TEXT, " +
                itemPrice + " DOUBLE, " +
                itemDOE + " TEXT, " +
                itemUsed + " INTEGER" +
                ")";
        db.execSQL(CREATE_Inventory_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ShoppingLists);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Items);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Inventory);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }


    //shoppingLists ::

    public modelShoppingList getShoppingList(String listName){
        //getting all saved shoppingLists from the DB


        final String strListName = listName;
        final String shoppingListID = "list_id";
        final String shoppingListNAME = "list_name";
        final String shoppingListUSEDATE = "list_useDate";
        final String shoppingListUSED = "list_used";


        String[] columns = {shoppingListID, shoppingListNAME, shoppingListUSEDATE, shoppingListUSED};
        String where = shoppingListNAME + "=?";         //"TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        String[] args = {strListName};                //{"tagname", "tagname", "tagname", "tagname", "tagname"};

        List<modelShoppingList> allShoppingLists = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_ShoppingLists, columns, where, args, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding shoppingList to list of allShoppingLists
                        modelShoppingList list = new modelShoppingList();
                        list.setListID(cur.getInt(cur.getColumnIndexOrThrow(shoppingListID)));
                        list.setListName(cur.getString(cur.getColumnIndexOrThrow(shoppingListNAME)));
                        list.setUseDate(cur.getString(cur.getColumnIndexOrThrow(shoppingListUSEDATE)));
                        list.setUsed(cur.getInt(cur.getColumnIndexOrThrow(shoppingListUSED)));
                        allShoppingLists.add(list);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return allShoppingLists.get(0);
    }

    public List<modelShoppingList> getAllUsedShoppingLists(){
        //getting all saved shoppingLists from the DB

        final int intUsed = 1;
        final String shoppingListID = "list_id";
        final String shoppingListNAME = "list_name";
        final String shoppingListUSEDATE = "list_useDate";
        final String shoppingListUSED = "list_used";

        String[] columns = {shoppingListID, shoppingListNAME, shoppingListUSEDATE, shoppingListUSED};
        String where = shoppingListUSED + "=?";         //"TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        String[] args = {String.valueOf(intUsed)};                //{"tagname", "tagname", "tagname", "tagname", "tagname"};

        List<modelShoppingList> allShoppingLists = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_ShoppingLists, columns, where, args, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding shoppingList to list of allShoppingLists
                        modelShoppingList list = new modelShoppingList();
                        list.setListID(cur.getInt(cur.getColumnIndexOrThrow(shoppingListID)));
                        list.setListName(cur.getString(cur.getColumnIndexOrThrow(shoppingListNAME)));
                        list.setUseDate(cur.getString(cur.getColumnIndexOrThrow(shoppingListUSEDATE)));
                        list.setUsed(cur.getInt(cur.getColumnIndexOrThrow(shoppingListUSED)));
                        allShoppingLists.add(list);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return allShoppingLists;
    }

    public List<modelShoppingList> getAllUnusedShoppingLists(){
        //getting all saved shoppingLists from the DB

        final int intUnused = 0;
        final String shoppingListID = "list_id";
        final String shoppingListNAME = "list_name";
        final String shoppingListUSEDATE = "list_useDate";
        final String shoppingListUSED = "list_used";

        String[] columns = {shoppingListID, shoppingListNAME, shoppingListUSEDATE, shoppingListUSED};
        String where = shoppingListUSED + "=?";         //"TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        String[] args = {String.valueOf(intUnused)};                //{"tagname", "tagname", "tagname", "tagname", "tagname"};

        List<modelShoppingList> allShoppingLists = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_ShoppingLists, columns, where, args, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding shoppingList to list of allShoppingLists
                        modelShoppingList list = new modelShoppingList();
                        list.setListID(cur.getInt(cur.getColumnIndexOrThrow(shoppingListID)));
                        list.setListName(cur.getString(cur.getColumnIndexOrThrow(shoppingListNAME)));
                        list.setUseDate(cur.getString(cur.getColumnIndexOrThrow(shoppingListUSEDATE)));
                        list.setUsed(cur.getInt(cur.getColumnIndexOrThrow(shoppingListUSED)));
                        allShoppingLists.add(list);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return allShoppingLists;
    }

    public List<modelShoppingList> getAllShoppingLists(){
        //getting all saved shoppingLists from the DB

        List<modelShoppingList> allShoppingLists = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.rawQuery("SELECT * FROM " + TABLE_ShoppingLists, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding shoppingList to list of allShoppingLists
                        modelShoppingList list = new modelShoppingList();
                        list.setListID(cur.getInt(cur.getColumnIndexOrThrow("list_id")));
                        list.setListName(cur.getString(cur.getColumnIndexOrThrow("list_name")));
                        list.setUseDate(cur.getString(cur.getColumnIndexOrThrow("list_useDate")));
                        allShoppingLists.add(list);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return allShoppingLists;
    }

    public void insertShoppingList(modelShoppingList currList){
        //adding to db
        ContentValues cv = new ContentValues();
        cv.put("list_name", currList.getListName());
        cv.put("list_useDate", currList.getUseDate());
        cv.put("list_used", currList.getUsed());
        db.insert(TABLE_ShoppingLists, null, cv);
    }

    public void updateShoppingList(modelShoppingList currList) {
        //updating db
        final String ID = "list_id";
        ContentValues cv = new ContentValues();
        cv.put("list_name", currList.getListName());
        cv.put("list_useDate", currList.getUseDate());
        cv.put("list_used", currList.getUsed());

        db.update(TABLE_ShoppingLists, cv, ID + "= ?", new String[] {String.valueOf(currList.getListID())});
    }

    public void deleteShoppingList(int list_id){
        //removing from db
        final String ID = "list_id";
        db.delete(TABLE_ShoppingLists, ID + "= ?", new String[] {String.valueOf(list_id)});
    }



    //items

    public Cursor readItemsData(){
        String query = "SELECT * FROM items WHERE item_price is not null";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public List<modelItem> getItemsForShoppingList(String listName){
        //getting all saved shoppingLists from the DB

        final String strListName = listName;
        final String itemID = "item_id";
        final String itemNAME = "item_name";
        final String itemQTY = "item_qty";
        final String itemType = "item_type";
        final String itemShoppingList = "item_listName";
        final String itemPrice = "item_price";
        final String itemDOE = "item_doe";
        final String itemUsed = "item_used";

        String[] columns = {itemID, itemNAME, itemQTY, itemType, itemShoppingList, itemPrice, itemDOE, itemUsed};
        String where = itemShoppingList + "=?";         //"TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        String[] args = {strListName};                //{"tagname", "tagname", "tagname", "tagname", "tagname"};


        List<modelItem> allItems = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_Items, columns, where, args, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding shoppingList to list of allShoppingLists
                        Log.d(TAG, "getItemsForShoppingList: Start");
                        modelItem currItem = new modelItem(cur.getString(cur.getColumnIndexOrThrow(itemNAME)));
                        currItem.setItemID(cur.getInt(cur.getColumnIndexOrThrow(itemID)));
                        currItem.setItemQty(cur.getInt(cur.getColumnIndexOrThrow(itemQTY)));
                        currItem.setItemType(cur.getString(cur.getColumnIndexOrThrow(itemType)));
                        currItem.setListName(cur.getString(cur.getColumnIndexOrThrow(itemShoppingList)));
                        currItem.setItemPrice(cur.getInt(cur.getColumnIndexOrThrow(itemPrice)));
                        currItem.setItemDOE(cur.getString(cur.getColumnIndexOrThrow(itemDOE)));
                        currItem.setChecked(cur.getInt(cur.getColumnIndexOrThrow(itemUsed)));
                        allItems.add(currItem);
                        Log.d(TAG, "getItemsForShoppingList: Item: " + currItem.getItemName() + "  Checked: " + currItem.getChecked());
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return allItems;
    }

    public void insertItem(modelItem currItem){
        //adding to db
        ContentValues cv = new ContentValues();
        cv.put("item_name", currItem.getItemName());
        cv.put("item_qty", currItem.getItemQty());

        cv.put("item_type", currItem.getItemType());
        cv.put("item_listName", currItem.getListName());
        cv.put("item_price", currItem.getItemPrice());
        cv.put("item_doe", currItem.getItemDOE());
        cv.put("item_used", currItem.getChecked());

        db.insert(TABLE_Items, null, cv);
    }

    public void updateItem(modelItem item) {
        //updating db
        final String ID = "item_id";
        ContentValues cv = new ContentValues();
        cv.put("item_name", item.getItemName());
        cv.put("item_qty", item.getItemQty());

        cv.put("item_type", item.getItemType());
        cv.put("item_price", item.getItemPrice());
        cv.put("item_doe", item.getItemDOE());
        cv.put("item_used", item.getChecked());

        db.update(TABLE_Items, cv, ID + "= ?", new String[] {String.valueOf(item.getItemID())});
    }

    public void deleteItem(int item_id){
        //removing from db
        final String ID = "item_id";
        db.delete(TABLE_Items, ID + "= ?", new String[] {String.valueOf(item_id)});
    }

    public modelItem getItem(String itemName, String shoppingListName){
        //getting all saved shoppingLists from the DB


        final String itemID = "item_id";
        final String itemNAME = "item_name";
        final String itemQTY = "item_qty";
        final String itemType = "item_type";
        final String itemShoppingList = "item_listName";
        final String itemPrice = "item_price";
        final String itemDOE = "item_doe";
        final String itemUsed = "item_used";

        String[] columns = {itemID, itemNAME, itemQTY, itemType, itemShoppingList, itemPrice, itemDOE, itemUsed};
        String where = itemNAME + "=?" + " AND " + itemShoppingList + "=?";         //"TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        String[] args = {itemName, shoppingListName};                //{"tagname", "tagname", "tagname", "tagname", "tagname"};

        List<modelItem> allItems = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_Items, columns, where, args, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding item to list of inventory items
                        modelItem currItem = new modelItem(cur.getString(cur.getColumnIndexOrThrow(itemNAME)));
                        currItem.setItemID(cur.getInt(cur.getColumnIndexOrThrow(itemID)));
                        currItem.setItemQty(cur.getInt(cur.getColumnIndexOrThrow(itemQTY)));
                        currItem.setItemType(cur.getString(cur.getColumnIndexOrThrow(itemType)));
                        currItem.setListName(cur.getString(cur.getColumnIndexOrThrow(itemShoppingList)));
                        currItem.setItemPrice(cur.getInt(cur.getColumnIndexOrThrow(itemPrice)));
                        currItem.setItemDOE(cur.getString(cur.getColumnIndexOrThrow(itemDOE)));
                        currItem.setChecked(cur.getInt(cur.getColumnIndexOrThrow(itemUsed)));
                        allItems.add(currItem);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        if(allItems.size()<1){
            return null;
        }
        return allItems.get(0);
    }

    public modelItem getItem(String itemName){
        //getting all saved shoppingLists from the DB


        final String itemID = "item_id";
        final String itemNAME = "item_name";
        final String itemQTY = "item_qty";
        final String itemType = "item_type";
        final String itemShoppingList = "item_listName";
        final String itemPrice = "item_price";
        final String itemDOE = "item_doe";
        final String itemUsed = "item_used";

        String[] columns = {itemID, itemNAME, itemQTY, itemType, itemShoppingList, itemPrice, itemDOE, itemUsed};
        String where = itemNAME + "=?";         //"TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        String[] args = {itemName};                //{"tagname", "tagname", "tagname", "tagname", "tagname"};

        List<modelItem> allItems = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_Items, columns, where, args, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding item to list of inventory items
                        modelItem currItem = new modelItem(cur.getString(cur.getColumnIndexOrThrow(itemNAME)));
                        currItem.setItemID(cur.getInt(cur.getColumnIndexOrThrow(itemID)));
                        currItem.setItemQty(cur.getInt(cur.getColumnIndexOrThrow(itemQTY)));
                        currItem.setItemType(cur.getString(cur.getColumnIndexOrThrow(itemType)));
                        currItem.setListName(cur.getString(cur.getColumnIndexOrThrow(itemShoppingList)));
                        currItem.setItemPrice(cur.getInt(cur.getColumnIndexOrThrow(itemPrice)));
                        currItem.setItemDOE(cur.getString(cur.getColumnIndexOrThrow(itemDOE)));
                        currItem.setChecked(cur.getInt(cur.getColumnIndexOrThrow(itemUsed)));
                        allItems.add(currItem);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        if(allItems.size()<1){
            return null;
        }
        return allItems.get(0);
    }



    //Inventory

    public List<modelItem> getAllInventoryItems(){
        //getting all saved shoppingLists from the DB

        Log.d(TAG, "getAllInventoryItems: GETTING ITEMS");

        final String itemID = "item_id";
        final String itemNAME = "item_name";
        final String itemQTY = "item_qty";
        final String itemType = "item_type";
        final String itemShoppingList = "item_listName";
        final String itemPrice = "item_price";
        final String itemDOE = "item_doe";
        final String itemUsed = "item_used";

        List<modelItem> allItems = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.rawQuery("SELECT * FROM " + TABLE_Inventory, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding item to list of inventory items
                        modelItem currItem = new modelItem(cur.getString(cur.getColumnIndexOrThrow(itemNAME)));
                        currItem.setItemID(cur.getInt(cur.getColumnIndexOrThrow(itemID)));
                        currItem.setItemQty(cur.getInt(cur.getColumnIndexOrThrow(itemQTY)));
                        currItem.setItemType(cur.getString(cur.getColumnIndexOrThrow(itemType)));
                        currItem.setListName(cur.getString(cur.getColumnIndexOrThrow(itemShoppingList)));
                        currItem.setItemPrice(cur.getInt(cur.getColumnIndexOrThrow(itemPrice)));
                        currItem.setItemDOE(cur.getString(cur.getColumnIndexOrThrow(itemDOE)));
                        currItem.setChecked(cur.getInt(cur.getColumnIndexOrThrow(itemUsed)));
                        allItems.add(currItem);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        Log.d(TAG, "getAllInventoryItems: DONE GETTING ITEMS");
        return allItems;
    }

    public void insertInventoryItem(modelItem currItem){
        //adding to db
        ContentValues cv = new ContentValues();
        cv.put("item_name", currItem.getItemName());
        cv.put("item_qty", currItem.getItemQty());
        cv.put("item_type", currItem.getItemType());
        cv.put("item_listName", currItem.getListName());
        cv.put("item_price", currItem.getItemPrice());
        cv.put("item_doe", currItem.getItemDOE());
        cv.put("item_used", currItem.getChecked());

        modelItem existingItem = getInventoryItem(currItem.getItemName());

        if(existingItem == null){
            db.insert(TABLE_Inventory, null, cv);
            Log.d(TAG, "insertInventoryItem DB: NEW INVENTORY ITEM ADDED SUCCESSFULLY");
        }
        else {
            Log.d(TAG, "insertInventoryItem DB: UPDATE INVENTORY");
            int prevQty = existingItem.getItemQty();
            double prevPrice = existingItem.getItemPrice();

            int newQty = currItem.getItemQty();
            double newPrice = currItem.getItemPrice();

            currItem.setItemQty(newQty + prevQty);
            currItem.setItemPrice(prevPrice + newPrice);

            updateInventoryItem(currItem);
            Log.d(TAG, "insertInventoryItem DB: INVENTORY UPDATED SUCCESSFULLY");
        }

    }

    public void updateInventoryItem(modelItem item) {
        //updating db
        final String NAME = "item_name";
        ContentValues cv = new ContentValues();
        cv.put("item_name", item.getItemName());
        cv.put("item_qty", item.getItemQty());
        cv.put("item_listName", item.getListName());
        cv.put("item_type", item.getItemType());
        cv.put("item_price", item.getItemPrice());
        cv.put("item_doe", item.getItemDOE());
        cv.put("item_used", item.getChecked());

        db.update(TABLE_Inventory, cv, NAME + "= ?", new String[] {String.valueOf(item.getItemName())});
    }

    public void deleteInventoryItem(String item_name){
        //removing from db
        final String NAME = "item_name";
        //todo if(exists) -> if(exists-currQty>=1) -> update(newQty)

        modelItem currItem = getItem(item_name);
        modelItem existingItem = getInventoryItem(item_name);

        if(existingItem == null){
            db.delete(TABLE_Inventory, NAME + "= ?", new String[] {item_name});
            Log.d(TAG, "insertInventoryItem DB: NEW INVENTORY ITEM DELETED SUCCESSFULLY");
        }
        else {
            Log.d(TAG, "insertInventoryItem DB: EXISTING INVENTORY ITEM");
            int prevQty = existingItem.getItemQty();
            int newQty = currItem.getItemQty();

            if(prevQty-newQty >= 1) {
                currItem.setItemQty(prevQty-newQty);
                updateInventoryItem(currItem);
                Log.d(TAG, "insertInventoryItem DB: EXISTING INVENTORY ITEM UPDATED SUCCESSFULLY");
            }
            else {
                db.delete(TABLE_Inventory, NAME + "= ?", new String[] {item_name});
                Log.d(TAG, "insertInventoryItem DB: EXISTING INVENTORY ITEM DELETED SUCCESSFULLY");
            }
        }

        db.delete(TABLE_Inventory, NAME + "= ?", new String[] {item_name});
    }

    public modelItem getInventoryItem(String itemName){
        //getting all saved shoppingLists from the DB


        final String strItemName = itemName;
        final String itemID = "item_id";
        final String itemNAME = "item_name";
        final String itemQTY = "item_qty";
        final String itemType = "item_type";
        final String itemShoppingList = "item_listName";
        final String itemPrice = "item_price";
        final String itemDOE = "item_doe";
        final String itemUsed = "item_used";

        String[] columns = {itemNAME, itemQTY, itemType, itemShoppingList, itemPrice, itemDOE, itemUsed};
        String where = itemNAME + "=?";         //"TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        String[] args = {strItemName};                //{"tagname", "tagname", "tagname", "tagname", "tagname"};

        List<modelItem> allItems = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_Inventory, columns, where, args, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding item to list of inventory items
                        modelItem currItem = new modelItem(cur.getString(cur.getColumnIndexOrThrow(itemNAME)));
                        currItem.setItemQty(cur.getInt(cur.getColumnIndexOrThrow(itemQTY)));
                        currItem.setItemType(cur.getString(cur.getColumnIndexOrThrow(itemType)));
                        currItem.setListName(cur.getString(cur.getColumnIndexOrThrow(itemShoppingList)));
                        currItem.setItemPrice(cur.getInt(cur.getColumnIndexOrThrow(itemPrice)));
                        currItem.setItemDOE(cur.getString(cur.getColumnIndexOrThrow(itemDOE)));
                        currItem.setChecked(cur.getInt(cur.getColumnIndexOrThrow(itemUsed)));
                        allItems.add(currItem);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        if(allItems.size()<1){
            return null;
        }
        return allItems.get(0);
    }

}

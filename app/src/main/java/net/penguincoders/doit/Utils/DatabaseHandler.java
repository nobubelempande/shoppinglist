package net.penguincoders.doit.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.penguincoders.doit.Model.mItems;
import net.penguincoders.doit.Model.mShoppingList;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 100000;
    private static final String NAME = "Shoppinglist_database";

    private SQLiteDatabase db;

    //own
    private final String TAG = "VIII";
    private final String TAG2 = "X";
    private static final String TABLE_ShoppingLists = "shoppingLists";

    private static final String TABLE_ITEMS = "ITEMS_TABLE";


    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //toDo create itemTable
        //db.execSQL(CREATE_TODO_TABLE);

        Log.d(TAG, "---> --> SQLite invoked for creation");

        //createShoppingLists
        final String ID = "list_id";
        final String NAME = "list_name";

        final String itemID = "item_id";
        final String itemName = "item_name";
        final String itemQuantity = "item_quantity";
        final String itemPrice = "item_price";
        final String D_O_E = "D_O_E";  //D_O_S = DATE OF EXPIRATION
        final String itemCategory = "item_category";


        final String CREATE_ShoppingLists_TABLE = "CREATE TABLE " + TABLE_ShoppingLists + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT" +
                ")";

//        final String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" +
//                itemID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                itemName + " TEXT," +
//                itemQuantity + "INTEGER, " +
//                /*itemPrice + "INTEGER,  " +
//                D_O_E + "DATE, " +*/
//                itemCategory + "TEXT" +
//                ")";

        final String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" +
                itemID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                itemName + " TEXT," +
                itemQuantity + " INTEGER," +
                itemCategory + " TEXT" +
                ")";



        db.execSQL(CREATE_ShoppingLists_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
        //db.execSQL(CREATE_ITEMS_TABLE);

        Log.d(TAG, "** ---> --> shopping list table Created");
        Log.d(TAG2, "** ---> --> items table created");
   }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ShoppingLists);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }


    //shoppingLists ::

    public List<mShoppingList> getAllShoppingLists(){
        //getting all saved shoppingLists from the DB

        List<mShoppingList> allShoppingLists = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            Log.d(TAG, "---> --> DB Query Starting");
            cur = db.rawQuery("SELECT * FROM " + TABLE_ShoppingLists, null);

            Log.d(TAG, "---> --> DB Query for all Lists done successfully");
            if(cur != null){
                cur.moveToFirst();
                    while(!cur.isAfterLast()){
                        //adding shoppingList to list of allShoppingLists
                        mShoppingList list = new mShoppingList();
                        //Log.d(TAG, "THIS IS THE MESSAGE"+ String.valueOf(cur.getColumnIndexOrThrow("list_id")));
                        if(cur.getString(cur.getColumnIndexOrThrow("list_name")) != null)
                        {

                            list.setList_id(cur.getInt(cur.getColumnIndexOrThrow("list_id")));
                            list.setList_name(cur.getString(cur.getColumnIndexOrThrow("list_name")));
                            allShoppingLists.add(list);
                        }
                        cur.moveToNext();
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

    public List<mItems> getAllItems(){
        /* getting all the saved items from the db */
        List<mItems> allItemsList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            Log.d(TAG, "---> --> DB Query Starting");
            cur = db.rawQuery("SELECT * FROM " + TABLE_ITEMS, null);
            Log.d(TAG, "---> --> DB Query for all items done successfully");
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        //adding item to list of allItemsList
                        mItems list = new mItems();
                        list.setItem_id((cur.getInt(cur.getColumnIndexOrThrow("item_id"))));
                        list.setItem_name(cur.getString(cur.getColumnIndexOrThrow("item_name")));
                        list.setCategory(cur.getString(cur.getColumnIndexOrThrow("item_category")));
                        list.setItem_qty(cur.getInt(cur.getColumnIndexOrThrow("item_quantity")));
                        allItemsList.add(list);
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
        return allItemsList;
    }

    public void insertShoppingList(mShoppingList currList){
        //adding to db
        ContentValues cv = new ContentValues();
        cv.put("list_name", currList.getList_name());
        db.insert(TABLE_ShoppingLists, null, cv);
    }

    public void insertItem(mItems currItem){
        //adding  item to db
        ContentValues cv = new ContentValues();
        cv.put("item_name",currItem.getItem_name());
        db.insert(TABLE_ITEMS,null,cv);
    }

    public void insertQuantity(mItems currItem){
        //adding quantity to db
        ContentValues cv = new ContentValues();
        cv.put("item_quantity",currItem.getItem_qty());
        db.insert(TABLE_ITEMS,null,cv);
    }
    public void insertCategory(mItems currItem){
        //adding quantity to db
        ContentValues cv = new ContentValues();
        cv.put("item_category",currItem.getCategory());
        db.insert(TABLE_ITEMS,null,cv);
    }

    public void updateShoppingList(int list_id, String list_name) {
        //updating db
        final String ID = "list_id";
        ContentValues cv = new ContentValues();
        cv.put("list_name", list_name);
        db.update(TABLE_ShoppingLists, cv, ID + "= ?", new String[] {String.valueOf(list_id)});
    }

    public void updateItemName(int item_id, String item_name){
        //updating items in db
        final String itemID = "item_id";
        ContentValues cv = new ContentValues();
        cv.put("item_name", item_name);
        db.update(TABLE_ITEMS, cv, itemID +  "= ?", new String[]{String.valueOf(item_id)});
    }

    public void updateItemQuantity(int item_id, int item_quantity){
        //updating quantity in db
        final String itemQuantity = "item_quantity";
        ContentValues cv = new ContentValues();
        cv.put("item_quantity",item_quantity);
        db.update(TABLE_ITEMS,cv, itemQuantity + "= ?",new String[]{String.valueOf(item_id)});
    }

    public void updateItemCategory(int item_id, String item_category){
        //updating category in db
        final String itemCategory = "item_category";
        ContentValues cv = new ContentValues();
        cv.put("item_category", item_category);
        db.update(TABLE_ITEMS, cv, itemCategory + "= ?", new String[]{String.valueOf(item_id)});
    }

    public void deleteShoppingList(int list_id){
        //removing from db
        //toDo removing existing shoppingList using listID
        final String ID = "list_id";
        db.delete(TABLE_ShoppingLists, ID + "= ?", new String[] {String.valueOf(list_id)});
    }

    public void deleteItemsFromList(int item_id){
        //removing from db
        final String itemID = "item_id";
        db.delete(TABLE_ITEMS,itemID + "= ?", new String[]{String.valueOf(item_id)});
    }

}

package net.penguincoders.doit.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.penguincoders.doit.Model.mShoppingList;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "ListDatabase_2";

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

        final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" +
                itemID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                itemName + " TEXT" +
                itemQuantity + "INTEGER" +
                itemPrice + "INTEGER" +
                D_O_E + "DATE" +
                itemCategory + "TEXT" +
                ")";



        db.execSQL(CREATE_ShoppingLists_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);

        Log.d(TAG, "** ---> --> shopping list table Created");
        Log.d(TAG2, "** ---> --> items table created");
   }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ShoppingLists);
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
                if(cur.moveToFirst()){
                    do{
                        //adding shoppingList to list of allShoppingLists
                        mShoppingList list = new mShoppingList();
                        list.setList_id(cur.getInt(cur.getColumnIndex("list_id")));
                        list.setList_name(cur.getString(cur.getColumnIndex("list_name")));
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

    public void insertShoppingList(mShoppingList currList){
        //adding to db
        ContentValues cv = new ContentValues();
        cv.put("list_name", currList.getList_name());
        db.insert(TABLE_ShoppingLists, null, cv);
    }

    public void updateShoppingList(int list_id, String list_name) {
        //updating db
        final String ID = "list_id";
        ContentValues cv = new ContentValues();
        cv.put("list_name", list_name);
        db.update(TABLE_ShoppingLists, cv, ID + "= ?", new String[] {String.valueOf(list_id)});
    }

    public void deleteShoppingList(int list_id){
        //removing from db
        //toDo removing existing shoppingList using listID
        final String ID = "list_id";
        db.delete(TABLE_ShoppingLists, ID + "= ?", new String[] {String.valueOf(list_id)});
    }

}

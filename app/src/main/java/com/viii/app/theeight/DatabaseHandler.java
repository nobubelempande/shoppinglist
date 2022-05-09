package com.viii.app.theeight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "ShoppinglistsITEMS.db";
    private static final int DATABASE_VERSION = 223;

    private static final String TABLE_NAME = "item_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "item_name";
    private static final String COLUMN_CATEGORY = "item_category";
    private static final String COLUMN_QUANTITY = "item_quantity";
    private static final String COLUMN_PRICE = "item_price";
    private static final String COLUMN_EXPIRATION_DATE = "item_expiration_date";
   // private static final String LIST_NAME = "saved_list_name";

    private static final String TABLE_NAME2 = "list_table";
    private static final String LIST_ID = "list_id";
    private static final String LIST_NAME = "list_name";
    private static final String LIST_DATE = "list_date";

    DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER,"+
                COLUMN_PRICE + " DOUBLE," +
                COLUMN_EXPIRATION_DATE +" DATE,"+
                LIST_NAME + " TEXT);";

        String query2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LIST_NAME + " TEXT, " +
                LIST_DATE + " DATE);";

        db.execSQL(query);
        db.execSQL(query2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    void addItem(String name, String category, int quantity,String listname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(LIST_NAME, listname);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addList(String name, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LIST_NAME, name);
        cv.put(LIST_DATE, date);
        long result = db.insert(TABLE_NAME2,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


   /*Cursor readSelectedItems(){
        String query = "SELECT * FROM " + TABLE_NAME +" s "+ " JOIN "  + TABLE_NAME2 + " ON s." + LIST_NAME + ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }*/

    Cursor readAllItemsData(String nameOfList){
        String query = "SELECT * FROM item_table WHERE list_name ='"+ nameOfList+ "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readItemsData(){
        String query = "SELECT * FROM item_table WHERE item_price is not null";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllListData(){
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

   /* Cursor selectList(String list){
        String query = "SELECT "+ LIST_NAME + " FROM" + TABLE_NAME2 + "WHERE" +LIST_NAME "=" +list;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
*/
    void updateItems(String row_id, String name, String price, String expiry_date, String list){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_EXPIRATION_DATE, expiry_date);
        cv.put(LIST_NAME, list);



        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }



    void deleteOneItem(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneList(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteAllItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    void deleteAllLists(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME2);
    }

}

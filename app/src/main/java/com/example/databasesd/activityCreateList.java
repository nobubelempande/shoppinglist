package com.example.databasesd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class activityCreateList extends AppCompatActivity {

    //DBHandler
    databaseexample myDB;
    //uiHandlers
    EditText etListName;
    EditText etItemName;
    EditText etQuantity;
    EditText etListID;

        @SuppressLint("Range")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_list);

            //get shoppingListDB
            myDB=new databaseexample(this, "app");
        }




    public void nameList(View view) {
        //getting new shopping list name and id from editTexts
        EditText e = (EditText)findViewById(R.id.etNameList);
        etListID = (EditText)findViewById(R.id.eListId);

        //inserting new shopiingList name and id into DB
        String[] vals = {e.getText().toString(), etListID.getText().toString()};
        myDB.doUpdate("Insert into shoppingList(list_name, list_id) values (?,?);", vals);

        //showing name and id of new shoppingList on new textVeiw
        LinearLayout l = (LinearLayout)findViewById(R.id.layout2);
        TextView t = new TextView(this);
        String whole = e.getText().toString() + ", " + etListID.getText().toString();
        t.setText(whole);
        l.addView(t);
    }

    public void addItem(View view) {
        //getting new item (name, quantity and shoppingListID) from editTexts
        etItemName = (EditText)findViewById(R.id.eItemName);
        etQuantity = (EditText)findViewById(R.id.eItemQuantity);
        etListID = (EditText)findViewById(R.id.eListId);

        //adding new item (name, quantity and shoppingListID) into db
        String[] vals = {etItemName.getText().toString(), etQuantity.getText().toString(), etListID.getText().toString()};
        myDB.doUpdate("Insert into items(name, quantity,list_id_items_table) values (?,?,?);", vals);

        //showing item (name, quantity and shoppingListID on layout2
        LinearLayout l1 = (LinearLayout)findViewById(R.id.layout2);
        TextView t1 = new TextView(this);
        String whole = etItemName.getText().toString() + " x "  + etQuantity.getText().toString() + " shoppingListID: " + etListID.getText().toString();
        t1.setText(whole);
        l1.addView(t1);
    }

    @SuppressLint("Range")
    //show shoppingList_items on layout(referenced)
    public void viewList(View view) {
        //getting shoppingList name and id from editTexts
        etListName = (EditText)findViewById(R.id.etNameList);
        String strListID = etListID.getText().toString();

        //getting all items in shoppingList_ID: strListID from DB
        Cursor currItemName_Quantity =  myDB.doQuery("SELECT name, quantity FROM items WHERE list_id_items_table=" + strListID );

        //showing all shoppingList items on layout2
        LinearLayout ll2 = (LinearLayout)findViewById(R.id.layout2);
        TextView tv2 = new TextView(this);
        String whole = " ";
        while(currItemName_Quantity.moveToNext()) {
            //showing each item on a new line
            // using single textView
            whole += (currItemName_Quantity.getString(currItemName_Quantity.getColumnIndex("name"))
                    + "x"
                    + currItemName_Quantity.getLong(currItemName_Quantity.getColumnIndex("quantity"))
                    +"\n ");
        }
        tv2.setText(whole);
        ll2.addView(tv2);
    }
}
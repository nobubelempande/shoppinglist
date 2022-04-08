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

        databaseexample myDB;

        //ui
    EditText etListName;
    EditText etItemName;
    EditText etQuantity;
    EditText etListID;

        @SuppressLint("Range")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_list);
            myDB=new databaseexample(this, "app");
        }




    public void nameList(View view) {
        EditText e = (EditText)findViewById(R.id.etNameList);
        etListID = (EditText)findViewById(R.id.eListId);

        //updating db
        String[] vals = {e.getText().toString(), etListID.getText().toString()};
        myDB.doUpdate("Insert into shoppingList(list_name, list_id) values (?,?);", vals);

        //updating ui
        LinearLayout l = (LinearLayout)findViewById(R.id.layout2);
        TextView t = new TextView(this);
        String whole = e.getText().toString() + ", " + etListID.getText().toString();
        t.setText(whole);
        l.addView(t);
    }

    public void itemAdd(View view) {
        etItemName = (EditText)findViewById(R.id.eItemName);
        etQuantity = (EditText)findViewById(R.id.eItemQuantity);
        etListID = (EditText)findViewById(R.id.eListId);
        //etListID = (EditText)findViewById(R.id.eListIdForItems);

        //updating db
        String[] vals = {etItemName.getText().toString(), etQuantity.getText().toString(), etListID.getText().toString()};
        myDB.doUpdate("Insert into items(name, quantity,list_id_items_table) values (?,?,?);", vals);

        //updating ui
        LinearLayout l1 = (LinearLayout)findViewById(R.id.layout2);
        TextView t1 = new TextView(this);
        String whole = etItemName.getText().toString() + " x "  + etQuantity.getText().toString() + " ID: " + etListID.getText().toString();
        t1.setText(whole);
        l1.addView(t1);
    }

    @SuppressLint("Range")
    public void viewList(View view) {
        EditText e = (EditText)findViewById(R.id.etNameList);

        String strListID = etListID.getText().toString();

        //get data from DB
        Cursor s =  myDB.doQuery("SELECT name, quantity FROM items WHERE list_id_items_table=" + strListID );

        //update ui
        LinearLayout ll2 = (LinearLayout)findViewById(R.id.layout2);
        TextView tv2 = new TextView(this);
        String whole = " ";
        while(s.moveToNext()) {
            whole += (s.getString(s.getColumnIndex("name")) + "x" + s.getLong(s.getColumnIndex("quantity"))+"\n ");
        }
        tv2.setText(whole);
        ll2.addView(tv2);
    }
}
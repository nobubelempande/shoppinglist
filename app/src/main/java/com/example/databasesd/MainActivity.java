package com.example.databasesd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void popUp(View view) {


                        Intent i = new Intent(getApplicationContext(), activityCreateList.class);
                        Toast.makeText(getApplicationContext(), "Creating New Shopping List", Toast.LENGTH_SHORT).show();
                        startActivity(i);

                    }





   /*public void doAdd(View view) {
        EditText e = (EditText)findViewById(R.id.editTextItem);
        EditText a = (EditText)findViewById(R.id.editTextQuantity);
        String[] vals = {e.getText().toString(), a.getText().toString()};
        myDB.doUpdate("Insert into items(name, quantity) values (?,?);", vals);
        LinearLayout l = (LinearLayout)findViewById(R.id.layout);
        TextView t = new TextView(this);
        String whole = e.getText().toString() + " x "  + a.getText().toString();
        t.setText(whole);
        l.addView(t);
    }

    @SuppressLint("Range")
    public void doQuery(View v) {
        Cursor c = myDB.doQuery("SELECT name, quantity from items");
        while (c.moveToNext()) {
            System.out.println(c.getString(c.getColumnIndex("name")) + "," + c.getLong(c.getColumnIndex("quantity")));
        }
    }

    @SuppressLint("Range")
    public void doList(View view) {
        Cursor s =  myDB.doQuery("SELECT name, quantity from items");
        LinearLayout l = (LinearLayout)findViewById(R.id.layout);
        TextView t = new TextView(this);
        String whole = " ";
        while(s.moveToNext()) {
            whole += (s.getString(s.getColumnIndex("name")) + "," + s.getLong(s.getColumnIndex("quantity"))+ "\n ");
        }
        t.setText(whole);
        l.addView(t);
    }

    public void doInsert(View view) {
        String[] vals = {"Chicken","3"};
        myDB.doUpdate("Insert into items(name, quantity) values (?,?);", vals);

    }*/
}

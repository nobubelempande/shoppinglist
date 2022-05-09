package com.viii.app.theeight;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class makeListActivity extends AppCompatActivity {

    RecyclerView listRecyclerViewer;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    DatabaseHandler myDB;
    ArrayList<String> list_id, list_name, list_date;
    listAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_list);

        listRecyclerViewer = findViewById(R.id.listRecyclerView);
        add_button = findViewById(R.id.btnAddList);
        empty_imageview = findViewById(R.id.empty_view);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(makeListActivity.this, addListActivity.class);
                startActivity(intent);
            }
        });

        //    myDB = new MyDatabaseHelper(MainActivity.this);
        myDB=new DatabaseHandler(makeListActivity.this);
        list_id = new ArrayList<>();
        list_name = new ArrayList<>();
        list_date = new ArrayList<>();

        storeDataInArrays();

        listAdapter = new listAdapter(makeListActivity.this,this, list_id, list_name, list_date);
        listRecyclerViewer.setAdapter(listAdapter);
        listRecyclerViewer.setLayoutManager(new LinearLayoutManager(makeListActivity.this));

    }
   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllListData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()) {
                list_id.add(cursor.getString(0));
                list_name.add(cursor.getString(1));
                list_date.add(cursor.getString(2));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHandler myDB = new DatabaseHandler(makeListActivity.this);
                myDB.deleteAllLists();
                //Refresh Activity
                Intent intent = new Intent(makeListActivity.this, makeListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void toInventory(View view) {
        Intent intent = new Intent(makeListActivity.this, InventoryActivity.class);

        startActivity(intent);
    }
}

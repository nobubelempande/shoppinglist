package com.viiishoppinglistapp.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.viiishoppinglistapp.doit.Adapters.AddingItemsAdapter;
import com.viiishoppinglistapp.doit.Adapters.InventoryItemsAdapter;
import com.viiishoppinglistapp.doit.Adapters.UseShoppingListAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseShoppingListActivity extends AppCompatActivity {

    private RecyclerView rvUseShoppingList;
    private UseShoppingListAdapter adapter;

    private DatabaseHandler db;

    private List<modelItem> allShoppingListItems;
    private modelShoppingList currShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_shopping_list);

        getSupportActionBar().hide();

        setCurrShoppingList();

        setupShoppingList();
    }

    private void setCurrShoppingList() {
        db = new DatabaseHandler(this);
        db.openDatabase();

        Bundle bundle = getIntent().getExtras();
        String strListName = bundle.getString("list_name", "Default");

        TextView name = (TextView) findViewById(R.id.tvTop_useList);
        name.setText("List Name: " + strListName);

        currShoppingList = db.getShoppingList(strListName);
    }

    private void setupShoppingList() {
        setCurrShoppingList();

        rvUseShoppingList = findViewById(R.id.rvInUseShoppingList);
        rvUseShoppingList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UseShoppingListAdapter(db, this);
        rvUseShoppingList.setAdapter(adapter);

        allShoppingListItems = db.getItemsForShoppingList(currShoppingList.getListName());
        if (allShoppingListItems.size()<1){
            //doDoneUsingShoppingList();
        }
        Collections.reverse(allShoppingListItems);

        adapter.setAllShoppingListItems(allShoppingListItems);
    }

    private void doDoneUsingShoppingList() {
        //goto new page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", currShoppingList.getListName());
        Intent I = new Intent(this,MainActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
        Toast.makeText(this, "Done Using Shopping List.", Toast.LENGTH_SHORT).show();
    }

    //Nav
    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", currShoppingList.getListName());
        Intent I = new Intent(this,MainActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }
}
package com.viiishoppinglistapp.doit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.Adapters.InventoryItemsAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.Collections;
import java.util.List;

public class InventoryActivity_old extends AppCompatActivity {

    private RecyclerView rvInventory;
    private InventoryItemsAdapter adapter;

    private DatabaseHandler db;

    private List<modelItem> allInventoryItems;
    private modelShoppingList currShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_old);

        getSupportActionBar().hide();

        setupInventory();


    }

    private void setCurrShoppingList() {
        Bundle bundle = getIntent().getExtras();
        String strListName = bundle.getString("list_name", "Default");

        currShoppingList = new modelShoppingList();
        currShoppingList.setListName(strListName);
    }

    private void setupInventory() {
        setCurrShoppingList();

        db = new DatabaseHandler(this);
        db.openDatabase();

        rvInventory = findViewById(R.id.rvInventory);
        rvInventory.setLayoutManager(new LinearLayoutManager(this));
        //todo adapter = new InventoryItemsAdapter(db, this);
        rvInventory.setAdapter(adapter);

        //todo: itemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new InventoryItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(rvInventory);

        allInventoryItems = db.getAllInventoryItems();
        Collections.reverse(allInventoryItems);
        adapter.setAllInventoryItems(allInventoryItems);
    }


    //Nav
    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "TestDay ShoppingList");
        Intent I = new Intent(this, HomeActivity_old.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }
}
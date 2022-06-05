package com.viiishoppinglistapp.doit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.Adapters.ItemCategoryAdapter;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;

public class ItemCategoryActivity extends AppCompatActivity {

    Spinner spinner;
    RecyclerView itemRecyclerViewer;

    DatabaseHandler myDB;
    ArrayList<String> item_id, item_name, item_category, item_quantity;

    ItemCategoryAdapter itemCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);
        spinner = findViewById(R.id.spinner_items);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);



        itemRecyclerViewer = findViewById(R.id.itemRecyclerView);

        myDB=new DatabaseHandler(ItemCategoryActivity.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_category = new ArrayList<>();
        item_quantity = new ArrayList<>();

        spinner.setAdapter(adapter);
        final String category1 = spinner.getSelectedItem().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItem().toString().equals("Food")) {
                    item_id.clear();
                    item_name.clear();
                    item_category.clear();
                    item_quantity.clear();
                    storeDataInArrays("Food");
                    itemCategoryAdapter = new ItemCategoryAdapter(ItemCategoryActivity.this, ItemCategoryActivity.this, item_id, item_name, item_category,
                            item_quantity);
                    itemRecyclerViewer.setAdapter(itemCategoryAdapter);
                    itemRecyclerViewer.setLayoutManager(new LinearLayoutManager(ItemCategoryActivity.this));
                }else if (spinner.getSelectedItem().toString().equals( "Appliance")) {
                    item_id.clear();
                    item_name.clear();
                    item_category.clear();
                    item_quantity.clear();
                    storeDataInArrays("Appliance");
                    itemCategoryAdapter = new ItemCategoryAdapter(ItemCategoryActivity.this, ItemCategoryActivity.this, item_id, item_name, item_category,
                            item_quantity);
                    itemRecyclerViewer.setAdapter(itemCategoryAdapter);
                    itemRecyclerViewer.setLayoutManager(new LinearLayoutManager(ItemCategoryActivity.this));
                }else if (spinner.getSelectedItem().toString().equals("Clothing")) {
                    item_id.clear();
                    item_name.clear();
                    item_category.clear();
                    item_quantity.clear();
                    storeDataInArrays("Clothing");
                    itemCategoryAdapter = new ItemCategoryAdapter(ItemCategoryActivity.this, ItemCategoryActivity.this, item_id, item_name, item_category,
                            item_quantity);
                    itemRecyclerViewer.setAdapter(itemCategoryAdapter);
                    itemRecyclerViewer.setLayoutManager(new LinearLayoutManager(ItemCategoryActivity.this));
                }else if (spinner.getSelectedItem().toString().equals("Personal Hygiene")) {
                    item_id.clear();
                    item_name.clear();
                    item_category.clear();
                    item_quantity.clear();
                    storeDataInArrays("Personal Hygiene");
                    itemCategoryAdapter = new ItemCategoryAdapter(ItemCategoryActivity.this, ItemCategoryActivity.this, item_id, item_name, item_category,
                            item_quantity);
                    itemRecyclerViewer.setAdapter(itemCategoryAdapter);
                    itemRecyclerViewer.setLayoutManager(new LinearLayoutManager(ItemCategoryActivity.this));
                }else if (spinner.getSelectedItem().toString().equals("Toys and Games")) {
                    item_id.clear();
                    item_name.clear();
                    item_category.clear();
                    item_quantity.clear();
                    storeDataInArrays("Toys And Games");
                    itemCategoryAdapter = new ItemCategoryAdapter(ItemCategoryActivity.this, ItemCategoryActivity.this, item_id, item_name, item_category,
                            item_quantity);
                    itemRecyclerViewer.setAdapter(itemCategoryAdapter);
                    itemRecyclerViewer.setLayoutManager(new LinearLayoutManager(ItemCategoryActivity.this));
                }else if (spinner.getSelectedItem().toString().equals("Other")) {
                    item_id.clear();
                    item_name.clear();
                    item_category.clear();
                    item_quantity.clear();
                    storeDataInArrays("Other");
                    itemCategoryAdapter = new ItemCategoryAdapter(ItemCategoryActivity.this, ItemCategoryActivity.this, item_id, item_name, item_category,
                            item_quantity);
                    itemRecyclerViewer.setAdapter(itemCategoryAdapter);
                    itemRecyclerViewer.setLayoutManager(new LinearLayoutManager(ItemCategoryActivity.this));
                }else if (spinner.getSelectedItem().toString().equals("Stationery")) {
                    item_id.clear();
                    item_name.clear();
                    item_category.clear();
                    item_quantity.clear();
                    storeDataInArrays("Stationery");
                    itemCategoryAdapter = new ItemCategoryAdapter(ItemCategoryActivity.this, ItemCategoryActivity.this, item_id, item_name, item_category,
                            item_quantity);
                    itemRecyclerViewer.setAdapter(itemCategoryAdapter);
                    itemRecyclerViewer.setLayoutManager(new LinearLayoutManager(ItemCategoryActivity.this));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }

    void storeDataInArrays(String category){
        Cursor cursor = myDB.readItemsCategory(category);
        while (cursor.moveToNext()){
                item_id.add(cursor.getString(2));
                item_name.add(cursor.getString(1));
                item_category.add(cursor.getString(6));
                item_quantity.add(cursor.getString(5));

            }
        }

    public void goHome(View view) {
        Intent I = new Intent(this, TabbedHomeActivity.class);
        this.startActivity(I);
    }
}
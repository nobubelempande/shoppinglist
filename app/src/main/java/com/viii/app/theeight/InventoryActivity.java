package com.viii.app.theeight;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data, priceView;

    DatabaseHandler myDB;
    ArrayList<String> inventory_item_id, inventory_item_name, inventory_item_category, inventory_item_quantity, inventory_item_price, inventory_item_expiry;
    InventoryAdapter InventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_view);
        no_data = findViewById(R.id.no_data);
        priceView = findViewById(R.id.price_txt);

        //    myDB = new MyDatabaseHelper(InventoryActivity.this);
        myDB=new DatabaseHandler(InventoryActivity.this);
        inventory_item_id = new ArrayList<>();
        inventory_item_name = new ArrayList<>();
        inventory_item_category = new ArrayList<>();
        inventory_item_quantity = new ArrayList<>();
        inventory_item_price = new ArrayList<>();
        inventory_item_expiry = new ArrayList<>();

        storeDataInArrays();

        InventoryAdapter = new InventoryAdapter(InventoryActivity.this,this,inventory_item_id, inventory_item_name, inventory_item_category, inventory_item_quantity, inventory_item_price, inventory_item_expiry);
        recyclerView.setAdapter(InventoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));

        int price = 0;
        for(int i = 0; i < inventory_item_price.size(); i++){
            price += Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i));
        }
        priceView.setText("Total Expenditure : R"+price);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readItemsData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                inventory_item_id.add(cursor.getString(0));
                inventory_item_name.add(cursor.getString(1));
                inventory_item_category.add(cursor.getString(2));
                inventory_item_quantity.add(cursor.getString(3));
                inventory_item_price.add(cursor.getString(4));
                inventory_item_expiry.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

}
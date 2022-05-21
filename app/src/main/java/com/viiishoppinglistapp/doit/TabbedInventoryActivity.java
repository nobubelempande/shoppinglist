package com.viiishoppinglistapp.doit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.viiishoppinglistapp.doit.Adapters.InventoryItemsAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;
import com.viiishoppinglistapp.doit.databinding.ActivityTabsInventoryBinding;
import com.viiishoppinglistapp.doit.ui.inventory.InventorySectionsPagerAdapter;

import java.util.List;

public class TabbedInventoryActivity extends AppCompatActivity {

    private ActivityTabsInventoryBinding binding;

    private RecyclerView rvInventory;
    private InventoryItemsAdapter adapter;

    private DatabaseHandler db;

    private List<modelItem> allInventoryItems;
    private modelShoppingList currShoppingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(HomeActivity_old.TAG, "onCreate: Setup Tabs Start");

        setupTabs();

        //setupInventory();

        Log.d(HomeActivity_old.TAG, "onCreate: Setup Tabs Done ***");

    }

    private void setupTabs() {
        binding = ActivityTabsInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        InventorySectionsPagerAdapter inventorySectionsPagerAdapter = new InventorySectionsPagerAdapter(this, getSupportFragmentManager(), this);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(inventorySectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


        Log.d(HomeActivity_old.TAG, "onCreate: Setup Tabs Done");
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
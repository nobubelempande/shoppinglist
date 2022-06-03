package com.viiishoppinglistapp.doit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.viiishoppinglistapp.doit.databinding.ActivityTabbedInventoryBinding;
import com.viiishoppinglistapp.doit.ui.inventory.InventorySectionsPagerAdapter;


public class TabbedInventoryActivity extends AppCompatActivity {

    private ActivityTabbedInventoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_old);

        setupTabs();

    }

    private void setupTabs() {
        binding = ActivityTabbedInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        InventorySectionsPagerAdapter inventorySectionsPagerAdapter = new InventorySectionsPagerAdapter(this, getSupportFragmentManager(), this);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(inventorySectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }

    //Nav
    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "TestDay ShoppingList");
        Intent I = new Intent(this, TabbedHomeActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }

    public void goToStats(View view) {
        Intent i = new Intent(this, StatisticsActivity.class);
        this.startActivity(i);
    }

    public void goToBar(View view) {
        Intent i = new Intent(this, BarChartActivity.class);
        this.startActivity(i);
    }

    public void goToItemCategory(View view){
        Intent i = new Intent(this, ItemCategoryActivity.class);
        this.startActivity(i);
    }
}
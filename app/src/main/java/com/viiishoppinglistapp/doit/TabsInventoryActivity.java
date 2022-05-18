package com.viiishoppinglistapp.doit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.viiishoppinglistapp.doit.Adapters.InventoryItemsAdapter;
import com.viiishoppinglistapp.doit.Adapters.UseShoppingListAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;
import com.viiishoppinglistapp.doit.databinding.ActivityTabsInventoryBinding;
import com.viiishoppinglistapp.doit.ui.main.SectionsPagerAdapter;

import java.util.Collections;
import java.util.List;

public class TabsInventoryActivity extends AppCompatActivity {

    private ActivityTabsInventoryBinding binding;

    private RecyclerView rvInventory;
    private InventoryItemsAdapter adapter;

    private DatabaseHandler db;

    private List<modelItem> allInventoryItems;
    private modelShoppingList currShoppingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MainActivity.TAG, "onCreate: Setup Tabs Start");

        setupTabs();

        //setupInventory();

        Log.d(MainActivity.TAG, "onCreate: Setup Tabs Done ***");

    }

    private void setupTabs() {
        binding = ActivityTabsInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), this);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


        Log.d(MainActivity.TAG, "onCreate: Setup Tabs Done");
    }

    //Nav
    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "TestDay ShoppingList");
        Intent I = new Intent(this,MainActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }

}
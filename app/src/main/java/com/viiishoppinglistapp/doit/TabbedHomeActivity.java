package com.viiishoppinglistapp.doit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.viiishoppinglistapp.doit.Adapters.UnusedShoppingListsAdapter;
import com.viiishoppinglistapp.doit.Adapters.UsedShoppingListsAdapter;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;
import com.viiishoppinglistapp.doit.ui.home.HomeSectionsPagerAdapter;
import com.viiishoppinglistapp.doit.databinding.ActivityTabbedHomeBinding;

import java.util.Collections;
import java.util.List;

public class  TabbedHomeActivity extends AppCompatActivity implements DialogCloseListener {

    private static final String TAG = "VIII";
    UsedShoppingListsAdapter usedShoppingListAdapter;
    UnusedShoppingListsAdapter unusedShoppingListAdapter;
    HomeSectionsPagerAdapter homeSectionsPagerAdapter;

    ViewPager viewPager;
    Switch aSwitch;

    DatabaseHandler db;

    List<modelShoppingList> allShoppingLists;

    private ActivityTabbedHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHandler(this);
        db.openDatabase();
        usedShoppingListAdapter = new UsedShoppingListsAdapter(db,this);
        unusedShoppingListAdapter = new UnusedShoppingListsAdapter(db,this);
        binding = ActivityTabbedHomeBinding.inflate(getLayoutInflater());

        setupHomeTabs();

    }


    private void setupHomeTabs() {
        setContentView(binding.getRoot());

        homeSectionsPagerAdapter = new HomeSectionsPagerAdapter(this, getSupportFragmentManager(), this);
        viewPager = binding.viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(homeSectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fabAddNewShoppingListTabbed;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewShoppingList i = new AddNewShoppingList();
                i.newInstance();
                i.show(getSupportFragmentManager(), AddNewShoppingList.TAG);

            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        usingUnusedShoppingLists();
        usingUsedShoppingLists();
        homeSectionsPagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(homeSectionsPagerAdapter);
    }


    //shoppingLists ::

    private void usingUnusedShoppingLists() {
        allShoppingLists = db.getAllUnusedShoppingLists();
        Collections.reverse(allShoppingLists);
        unusedShoppingListAdapter.setAllUnusedShoppingLists(allShoppingLists);
        unusedShoppingListAdapter.notifyDataSetChanged();

    }
    private void usingUsedShoppingLists() {
        allShoppingLists = db.getAllUsedShoppingLists();
        Collections.reverse(allShoppingLists);
        usedShoppingListAdapter.setAllUsedShoppingLists(allShoppingLists);
        usedShoppingListAdapter.notifyDataSetChanged();

    }

    //Nav
    public void goToInventory(View view){
        //toDO remove list name bundle
        //goto new page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "No List Selected.");
        Intent I = new Intent(this, TabbedInventoryActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }
}
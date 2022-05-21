package com.viiishoppinglistapp.doit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.viiishoppinglistapp.doit.ui.home.HomeSectionsPagerAdapter;
import com.viiishoppinglistapp.doit.databinding.ActivityTabbedHomeBinding;

public class TabbedHomeActivity extends AppCompatActivity {

    private ActivityTabbedHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTabbedHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HomeSectionsPagerAdapter homeSectionsPagerAdapter = new HomeSectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
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
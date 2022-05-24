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
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupTabs();

    }

    private void setupTabs() {
        binding = ActivityTabbedInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        InventorySectionsPagerAdapter inventorySectionsPagerAdapter = new InventorySectionsPagerAdapter(this, getSupportFragmentManager(), this);
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

}
package com.viiishoppinglistapp.doit.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.Adapters.InventoryItemsAdapter;
import com.viiishoppinglistapp.doit.InventoryItemTouchHelper;
import com.viiishoppinglistapp.doit.MainActivity;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedInventoryActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class fragmentInventoryItems extends Fragment {

    TabbedInventoryActivity activity;
    InventoryItemsAdapter adapter;

    RecyclerView rvInventory;

    DatabaseHandler db;

    List<modelItem> allInventoryItems;
    modelShoppingList currShoppingList;

    final Context mContext;


    public fragmentInventoryItems(Context C, TabbedInventoryActivity activity){
        this.mContext = C;
        this.activity = activity;
        allInventoryItems = new ArrayList<>();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {

        return inflater.inflate(R.layout.inventory_items_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupInventory(view);
    }

    private void setCurrShoppingList() {
        Bundle bundle = activity.getIntent().getExtras();
        String strListName = bundle.getString("list_name", "Default");

        currShoppingList = new modelShoppingList();
        currShoppingList.setListName(strListName);

    }

    private void setupInventory(View root) {
        setCurrShoppingList();

        db = new DatabaseHandler(mContext);
        db.openDatabase();

        rvInventory = root.findViewById(R.id.rvInventory_fragment);
        rvInventory.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new InventoryItemsAdapter(db, activity);


        //todo: itemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new InventoryItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(rvInventory);

        allInventoryItems = db.getAllInventoryItems();
        Collections.reverse(allInventoryItems);
        adapter.setAllInventoryItems(allInventoryItems);

        rvInventory.setAdapter(adapter);

    }



}

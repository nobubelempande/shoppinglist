package com.viiishoppinglistapp.doit.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedHomeActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class fragmentHomeUnusedLists extends Fragment {

    TabbedHomeActivity activity;
    UnusedShoppingListsAdapter adapter;

    RecyclerView rvUnusedShoppingLists;

    DatabaseHandler db;

    List<modelItem> allShoppingLists;
    modelShoppingList currShoppingList;

    final Context mContext;


    public fragmentHomeUnusedLists(Context C, TabbedHomeActivity activity){
        this.mContext = C;
        this.activity = activity;
        allShoppingLists = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_unused_shopping_lists_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupUnusedShoppingLists(view);
    }


    private void setCurrShoppingList() {
        Bundle bundle = activity.getIntent().getExtras();
        String strListName = bundle.getString("list_name", "Default");

        currShoppingList = new modelShoppingList();
        currShoppingList.setListName(strListName);

    }

    private void setupUnusedShoppingLists(View root) {
        setCurrShoppingList();

        db = new DatabaseHandler(mContext);
        db.openDatabase();

        rvUnusedShoppingLists = root.findViewById(R.id.rvHomeUnusedLists_fragment);
        rvUnusedShoppingLists.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new UnusedShoppingListsAdapter(db, activity);


        //todo: itemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new UnsedShoppingListTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(rvUnusedShoppingLists);

        allShoppingLists = db.getAllUnusedShoppingLists();
        Collections.reverse(allShoppingLists);
        adapter.setAllUnusedShoppingLists(allShoppingLists);

        rvUnusedShoppingLists.setAdapter(adapter);

    }
}

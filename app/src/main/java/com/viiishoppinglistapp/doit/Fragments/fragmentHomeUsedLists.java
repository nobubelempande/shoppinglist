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

import com.viiishoppinglistapp.doit.Adapters.UsedShoppingListsAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedHomeActivity;
import com.viiishoppinglistapp.doit.UsedShoppingListTouchHelper;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class fragmentHomeUsedLists extends Fragment {

    TabbedHomeActivity activity;
    UsedShoppingListsAdapter adapter;

    RecyclerView rvUsedShoppingLists;

    DatabaseHandler db;

    List<modelShoppingList> allShoppingLists;
    modelShoppingList currShoppingList;

    final Context mContext;


    public fragmentHomeUsedLists(Context C, TabbedHomeActivity activity){
        this.mContext = C;
        this.activity = activity;
        allShoppingLists = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_layout_home_used_shopping_lists, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupUsedShoppingLists(view);
    }


    private void setCurrShoppingList() {
        Bundle bundle = activity.getIntent().getExtras();
        String strListName = bundle.getString("list_name", "Default");

        currShoppingList = new modelShoppingList();
        currShoppingList.setListName(strListName);

    }

    private void setupUsedShoppingLists(View root) {
        setCurrShoppingList();

        db = new DatabaseHandler(mContext);
        db.openDatabase();

        rvUsedShoppingLists = root.findViewById(R.id.rvHomeUsedLists_fragment);
        rvUsedShoppingLists.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new UsedShoppingListsAdapter(db, activity);


        //todo: itemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new UsedShoppingListTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(rvUsedShoppingLists);

        allShoppingLists = db.getAllUsedShoppingLists();
        Collections.reverse(allShoppingLists);
        adapter.setAllUsedShoppingLists(allShoppingLists);

        rvUsedShoppingLists.setAdapter(adapter);

    }
}

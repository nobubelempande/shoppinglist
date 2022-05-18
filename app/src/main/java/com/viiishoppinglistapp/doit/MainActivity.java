package com.viiishoppinglistapp.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.viiishoppinglistapp.doit.Adapters.SavedShoppingListAdapter;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private DatabaseHandler db;

    private RecyclerView shoppingListsRecyclerView;
    private SavedShoppingListAdapter shoppingListAdapter;
    private FloatingActionButton fab;


    //own
    public static final String TAG = "VIII-App";
    private List<modelShoppingList> allSavedShoppingLists;
    private modelShoppingList currShoppingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUsingShoppingLists();

    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        usingShoppingLists();
    }


    //shoppingLists ::

    private void usingShoppingLists() {
        allSavedShoppingLists = db.getAllShoppingLists();
        Collections.reverse(allSavedShoppingLists);
        shoppingListAdapter.setAllShoppingLists(allSavedShoppingLists);
        shoppingListAdapter.notifyDataSetChanged();

    }

    private void setupUsingShoppingLists() {
        //given
        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        shoppingListsRecyclerView = findViewById(R.id.rvShoppingListsHome);
        shoppingListsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingListAdapter = new SavedShoppingListAdapter(db, MainActivity.this);
        shoppingListsRecyclerView.setAdapter(shoppingListAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SavedShoppingListTouchHelper(shoppingListAdapter));
        itemTouchHelper.attachToRecyclerView(shoppingListsRecyclerView);

        //add new shoppingList to RecyclerViewList button
        fab = findViewById(R.id.fabAddShoppingList);

        allSavedShoppingLists = db.getAllShoppingLists();
        Collections.reverse(allSavedShoppingLists);

        //setupCurrentShoppingList();

        shoppingListAdapter.setAllShoppingLists(allSavedShoppingLists);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewShoppingList i = new AddNewShoppingList();
                i.newInstance();
                i.show(getSupportFragmentManager(), AddNewShoppingList.TAG);

            }
        });
    }

    private void setupCurrentShoppingList() {
        if (allSavedShoppingLists.get(0) != null){
            currShoppingList = allSavedShoppingLists.get(0);
        }
        else return;
    }


    //Navigation

    public void goToInventory(View view){
        //toDO remove list name bundle
        //goto new page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "Opening Inventory");
        Intent I = new Intent(this,TabsInventoryActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }


}
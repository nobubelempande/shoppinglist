package com.viiishoppinglistapp.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viiishoppinglistapp.doit.Adapters.AddingItemsAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.Collections;
import java.util.List;

public class AddShoppingListItemsActivity extends AppCompatActivity implements DialogCloseListener {

    private final String TAG = "VIII-App";

    private RecyclerView rvItems;
    private AddingItemsAdapter adapterItems;

    private FloatingActionButton fabAddItem;

    private DatabaseHandler db;

    private List<modelItem> itemsList;
    private modelShoppingList currShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        setCurrShoppingList();
        setupItems();

        ImageView icon = findViewById(R.id.imgVIII_icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSettings();
            }
        });
    }

    private void setCurrShoppingList() {
        db = new DatabaseHandler(this);
        db.openDatabase();

        Bundle bundle = getIntent().getExtras();
        String strListName = bundle.getString("list_name", "Default");

        TextView name = (TextView) findViewById(R.id.tvTop_newActivity);
        name.setText(strListName);

        currShoppingList = db.getShoppingList(strListName);
    }

    private void setupItems() {
        //db = new DatabaseHandler(this);
        //db.openDatabase();

        rvItems = findViewById(R.id.rvItems_newActivity);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        adapterItems = new AddingItemsAdapter(db, this);
        rvItems.setAdapter(adapterItems);

        //toDo: itemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NewItemTouchHelper(adapterItems));
        itemTouchHelper.attachToRecyclerView(rvItems);


        itemsList = db.getItemsForShoppingList(currShoppingList.getListName());
        Collections.reverse(itemsList);
        adapterItems.setAllItems(itemsList);

        fabAddItem = findViewById(R.id.fabAddNewItem_NewActivity);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("listName", currShoppingList.getListName());


                AddNewItem i = new AddNewItem();
                i.newInstance();
                i.setArguments(bundle);
                i.show(getSupportFragmentManager(), AddNewItem.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        usingItems();
    }

    private void usingItems() {
        itemsList = db.getItemsForShoppingList(currShoppingList.getListName());
        Collections.reverse(itemsList);
        adapterItems.setAllItems(itemsList);
        adapterItems.notifyDataSetChanged();
    }


    //navigation
    public void goToHome(View view){
        //toDO remove bundle to Home
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", currShoppingList.getListName());
        Intent I = new Intent(this, TabbedHomeActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }
    public void goToSettings(){
        //goto settings
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "No List Selected.");
        Intent I = new Intent(this, SettingsActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }
}
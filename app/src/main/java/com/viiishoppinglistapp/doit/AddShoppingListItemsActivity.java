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
        int intID = bundle.getInt("listID", 0);

        TextView name = (TextView) findViewById(R.id.tvTop_newActivity);
        name.setText(strListName);

        currShoppingList = db.getShoppingList(intID);
    }

    private void setupItems() {
        rvItems = findViewById(R.id.rvItems_newActivity);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        adapterItems = new AddingItemsAdapter(db, this);
        rvItems.setAdapter(adapterItems);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ShoppingListItemTouchHelper(adapterItems));
        itemTouchHelper.attachToRecyclerView(rvItems);

        itemsList = db.getItemsForShoppingList(currShoppingList.getListID());
        Collections.reverse(itemsList);
        adapterItems.setAllItems(itemsList, currShoppingList);

        fabAddItem = findViewById(R.id.fabAddNewItem_NewActivity);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("listName", currShoppingList.getListName());
                bundle.putInt("listID", currShoppingList.getListID());

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
        //re-setup page using shopping list items
        itemsList = db.getItemsForShoppingList(currShoppingList.getListID());
        Collections.reverse(itemsList);
        adapterItems.setAllItems(itemsList, currShoppingList);
        adapterItems.notifyDataSetChanged();
    }


    //navigation
    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
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
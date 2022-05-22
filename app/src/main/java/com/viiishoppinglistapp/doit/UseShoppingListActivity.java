package com.viiishoppinglistapp.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.viiishoppinglistapp.doit.Adapters.UseShoppingListAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.Collections;
import java.util.List;

public class UseShoppingListActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView rvUseShoppingList;
    private UseShoppingListAdapter adapter;

    private DatabaseHandler db;

    private List<modelItem> allShoppingListItems;
    private modelShoppingList currShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_shopping_list);

        getSupportActionBar().hide();

        setCurrShoppingList();
        setupShoppingList();
    }

    private void setCurrShoppingList() {
        db = new DatabaseHandler(this);
        db.openDatabase();

        Bundle bundle = getIntent().getExtras();
        String strListName = bundle.getString("list_name", "Default");

        TextView name = (TextView) findViewById(R.id.tvTop_useList);
        name.setText("Using " + strListName + " Shopping List");

        currShoppingList = new modelShoppingList();
        currShoppingList = db.getShoppingList(strListName);
    }

    private void setupShoppingList() {
        rvUseShoppingList = findViewById(R.id.rvInUseShoppingList);
        rvUseShoppingList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UseShoppingListAdapter(db, this);
        rvUseShoppingList.setAdapter(adapter);


        allShoppingListItems = db.getItemsForShoppingList(currShoppingList.getListName());

        if (allShoppingListItems.size()<1){
            //no items
            Toast.makeText(this, "Add Items To Your Shopping List.", Toast.LENGTH_SHORT).show();
        }
        Collections.reverse(allShoppingListItems);

        Log.d(HomeActivity_old.TAG, "USE LIST:: Setup --> Adapter Items Set");

        adapter.setAllShoppingListItems(allShoppingListItems);

        Log.d(HomeActivity_old.TAG, "USE LIST:: Setup --> Adapter Set II");
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        usingItems();
    }

    private void usingItems() {
        allShoppingListItems = db.getItemsForShoppingList(currShoppingList.getListName());
        Collections.reverse(allShoppingListItems);
        adapter.setAllShoppingListItems(allShoppingListItems);
        adapter.notifyDataSetChanged();

        boolean allItemsChecked = areAllItemsChecked(allShoppingListItems);

        if(allItemsChecked){
            //set used
            currShoppingList.setToUsed();
            db.updateShoppingList(currShoppingList);
            //close page
            Toast.makeText(this, "Done Using " + currShoppingList.getListName(), Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    doDoneUsingList();

                }
            }, 1600);
        }
    }

    private boolean areAllItemsChecked(List<modelItem> allShoppingListItems) {
        for(modelItem I:allShoppingListItems){
            if(!I.isChecked()){
                return false;
            }
        }
        return true;
    }


    //Nav
    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", currShoppingList.getListName());
        Intent I = new Intent(this, TabbedHomeActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }
    public void doDoneUsingList(){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", currShoppingList.getListName());
        Intent I = new Intent(this, TabbedHomeActivity.class);
        I.putExtras(bundle);

        this.startActivity(I);
    }
}
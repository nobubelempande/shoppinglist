package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.penguincoders.doit.Adapters.ItemAdapter;
import net.penguincoders.doit.Adapters.ToDoAdapter;
import net.penguincoders.doit.Utils.DatabaseHandler;

import java.util.Objects;


public class itemAddingActivity extends AppCompatActivity implements DialogCloseListener{
    private final String TAG = "VIII";

    private DatabaseHandler db;

    private RecyclerView itemsRecyclerView;
    private ItemAdapter itemAdapter;
    private FloatingActionButton itemadd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_adding);

        Bundle bundle = getIntent().getExtras();
        String listName = bundle.getString("list_name");

        TextView name = (TextView) findViewById(R.id.nameView);
        name.setText(listName);

        Log.d(TAG, "setup start");

        setPageUsingShoppingLists();

        Log.d(TAG, "setup complete");






    }
    private void setPageUsingShoppingLists() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        db = new DatabaseHandler(this);
        db.openDatabase();

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(db, itemAddingActivity.this);
        itemsRecyclerView.setAdapter(itemAdapter);


        itemadd = findViewById(R.id.itemadd);

        itemadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewItem.newInstance().show(getSupportFragmentManager(), AddNewItem.TAG);


            }



        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {

    }
}
package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.penguincoders.doit.Adapters.ToDoAdapter;
import net.penguincoders.doit.Model.mShoppingList;
import net.penguincoders.doit.Utils.DatabaseHandler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;


    //own
    private final String TAG = "VIII";
    private List<mShoppingList> allSavedShoppingLists;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "setup start");

        setPageUsingShoppingLists();

        Log.d(TAG, "setup complete");
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        usingShoppingLists();
    }


    //shoppingLists ::

    private void usingShoppingLists() {
        allSavedShoppingLists = db.getAllShoppingLists();
        Collections.reverse(allSavedShoppingLists);
        tasksAdapter.setAllShoppingLists(allSavedShoppingLists);
        tasksAdapter.notifyDataSetChanged();
    }

    private void setPageUsingShoppingLists() {
        //given
        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db, MainActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        //add new shoppingList to RecyclerViewList button
        fab = findViewById(R.id.fab);

        allSavedShoppingLists = db.getAllShoppingLists();
        Collections.reverse(allSavedShoppingLists);
        tasksAdapter.setAllShoppingLists(allSavedShoppingLists);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);


            }
            ;

        });
    }


}
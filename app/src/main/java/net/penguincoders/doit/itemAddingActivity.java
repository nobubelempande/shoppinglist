package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class itemAddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_adding);

        Bundle bundle = getIntent().getExtras();
        String listName = bundle.getString("list_name");

        TextView name = (TextView) findViewById(R.id.nameView);
        name.setText(listName);
    }

    //viewing selected shopping list
    //from MainActivity

    //toDO edit selectedShoppingList
}
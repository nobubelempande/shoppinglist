package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class activitySelectedShoppingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_adding);

        Bundle bundle = getIntent().getExtras();
        String task = bundle.getString("list_name", "Default");

        TextView name = (TextView) findViewById(R.id.nameView);
        name.setText(task);

    }
}
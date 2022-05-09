package com.viii.app.theeight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//THIS ACTIVITY IS FOR ADDING THE ITEMS OF EACH SPECIFIC LIST
public class AddActivity extends AppCompatActivity {

    EditText item_name, item_category, item_quantity;
    TextView listname;
    Button add_button;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);





        listname= findViewById(R.id.list_activity_name);
        item_name = findViewById(R.id.item_name);
        item_category = findViewById(R.id.item_category);
        item_quantity = findViewById(R.id.item_quantity);

        name=getIntent().getStringExtra("key");

        listname.setText(name);
        //Add new item

        add_button = findViewById(R.id.btnIAddtem);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler myDB = new DatabaseHandler(AddActivity.this);
                myDB.addItem(item_name.getText().toString().trim(),
                        item_category.getText().toString().trim(),
                        Integer.valueOf(item_quantity.getText().toString().trim()),
                        listname.getText().toString().trim());
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                i.putExtra("list_name",name);
                startActivity(i);
            }
        });
    }
}

package com.viii.app.theeight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addListActivity extends AppCompatActivity {

    EditText list_name, list_date;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_actvity);

        list_name = findViewById(R.id.list_name);
        list_date = findViewById(R.id.list_date);

        add_button = findViewById(R.id.btnIAddtem);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler myDB = new DatabaseHandler(addListActivity.this);
                myDB.addList(list_name.getText().toString().trim(),
                         list_date.getText().toString().trim());
            }
        });
    }
}
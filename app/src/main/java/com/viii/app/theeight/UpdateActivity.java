package com.viii.app.theeight;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText item_name, item_price,item_expiration_date ;
    Button update_button, delete_button;
    TextView nameOfList;

    String id, name, price, expiry_date;
    String gotString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        item_name = findViewById(R.id.item_name2_txt);
        item_expiration_date  = findViewById(R.id.item_date);
        item_price = findViewById(R.id.item_price);
        nameOfList = (TextView)findViewById(R.id.update_list_name);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        gotString = Global.stringToPass;
        nameOfList.setText(gotString);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DatabaseHandler myDB = new DatabaseHandler(UpdateActivity.this);
                name = item_name.getText().toString().trim();
                price = item_price.getText().toString().trim();
                expiry_date = item_expiration_date.getText().toString().trim();
                String list = " ";
                myDB.updateItems(id, name, price, expiry_date, list);
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                i.putExtra("list_name",gotString);
                startActivity(i);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
       if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("title");

            //Setting Intent Data
            item_name.setText(name);

            Log.d("stev", name+" ");
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHandler myDB = new DatabaseHandler(UpdateActivity.this);
                myDB.deleteOneItem(id);
                finish();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra("list_name",gotString);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}

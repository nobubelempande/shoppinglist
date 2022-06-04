package com.viiishoppinglistapp.doit;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    DatabaseHandler myDB;
    PieChart pieChart;
    ArrayList<String> inventory_item_id, inventory_item_name, inventory_item_category, inventory_item_quantity, inventory_item_price, inventory_item_expiry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        myDB =  new DatabaseHandler(StatisticsActivity.this);
        inventory_item_id = new ArrayList<>();
        inventory_item_name = new ArrayList<>();
        inventory_item_category = new ArrayList<>();
        inventory_item_quantity = new ArrayList<>();
        inventory_item_price = new ArrayList<>();
        inventory_item_expiry = new ArrayList<>();

        storeDataInArrays();
        double foodPrice =  0;
        double clothingPrice = 0;
        double appliancePrice = 0;
        double hygienePrice = 0;
        double stationeryPrice = 0;
        double toyPrice = 0;
        double otherPrice = 0;
        double price = 0;

        for(int j = 0; j < inventory_item_price.size(); j++){
            price += (Double.valueOf(inventory_item_price.get(j)) * Double.valueOf(inventory_item_quantity.get(j)));
        }

        for(int i = 0; i < inventory_item_price.size(); i++){
            if(inventory_item_category.get(i).equals("Food")){
                foodPrice += (((Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)))/price)*100);
            }
            else if(inventory_item_category.get(i).equals("Clothing")){
                clothingPrice += (((Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)))/price)*100);
            }
            else if(inventory_item_category.get(i).equals("Appliance")){
                appliancePrice += (((Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)))/price)*100);
            }
            else if(inventory_item_category.get(i).equals("Personal Hygiene")){
                hygienePrice += (((Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)))/price)*100);
            }
            else if(inventory_item_category.get(i).equals("Stationery")){
                stationeryPrice += (((Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)))/price)*100);
            }
            else if(inventory_item_category.get(i).equals("Toys And Games")){
                toyPrice += (((Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)))/price)*100);
            }
            else{
                otherPrice += (((Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)))/price)*100);
            }
        }


        pieChart = (PieChart)findViewById(R.id.PieChart);
        ArrayList<PieEntry> categories = new ArrayList<>();
        categories.add(new PieEntry((int)foodPrice, "Food"));
        categories.add(new PieEntry((int)clothingPrice, "Clothing"));
        categories.add(new PieEntry((int)appliancePrice, "App"));
        categories.add(new PieEntry((int)hygienePrice, "PH"));
        categories.add(new PieEntry((int)stationeryPrice, "Stationery"));
        categories.add(new PieEntry((int)toyPrice, "T&G"));
        categories.add(new PieEntry((int)otherPrice, "Other"));

        PieDataSet pieDataSet = new PieDataSet(categories, "Categories");
        ArrayList<Integer> colours = new ArrayList<>();
        colours.add(Color.MAGENTA);
        colours.add(Color.RED);
        colours.add(Color.BLUE);
        colours.add(Color.GREEN);
        colours.add(Color.CYAN);
        colours.add(Color.LTGRAY);
        colours.add(Color.YELLOW);

        pieDataSet.setColors(colours);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(18f);
        pieDataSet.setSliceSpace(4f);


        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Total Spent: " +price);
        pieChart.setCenterTextSize(15f);
        pieChart.getDescription().setEnabled(false);

        pieChart.animate();

    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readItemsData();
        while (cursor.moveToNext()){
            inventory_item_id.add(cursor.getString(0));
            inventory_item_name.add(cursor.getString(1));
            inventory_item_category.add(cursor.getString(3));
            inventory_item_quantity.add(cursor.getString(2));
            inventory_item_price.add(cursor.getString(5));
            inventory_item_expiry.add(cursor.getString(6));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Create a delete all options in the menu
        if(item.getItemId() == R.id.Setting){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        //Question user if they are sure about their choice and give them a negative and positive response option
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Settings");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(StatisticsActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
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



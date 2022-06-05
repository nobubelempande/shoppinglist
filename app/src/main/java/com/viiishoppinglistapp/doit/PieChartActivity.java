package com.viiishoppinglistapp.doit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    DatabaseHandler myDB;
    PieChart pieChart;
    ArrayList<String> inventory_item_id, inventory_item_name, inventory_item_category, inventory_item_quantity, inventory_item_price, inventory_item_expiry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        ImageView icon = findViewById(R.id.imgIcon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSettings();
            }

        });

        myDB =  new DatabaseHandler(PieChartActivity.this);
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
        if(foodPrice!= 0){
        categories.add(new PieEntry((int)foodPrice, "Food"));
        }
        if(clothingPrice!= 0){
        categories.add(new PieEntry((int)clothingPrice, "Clothing"));
        }
        if(appliancePrice!= 0){
        categories.add(new PieEntry((int)appliancePrice, "App"));
        }
        if(hygienePrice!= 0){
        categories.add(new PieEntry((int)hygienePrice, "PH"));
        }
        if(stationeryPrice!= 0){
        categories.add(new PieEntry((int)stationeryPrice, "Stationery"));
        }
        if(toyPrice!= 0) {
            categories.add(new PieEntry((int) toyPrice, "T&G"));
        }
        if(otherPrice!= 0) {
            categories.add(new PieEntry((int) otherPrice, "Other"));
        }
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
        pieChart.setCenterText("Total Spent: R" +price);
        pieChart.setCenterTextSize(15f);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setHoleRadius(75f);
        pieChart.setTransparentCircleRadius(33f);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        pieChart.animateY(1200);
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

    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "TestDay ShoppingList");
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



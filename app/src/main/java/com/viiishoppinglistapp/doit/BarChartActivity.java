package com.viiishoppinglistapp.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    DatabaseHandler myDB;
    BarChart barChart;
    ArrayList<String> inventory_item_id, inventory_item_name, inventory_item_category, inventory_item_quantity, inventory_item_price, inventory_item_expiry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        myDB =  new DatabaseHandler(BarChartActivity.this);
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
                foodPrice += (Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)));
            }
            else if(inventory_item_category.get(i).equals("Clothing")){
                clothingPrice += (Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)));
            }
            else if(inventory_item_category.get(i).equals("Appliance")){
                appliancePrice += (Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)));
            }
            else if(inventory_item_category.get(i).equals("Personal Hygiene")){
                hygienePrice += (Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)));
            }
            else if(inventory_item_category.get(i).equals("Stationery")){
                stationeryPrice += (Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)));
            }
            else if(inventory_item_category.get(i).equals("Toys And Games")){
                toyPrice += (Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)));
            }
            else{
                otherPrice += (Double.valueOf(inventory_item_price.get(i)) * Double.valueOf(inventory_item_quantity.get(i)));
            }
        }

        barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> categories = new ArrayList<>();
        categories.add(new BarEntry(1, (float)foodPrice));
        categories.add(new BarEntry(2, (int)clothingPrice));
        categories.add(new BarEntry(3, (int)appliancePrice));
        categories.add(new BarEntry(4, (int)hygienePrice));
        categories.add(new BarEntry(5, (int)stationeryPrice));
        categories.add(new BarEntry(6, (int)toyPrice));
        categories.add(new BarEntry(7, (int)otherPrice));

        final ArrayList<String> xAxisLabel = new ArrayList();
        xAxisLabel.add(" ");
        xAxisLabel.add("Food");
        xAxisLabel.add("Clothing");
        xAxisLabel.add("App");
        xAxisLabel.add("Hygiene");
        xAxisLabel.add("Stationery");
        xAxisLabel.add("T&G");
        xAxisLabel.add("Other");

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setDrawGridBackground(false);

        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);



        BarDataSet barDataSet = new BarDataSet(categories,"Categories");

        ArrayList<Integer> colours = new ArrayList<>();
        colours.add(Color.MAGENTA);
        colours.add(Color.RED);
        colours.add(Color.BLUE);
        colours.add(Color.GREEN);
        colours.add(Color.CYAN);
        colours.add(Color.LTGRAY);
        colours.add(Color.YELLOW);

        barDataSet.setColors(colours);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.animateY(2000);




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


}
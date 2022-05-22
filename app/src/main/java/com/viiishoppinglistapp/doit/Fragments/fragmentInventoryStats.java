package com.viiishoppinglistapp.doit.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedInventoryActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class fragmentInventoryStats extends Fragment {

    TabbedInventoryActivity activity;

    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;
    PieDataSet pieDataSet;
    PieData pieData;

    DatabaseHandler db;

    List<modelItem> allInventoryItems;

    final Context mContext;


    //---

    double price;
    ArrayList<PieEntry> categories;

    ArrayList<String> inventory_item_id, inventory_item_name, inventory_item_category, inventory_item_quantity, inventory_item_price, inventory_item_expiry;



    //constructor
    public fragmentInventoryStats(Context C, TabbedInventoryActivity activity){
        this.mContext = C;
        this.activity = activity;
        allInventoryItems = new ArrayList<modelItem>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_inventory_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doShowPieChart_ItemPrices();

        //doShowPieChart_Elements();
    }

    private void doShowPieChart_Elements() {
        pieChart = getActivity().findViewById(R.id.pieChart_inventoryStats);
        db =  new DatabaseHandler(mContext);

        loadPieChartData();
        setupPieChart();

        //--
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Total Spent: " +price);
        pieChart.setCenterTextSize(15f);
    }

    private void loadPieChartData() {
        //usingEntriesElements();
        usingEntries_TotalItemPrices();

        //makePieChartDataSet_Elements();
        makePieChartDataSet_TotalItemPrices();

        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }




    private void setupPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDescription(new Description());
        pieChart.setEntryLabelTextSize(12);
        pieChart.setHoleRadius(75f);
        pieChart.setTransparentCircleRadius(33f);
        pieChart.getDescription().setEnabled(false);

        Legend L = pieChart.getLegend();
        L.setEnabled(false);

        pieChart.animateY(1200);
        pieChart.animate();
    }

    private void usingEntriesElements() {
        pieEntries = new ArrayList<PieEntry>();
        pieEntries.add(new PieEntry(0.4f, "First Element"));
        pieEntries.add(new PieEntry(0.25f, "Second Element"));
        pieEntries.add(new PieEntry(0.25f, "Third Element"));
        pieEntries.add(new PieEntry(0.1f, "Forth Element"));
    }

    private void usingEntries_TotalItemPrices() {
        //toDo
        usingEntries_ItemPrices();
    }

    private void makePieChartDataSet_Elements() {
        pieDataSet = new PieDataSet(pieEntries, "Elements");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.DKGRAY);
        pieDataSet.setValueFormatter(new PercentFormatter(pieChart));
    }

    private void makePieChartDataSet_TotalItemPrices() {
        //ToDo
        pieDataSet = new PieDataSet(categories, "Categories");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.DKGRAY);
        pieDataSet.setValueFormatter(new PercentFormatter(pieChart));
    }





    //---

    private void doShowPieChart_ItemPrices() {
        price = 0;

        loadPieChartData_ItemPrices();

        setupPieChart_ItemPrices();
    }

    private void loadPieChartData_ItemPrices() {
        pieChart = getActivity().findViewById(R.id.pieChart_inventoryStats);
        db =  new DatabaseHandler(mContext);

        usingEntries_ItemPrices();

        makeDataSet();


        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void usingEntries_ItemPrices() {
        categories = new ArrayList<>();

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
        price = 0;

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


        categories.add(new PieEntry((int)foodPrice, "Food"));
        categories.add(new PieEntry((int)clothingPrice, "Clothing"));
        categories.add(new PieEntry((int)appliancePrice, "App"));
        categories.add(new PieEntry((int)hygienePrice, "PH"));
        categories.add(new PieEntry((int)stationeryPrice, "Stationery"));
        categories.add(new PieEntry((int)toyPrice, "T&G"));
        categories.add(new PieEntry((int)otherPrice, "Other"));
    }

    private void makeDataSet() {
        pieDataSet = new PieDataSet(categories, "Categories");
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
    }

    private void setupPieChart_ItemPrices() {

        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Total Spent: " +price);
        pieChart.setCenterTextSize(15f);
        pieChart.getDescription().setEnabled(false);

        pieChart.animateY(1200);
        pieChart.animate();
    }

    void storeDataInArrays(){
        Cursor cursor = db.readItemsData();
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

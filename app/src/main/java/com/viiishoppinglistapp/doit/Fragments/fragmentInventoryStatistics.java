package com.viiishoppinglistapp.doit.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class fragmentInventoryStatistics extends Fragment {

    private TabbedInventoryActivity activity;

    private PieChart pieChart;
    private ArrayList<PieEntry> pieEntries;
    private PieDataSet pieDataSet;

    private DatabaseHandler db;

    private List<modelItem> allInventoryItems;
    private double moneyUser;
    private double totalInventory;

    private final Context mContext;

    private final ArrayList<String> itemTypes = new ArrayList<>(Arrays.asList("Food", "Clothing", "Appliance", "Personal Hygiene", "Stationery", "Toys And Games", "Other"));



    //constructor
    public fragmentInventoryStatistics(Context C, TabbedInventoryActivity activity){
        this.mContext = C;
        this.activity = activity;
        allInventoryItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_inventory_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doShowPieChart_Elements();
    }

    private void doShowPieChart_Elements() {
        pieChart = Objects.requireNonNull(getActivity()).findViewById(R.id.pieChart_inventoryStats);
        db =  new DatabaseHandler(mContext);
        db.openDatabase();
        allInventoryItems = db.getAllInventoryItems();

        loadPieChartData();
        setupPieChart();

    }

    private void loadPieChartData() {
        usingInventory();

        makePieChartDataSet_Elements();

        PieData pieData = new PieData(pieDataSet);
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
        //L.setEnabled(false);
        L.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        pieChart.animateY(1200);
        pieChart.animate();
    }

    private void usingInventory(){
        pieEntries = new ArrayList<>();

        if(allInventoryItems.size()>0){
            entriesUsingInventory();
            pieChart.setCenterText("Spent\nR " + String.format("%,.2f", totalInventory) + "\nof\nR " + String.format("%,.2f", moneyUser));
            pieChart.setCenterTextSize(18);
            pieChart.setDrawEntryLabels(false);
        }
        else{
            //empty
            pieChart.setCenterText("No Items In Inventory.");
        }

    }

    private void entriesUsingInventory() {
        moneyUser = 0;
        totalInventory = 0;
        double section;
        double diff;
        modelItem currItem;

        for(int i = 0; i < allInventoryItems.size(); i++){
            currItem = allInventoryItems.get(i);
            totalInventory += currItem.getItemPrice();
        }

        int number = (int)totalInventory;
        int length = (int) (Math. log10(number) + 1);
        moneyUser = Math.pow(10,length);
        diff = moneyUser - totalInventory;

        int sizeInventory = allInventoryItems.size();
        int sizeTypes = itemTypes.size();

        for(int i = 0; i < sizeTypes; i++){
            double typeTotal = 0;
            String type = itemTypes.get(i);

            for(int j = 0; j < sizeInventory; j++){
                currItem = allInventoryItems.get(j);
                String itemType = currItem.getItemType();


                if(itemType.equals(type)){
                    typeTotal = typeTotal + currItem.getItemPrice();
                    //allInventoryItems.remove(j);
                }

            }

            if(typeTotal>0){
                section = typeTotal/moneyUser;
                pieEntries.add(new PieEntry((float) section, type));
            }

        }
        section = diff/moneyUser;
        pieEntries.add(new PieEntry((float) section, "Unused"));

        allInventoryItems = db.getAllInventoryItems();
    }

    private void makePieChartDataSet_Elements() {
        pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);     //toDo custom template
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.DKGRAY);
        pieDataSet.setValueFormatter(new PercentFormatter(pieChart));
    }


}

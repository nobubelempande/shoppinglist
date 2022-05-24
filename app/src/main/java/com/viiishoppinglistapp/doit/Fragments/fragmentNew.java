package com.viiishoppinglistapp.doit.Fragments;

import android.content.Context;
import android.database.Cursor;
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
import com.viiishoppinglistapp.doit.HomeActivity_old;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedInventoryActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class fragmentNew extends Fragment {

    TabbedInventoryActivity activity;

    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;
    PieDataSet pieDataSet;
    PieData pieData;

    DatabaseHandler db;

    List<modelItem> allInventoryItems;
    double moneyUser;
    double totalInventory;

    final Context mContext;


    //constructor
    public fragmentNew(Context C, TabbedInventoryActivity activity){
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

        doShowPieChart_Elements();
    }

    private void doShowPieChart_Elements() {
        pieChart = getActivity().findViewById(R.id.pieChart_inventoryStats);
        db =  new DatabaseHandler(mContext);
        allInventoryItems = db.getAllInventoryItems();

        Log.d(HomeActivity_old.TAG, "doShowPieChart_Elements: DONE RETURNING ITEMS");

        loadPieChartData();
        setupPieChart();

    }

    private void loadPieChartData() {
        usingInventory();       //usingEntriesElements();

        makePieChartDataSet_Elements();

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

    private void usingInventory(){
        pieEntries = new ArrayList<PieEntry>();

        if(allInventoryItems.size()>0){

        }
        else{
            //empty
            pieChart.setCenterText("No Items In Inventory.");
        }

    }

    private void makePieChartDataSet_Elements() {
        pieDataSet = new PieDataSet(pieEntries, "Elements");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);     //toDo custom template
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.DKGRAY);
        pieDataSet.setValueFormatter(new PercentFormatter(pieChart));
    }


}

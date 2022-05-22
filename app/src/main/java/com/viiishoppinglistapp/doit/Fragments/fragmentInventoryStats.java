package com.viiishoppinglistapp.doit.Fragments;

import android.content.Context;
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
import com.viiishoppinglistapp.doit.Adapters.InventoryItemsAdapter;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedInventoryActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class fragmentInventoryStats extends Fragment {

    TabbedInventoryActivity activity;
    InventoryItemsAdapter adapter;

    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;
    PieDataSet dataSet;

    DatabaseHandler db;

    List<modelItem> allInventoryItems;
    modelShoppingList currShoppingList;

    final Context mContext;


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

        pieChart = getActivity().findViewById(R.id.pieChart_inventoryStats);

        loadPieChartData();
        setupPieChart();
    }

    private void loadPieChartData() {
        usingElements();
        //toDo: usingTotalItemPrices();

        makePieChartDataSet_Elements();
        //toDo: makePieChartDataSet_TotalItemPrices();

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
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

    private void usingElements() {
        pieEntries = new ArrayList<PieEntry>();
        pieEntries.add(new PieEntry(0.4f, "First Element"));
        pieEntries.add(new PieEntry(0.25f, "Second Element"));
        pieEntries.add(new PieEntry(0.25f, "Third Element"));
        pieEntries.add(new PieEntry(0.1f, "Forth Element"));
    }

    private void usingTotalItemPrices() {
        //toDo
    }

    private void makePieChartDataSet_Elements() {
        dataSet = new PieDataSet(pieEntries, "Elements");
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        dataSet.setDrawValues(true);
        dataSet.setValueTextSize(12);
        dataSet.setValueTextColor(Color.DKGRAY);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));
    }

    private void makePieChartDataSet_TotalItemPrices() {
        //ToDo
    }

}

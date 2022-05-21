package com.viiishoppinglistapp.doit.Model;

import android.util.Log;

import com.viiishoppinglistapp.doit.HomeActivity_old;
import com.viiishoppinglistapp.doit.Utils.DateHandler;

public class modelItem {
    //attributes
    private int item_id;
    private int item_qty;
    private double item_price;
    private String item_doe;
    private String item_name;
    private String list_name;
    private String item_type;
    private boolean checked;

    //handlers
    DateHandler date;


    //constructor
    public modelItem(String name){
        date = new DateHandler();
        Log.d(HomeActivity_old.TAG, "Model Setup Start");
        setItemName(name);
        setItemQty(1);
        Log.d(HomeActivity_old.TAG, "Model Setup Mid 1");
        setChecked(0);
        Log.d(HomeActivity_old.TAG, "Model Setup Mid 2");
        setItemPrice(0.0);
        Log.d(HomeActivity_old.TAG, "Model Setup Mid 3");
        setItemDOE("N/A");
        Log.d(HomeActivity_old.TAG, "Model Setup Mid 3");

        Log.d(HomeActivity_old.TAG, "Model Setup End");
    }


    //getters
    public int getItemID() {
        return item_id;
    }
    public int getItemQty() {
        return item_qty;
    }
    public String getItemName() {
        return item_name;
    }
    public String getListName() {
        return list_name;
    }
    public String getItemType() {
        return item_type;
    }
    public double getItemPrice() {
        return item_price;
    }
    public String getItemDOE() {
        return item_doe;
    }
    public boolean isChecked() {
        return checked;
    }

    //setters
    public void setItemID(int item_id) {
        this.item_id = item_id;
    }
    public void setItemQty(int item_qty) {
        this.item_qty = item_qty;
    }
    public void setItemName(String item_name) {
        this.item_name = item_name;
    }
    public void setListName(String list_name) {
        this.list_name = list_name;
    }
    public void setItemType(String item_type) {
        this.item_type = item_type;
    }
    public void setItemPrice(double item_price) {
        this.item_price = item_price;
    }
    public void setItemDOE(String item_doe) {
        if(item_doe.equals(date.getNoDate())){
            this.item_doe = "N/A";
        }
        else{
            this.item_doe = item_doe;
        }
    }
    public void setChecked(int checked) {
        if(checked == 0){
            this.checked = false;
        }
        if(checked == 1){
            this.checked = true;
        }
    }

}

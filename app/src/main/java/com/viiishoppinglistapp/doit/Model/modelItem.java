package com.viiishoppinglistapp.doit.Model;

import com.viiishoppinglistapp.doit.Utils.DateHandler;

public class modelItem {
    //attributes
    private int item_id;
    private int item_qty;
    private double item_price;
    private String item_doe;
    private String item_name;
    private int list_id;
    private String item_type;
    private boolean checked;
    private int inventory_id;

    //handlers
    DateHandler date;



    //constructor
    public modelItem(String name){
        date = new DateHandler();
        setItemName(name);
        setItemQty(1);
        setChecked(0);
        setItemPrice(0.0);
        setItemDOE("N/A");
        setInventory_ID(0);
    }
    public modelItem(int ID){
        date = new DateHandler();
        setItemID(ID);
        setItemName("some Item");
        setItemQty(1);
        setChecked(0);
        setItemPrice(0.0);
        setItemDOE("N/A");
        setInventory_ID(0);
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
    public int getListID() {
        return list_id;
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
    public int getChecked() {
        if(this.checked){
            return 1;
        }
        return 0;
    }
    public int getInventory_ID() {
        return inventory_id;
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
    public void setShoppingListID(int list_id) {
        this.list_id = list_id;
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
    public void setInventory_ID(int inventory_id) {
        this.inventory_id = inventory_id;
    }
}

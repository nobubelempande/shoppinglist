package com.viiishoppinglistapp.doit.Model;

public class modelItem {
    private int item_id;
    private int item_qty;
    private double item_price;
    private String item_doe;
    private String item_name;
    private String list_name;
    private String item_type;
    private boolean checked;


    //constructor
    public modelItem(String name){
        setItemQty(1);
        setItemName(name);
        setChecked(0);
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
        this.item_doe = item_doe;
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

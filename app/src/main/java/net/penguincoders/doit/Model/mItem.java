package net.penguincoders.doit.Model;

public class mItem {
    private int item_id;
    private int item_qty;
    private String item_name;


    //constructor
    public mItem(String name){
        setItem_id(01);
        setItem_qty(1);
        setItem_name(name);
    }


    //getters
    public int getItem_id() {
        return item_id;
    }
    public int getItem_qty() {
        return item_qty;
    }
    public String getItem_name() {
        return item_name;
    }


    //setters
    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

}

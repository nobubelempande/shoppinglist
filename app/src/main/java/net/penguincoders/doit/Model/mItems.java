package net.penguincoders.doit.Model;

import java.util.ArrayList;

public class mItems {
    private int item_id;
    private int item_qty;
    private String item_name;
    private String category;

    public int getItem_id() {
        return item_id;
    }

    /*public mItems(){
        setItem_id(001);
        setItem_name("no name");
        setItem_qty(0);
        setCategory("food");
    }*/


    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

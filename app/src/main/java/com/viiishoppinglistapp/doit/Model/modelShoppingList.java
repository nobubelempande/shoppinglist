package com.viiishoppinglistapp.doit.Model;

import java.util.ArrayList;
import java.util.List;

public class modelShoppingList {
    private int list_id;
    private String list_name;
    private String list_useDate;
    private int list_used;
    private List<modelItem> list_items;
    //private int saved;  //unsaved = 0 ; saved = 1


    //constructor
    public modelShoppingList(String name){
        setListID(001);
        setListName(name);
        setListItems(new ArrayList<modelItem>());
        setToUnused();

    }

    public modelShoppingList(){
        setListID(001);
        setListName("no name");
        setListItems(new ArrayList<modelItem>());
        setToUnused();
    }



    //getters
    public String getListName() {
        return list_name;
    }
    public int getListID() {
        return list_id;
    }
    public int getUsed() {
        return list_used;
    }
    public String getUseDate() {
        return list_useDate;
    }
    public List<modelItem> getListItems() {
        return list_items;
    }


    //setters
    public void setListName(String list_name) {
        this.list_name = list_name;
    }
    public void setListID(int list_id) {
        this.list_id = list_id;
    }
    public void setListItems(List<modelItem> list_items) {
        this.list_items = list_items;
    }
    public void setUseDate(String list_useDate) {
        this.list_useDate = list_useDate;
    }
    public void setUsed(int list_used) {
        this.list_used = list_used;
    }
    public void setToUsed(){
        this.list_used = 1;
    }
    public void setToUnused(){
        this.list_used = 0;
    }
}

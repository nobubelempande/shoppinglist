package net.penguincoders.doit.Model;

import java.util.ArrayList;

public class mShoppingList {
    private int list_id;
    private String list_name;
    private ArrayList<mItem> list_items;
    //private int saved;  //unsaved = 0 ; saved = 1


    //constructor
    public mShoppingList(String name){
        setList_id(001);
        setList_name(name);
        setList_items(new ArrayList<mItem>());
    }

    public mShoppingList(){
        setList_id(001);
        setList_name("no name");
        setList_items(new ArrayList<mItem>());
    }



    //getters
    public String getList_name() {
        return list_name;
    }
    public int getList_id() {
        return list_id;
    }
    public ArrayList<mItem> getList_items() {
        return list_items;
    }


    //setters
    public void setList_name(String list_name) {
        this.list_name = list_name;
    }
    public void setList_id(int list_id) {
        this.list_id = list_id;
    }
    public void setList_items(ArrayList<mItem> list_items) {
        this.list_items = list_items;
    }

}

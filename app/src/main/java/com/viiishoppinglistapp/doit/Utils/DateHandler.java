package com.viiishoppinglistapp.doit.Utils;

import android.util.Log;

import com.viiishoppinglistapp.doit.HomeActivity_old;

public class DateHandler {
    private int day;
    private String month;
    private String numMonth;
    private int year;
    private String DATE;

    String no_date = "--/--/--";

    //constructors
    public DateHandler(int day, int month, int year){
        setDay(day);
        setNumMonth(String.valueOf(month));
        setMonth(month);
        setYear(year);
        setDATE(getDate());
    }
    public DateHandler(){
        setDATE(no_date);
    }


    //getters
    public int getDay() {
        return day;
    }
    public String getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public String getNumMonth() {
        return numMonth;
    }
    public String getDate(){
        return getDay() + "/" + getNumMonth() + "/" + getYear();
    }
    public String getNoDate() {
        setDATE(no_date);
        return DATE;
    }

    //setters
    public void setDay(int d) {
        this.day = d;
    }
    public void setMonth(int m) {
        String month = "";

        if(m==1){
            setNumMonth("01");
            month = "Jan";
        }
        if(m==2){
            setNumMonth("02");
            month = "Feb";
        }
        if(m==3){
            setNumMonth("03");
            month = "Mar";
        }
        if(m==4){
            setNumMonth("04");
            month = "Apr";
        }
        if(m==5){
            setNumMonth("05");
            month = "May";
        }
        if(m==6){
            setNumMonth("06");
            month = "Jun";
        }
        if(m==7){
            setNumMonth("07");
            month = "Jul";
        }
        if(m==8){
            setNumMonth("08");
            month = "Aug";
        }
        if(m==9){
            setNumMonth("09");
            month = "Sep";
        }
        if(m==10){
            month = "Oct";
        }
        if(m==11){
            month = "Nov";
        }
        if(m==12){
            month = "Dec";
        }


        this.month = month;
    }
    public void setYear(int yr) {
        this.year = yr;
    }
    public void setNumMonth(String numMonth) {
        this.numMonth = numMonth;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }
}

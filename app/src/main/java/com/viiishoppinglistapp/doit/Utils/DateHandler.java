package com.viiishoppinglistapp.doit.Utils;

public class DateHandler {
    private int day;
    private String month;
    private int year;

    public DateHandler(int day, int month, int year){
        setDay(day);
        setMonth(month);
        setYear(year);
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

    public String getDate(){
        return getDay() + " " + getMonth() + " " + getYear();
    }

    //setters
    public void setDay(int d) {
        this.day = d;
    }
    public void setMonth(int m) {
        String month = "";

        if(m==1){
            month = "Jan";
        }
        if(m==2){
            month = "Feb";
        }
        if(m==3){
            month = "Mar";
        }
        if(m==4){
            month = "Apr";
        }
        if(m==5){
            month = "May";
        }
        if(m==6){
            month = "Jun";
        }
        if(m==7){
            month = "Jul";
        }
        if(m==8){
            month = "Aug";
        }
        if(m==9){
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
}

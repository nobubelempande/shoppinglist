
    package com.viiishoppinglistapp.doit;

    public class ValidatorInt {

//this is an example test. Please create test for each activity. thank you
        Integer c;

        public ValidatorInt(Integer d){
            c = d;
        }

        public boolean isinputInteger(){
            if(c instanceof Integer){
                return true;
            }else{
                return false;
            }

        }

        public int add(){
            return 2;
        }
    }





    package net.penguincoders.doit;

    public class ValidatorInt {


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




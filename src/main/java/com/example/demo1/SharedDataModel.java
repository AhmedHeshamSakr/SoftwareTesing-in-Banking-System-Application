package com.example.demo1;


public  class SharedDataModel {
    private  static String myVariable;

    public static String getMyVariable() {
        return myVariable;
    }

    public static void setMyVariable( String myV) {
        myVariable = myV;
    }
}

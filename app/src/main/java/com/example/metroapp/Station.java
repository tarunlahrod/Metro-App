package com.example.metroapp;

public class Station {

    private String stationName;
    private int stationColorCode;

    public void setStationName(String name){
        this.stationName = name;
    }

    public void setStationColorCode(int colorCode){
        this.stationColorCode = colorCode;
    }

    public String getStationName(){
        return stationName;
    }

    public int getStationColorCode(){
        return stationColorCode;
    }

    // Parameterised constructor
    public Station(String name, int colorCode){
        this.stationName = name;
        this.stationColorCode = colorCode;
    }

    public String colorCodeToColorHex(int colorCode){
        String red = "";
        String blue = "";
        String green = "";
        String yellow = "";
        String pink = "";
        String purple = "";
        String magenta = "";

        String colorHex;

        switch(colorCode){
            case 1: colorHex = red; break;
            case 2: colorHex = blue; break;
            case 3: colorHex = green; break;
            case 4: colorHex = yellow; break;
            case 5: colorHex = pink; break;
            case 6: colorHex = purple; break;
            case 7: colorHex = magenta; break;
            default: colorHex = yellow;
        }
        return colorHex;
    }
}
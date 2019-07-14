package com.example.metroapp;

public class Station {

    private String stationName;
    private int stationColorCode;
    private int stationGraphNode;

    public void setStationName(String name){
        this.stationName = name;
    }

    public void setStationColorCode(int colorCode){
        this.stationColorCode = colorCode;
    }

    public void setStationGraphNode(int node) {
        this.stationGraphNode = node;
    }

    public String getStationName(){
        return stationName;
    }

    public int getStationColorCode(){
        return stationColorCode;
    }

    public int getStationGraphNode(){
        return stationGraphNode;
    }

    // default constructor
    public Station(){
        this.stationName = "null";
        this.stationColorCode = 0;
        this.stationGraphNode = 0;
    }

    // Parameterised constructor
    public Station(String name, int node, int colorCode){
        this.stationName = name;
        this.stationGraphNode = node;
        this.stationColorCode = colorCode;
    }

    //function to add station values explicitly
    public void addStationValues(String name, int node, int colorCode){
        this.stationName = name;
        this.stationGraphNode = node;
        this.stationColorCode = colorCode;
    }

    public String colorCodeToColorHex(int colorCode){
        String red = "#f00";
        String blue = "#00f";
        String green = "#0f0";
        String yellow = "#ffff00";
        String pink = "";
        String purple = "";
        String magenta = "";
        String black = "#000";

        String colorHex;

        switch(colorCode){
            case 1: colorHex = red; break;
            case 2: colorHex = blue; break;
            case 3: colorHex = green; break;
            case 4: colorHex = yellow; break;
            case 5: colorHex = pink; break;
            case 6: colorHex = purple; break;
            case 7: colorHex = magenta; break;
            default: colorHex = black;
        }
        return colorHex;
    }
}
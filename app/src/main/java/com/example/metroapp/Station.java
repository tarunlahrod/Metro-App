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

    public int colorCodeToImageResource(int colorCode){
        int red = R.drawable.delhi_metro_logo1;
        int blue = R.drawable.delhi_metro_logo2;
        int green = R.drawable.delhi_metro_logo3;
        int yellow = R.drawable.delhi_metro_logo4;
        int pink = R.drawable.delhi_metro_logo5;
        int purple = R.drawable.delhi_metro_logo6;
        int magenta = R.drawable.delhi_metro_logo7;
		int violet = R.drawable.delhi_metro_logo8;
		int orange = R.drawable.delhi_metro_logo9;
        int black = R.drawable.delhi_metro;

        int logoColor;

        switch(colorCode){
            case 1: logoColor = red; break;
            case 2: logoColor = blue; break;
            case 3: logoColor = green; break;
            case 4: logoColor = yellow; break;
            case 5: logoColor = pink; break;
            case 6: logoColor = purple; break;
            case 7: logoColor = magenta; break;
			case 8: logoColor = violet; break;
			case 9: logoColor = orange; break;
            default: logoColor = black;
        }
        return logoColor;
    }


}

package com.example.metroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText et_from, et_to;
    Button btn_route;
    ListView listView;

    ArrayList<Station> stationArrayList = new ArrayList<>();
    String[] stationNameList = new String[]{"Peeragari", "Paschim Vihar West", "Paschim Vihar East", "Madipur", "Shivaji Park"};
    int[] stationNodeList = new int[]{1, 2, 3, 4, 5};
    int[] stationColorCode = new int[]{3, 3, 3, 3, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et_from = findViewById(R.id.et_from);
        et_to = findViewById(R.id.et_to);
        btn_route = findViewById(R.id.btn_route);
        listView = findViewById(R.id.listView);

        // array of station
        Station[] stationArray = new Station[5];
        fillStationArray(stationArray);

        // adding stations to the stationArrayList
        fillStationArrayList(stationArray);

        btn_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = et_from.getText().toString();
                String to = et_to.getText().toString();

                if(from.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter boarding station", Toast.LENGTH_SHORT).show();
                    et_from.requestFocus();
                } else if(to.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter destination station", Toast.LENGTH_SHORT).show();
                    et_to.requestFocus();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Searching...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void fillStationArrayList(Station[] stationArray){
        
    }

    public void fillStationArray(Station[] stationArray){
        for (int i = 0; i < stationArray.length; i++){
            stationArray[i].addStation(stationNameList[i], stationNodeList[i], stationColorCode[i]);
        }
    }
}

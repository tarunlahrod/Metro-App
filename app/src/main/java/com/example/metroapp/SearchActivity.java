package com.example.metroapp;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText et_from, et_to;
    Button btn_route;
    ListView listView;

    ArrayList<Station> stationArrayList = new ArrayList<>(5);
    ArrayAdapter<String> adapterString;
    ArrayAdapter<Station> adapterStation;
    Station[] stationArray = new Station[5];


    String[] stationNameList = new String[]{"Peera Garhi", "Paschim Vihar West", "Paschim Vihar East", "Madipur", "Shivaji Park"};
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

        Station[] stationArray = new Station[5];
        for(int i = 0; i<5; i++){
            stationArray[i] = new Station(stationNameList[i], stationNodeList[i], stationColorCode[i]);
        }
//        fillStationArray();

        // adding stations to the stationArrayList
        fillStationArrayList(stationArray);

        // populating the list view of all the stations
        populateListView();

//        populateListViewRoute();

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
//                    populateListViewRoute();
                    Intent i = new Intent(getApplicationContext(), RouteActivity.class);
                    i.putExtra("nameList", stationNameList);
                    i.putExtra("nodeList", stationNodeList);
                    startActivity(i);
                }
            }
        });
    }



    // functions

    public void fillStationArrayList(Station stationArray[]){
        for(int i = 0; i < stationArray.length; i++){
            stationArrayList.add(stationArray[i]);
        }
    }

    public void fillStationArray(){
        for (int i = 0; i < stationArray.length; i++){
            stationArray[i].addStationValues(stationNameList[i], stationNodeList[i], stationColorCode[i]);
        }
    }

    public void populateListView(){
        adapterString = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stationNameList);
        listView.setAdapter(adapterString);
    }

    public void populateListViewRoute(){
        MyAdapter adapter = new MyAdapter(this, stationNameList, stationNodeList);
        listView.setAdapter(adapter);
    }



    // creating a custom array adapter class

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String stationName[];
        int stationNode[];

        MyAdapter (Context c, String name[], int node[]) {
            super(c, R.layout.station_row, R.id.tv_stationName, name);
            this.context = c;
            this.stationName = name;
            this.stationNode = node;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.station_row, parent, false);
            TextView sName = row.findViewById(R.id.tv_stationName);
            TextView sNode = row.findViewById(R.id.tv_stationNode);

            // now set our resources on views
            sName.setText(stationName[position]);
            sNode.setText("Node number " + stationNode[position]);




            return row;
        }
    }
}

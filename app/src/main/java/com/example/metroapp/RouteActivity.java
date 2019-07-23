package com.example.metroapp;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity {

    ListView listViewRoute;
    TextView tv_station, tv_cost, tv_time;
    Spinner spinner;
    MyAdapter adapter;


    String[] stationNameList = new String[]{"Netaji Subhash Place", "Shalimar Bagh", "Azadpur", "Model Town", "G.T.B. Nagar", "Vishwavidyalaya", "Vidhan Sabha", "Civil Lines", "Kashmere Gate", "Tis Hazari", "Pulbangash", "Pratap Nagar", "Shastri Nagar", "Inderlok", "Kanhaiya Nagar", "Keshav Puram", "Chandni Chowk", "Chawri Bazar", "New Delhi", "Rajiv Chowk", "RK Ashram Marg", "Jhandewalan", "Karol Bagh", "Rajendra Place", "Patel Nagar", "Shadipur", "Kirti Nagar", "Satguru Ram Singh Marg", "Ashok Park Main", "Moti Nagar", "Ramesh Nagar", "Rajouri Garden", "ESI - Basaidarapur", "Punjabi Bagh (W)", "Shakurpur"};
    int[] stationNodeList = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        listViewRoute = findViewById(R.id.listView_route);
        tv_station = findViewById(R.id.numStation);
        tv_cost = findViewById(R.id.cost);
        tv_time = findViewById(R.id.time);

        spinner = findViewById(R.id.spinner);
        int numArrayList;
        final ArrayList <ArrayList <Integer> > allRouteArrayList;


        //getting data from intent
        numArrayList  = getIntent().getIntExtra("noOfArrayLists", 3);
        allRouteArrayList = new ArrayList<>(numArrayList);
        Log.d("route", "all paths list declared with size 3: "+ allRouteArrayList);

        String[] routes = new String[numArrayList];

        for(int i = 0; i < numArrayList; i++){
            routes[i] = "Route " + (i+1);
            ArrayList<Integer> arrayList = getIntent().getIntegerArrayListExtra("pathArrayList"+i);
            tv_station.setText("" + arrayList.size());
            tv_time.setText("" + 2*arrayList.size() + " min");
            tv_cost.setText("Rs. 0");
            allRouteArrayList.add(arrayList);
        }

        Log.d("route", "all paths list after adding all routes: "+ allRouteArrayList);

        //setting the spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, routes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "position:" + position, Toast.LENGTH_SHORT).show();
                String stationNameArray[] = nodeToStation(allRouteArrayList.get(position), allRouteArrayList.get(position).size());
                int stationNodeArray[] = nodeArrayListToNodeArray(allRouteArrayList.get(position), allRouteArrayList.get(position).size());
                adapter = new MyAdapter(getApplicationContext(), stationNameArray, stationNodeArray);
                listViewRoute.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                tv_station.setText("" + stationNameArray.length);
                tv_cost.setText("Rs. 0");
                tv_time.setText("" + 2*stationNameArray.length);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




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

    public String[] nodeToStation(ArrayList<Integer> route, int length){
        String[] stationName = new String[length];
        for(int i = 0; i < length; i++){
            stationName[i] = stationNameList[route.get(i)];
        }
        return stationName;
    }

    public int[] nodeArrayListToNodeArray(ArrayList<Integer> route, int length){
        int[] stationNode = new int[length];
        for(int i=0; i<length; i++){
            stationNode[i] = route.get(i);
        }
        return stationNode;
    }

}

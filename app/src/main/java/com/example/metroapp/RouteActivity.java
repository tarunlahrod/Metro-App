package com.example.metroapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RouteActivity extends AppCompatActivity {

    ListView listViewRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        listViewRoute = findViewById(R.id.listView_route);

        String[] stationNameList = getIntent().getStringArrayExtra("nameList");
        int[] stationNodeList = getIntent().getIntArrayExtra("nodeList");

        MyAdapter adapter = new MyAdapter(this, stationNameList, stationNodeList);
        listViewRoute.setAdapter(adapter);
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

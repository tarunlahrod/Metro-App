package com.example.metroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class RouteActivity extends AppCompatActivity {

    ListView listViewRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        listViewRoute = findViewById(R.id.listView_route);
    }
}

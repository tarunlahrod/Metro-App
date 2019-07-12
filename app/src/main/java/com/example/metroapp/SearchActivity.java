package com.example.metroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    EditText et_from, et_to;
    Button btn_route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et_from = findViewById(R.id.et_from);
        et_to = findViewById(R.id.et_to);
        btn_route = findViewById(R.id.btn_route);

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
}

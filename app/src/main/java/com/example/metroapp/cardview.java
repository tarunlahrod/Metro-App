package com.example.metroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class cardview extends AppCompatActivity {
    ImageView card1_image,card2_image,card3_image;
    TextView card1_time,card2_time,card3_time,card1_cost,card2_cost,card3_cost;
    CardView card1,card2,card3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
//   Initializing card1 and its attributes
        card1=findViewById(R.id.card1);
        card1_cost=findViewById(R.id.card1_cost);
        card1_image=findViewById(R.id.card1_image);
        card1_time=findViewById(R.id.card1_time);
//   Initializing card2 and its attributes
        card2=findViewById(R.id.card2);
        card2_cost=findViewById(R.id.card2_cost);
        card2_image=findViewById(R.id.card2_image);
        card2_time=findViewById(R.id.card2_time);
//   Initializing card3 and its attributes
        card3=findViewById(R.id.card3);
        card3_cost=findViewById(R.id.card3_cost);
        card3_image=findViewById(R.id.card3_image);
        card3_time=findViewById(R.id.card3_time);
//   Setting the values
//        card1_time.setText();


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start a new activity with the route with max crowd and least time and cost
                Log.d("Cardview","You have clicked on First card");
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start a new activity with the route with less crowd and more time and cost
                Log.d("Cardview","You have clicked on Second card");
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start a new activity with the route with min crowd and respective time and cost
                Log.d("Cardview","You have clicked on Third card");
            }
        });

    }
}

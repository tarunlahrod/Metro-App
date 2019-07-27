package com.example.metroapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        textView = findViewById(R.id.text);

        openingAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // this code be called after delay
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
                finish();
            }
        }, 1500);
    }

    public void openingAnimation(){
        logo.animate().scaleXBy(0.5f).scaleYBy(0.5f).setDuration(2000);
        textView.animate().translationY(200).setDuration(2000);
    }
}

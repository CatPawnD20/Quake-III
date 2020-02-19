package com.ab.quake_iii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mapButton;
    private Button simulationButton;
    private Button earthquakeListButton;
    private Button optionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapButton = (Button) findViewById(R.id.mapButton);
        simulationButton = (Button) findViewById(R.id.simulationButton);
        earthquakeListButton = (Button) findViewById(R.id.earthquakeListButton);
        optionsButton = (Button) findViewById(R.id.optionsButton);
        mapButton.setOnClickListener(new mapScreen());
        simulationButton.setOnClickListener(new simulationScreen());
        earthquakeListButton.setOnClickListener(new earthquakeListScreen());
        optionsButton.setOnClickListener(new optionsScreen());

    }

    private class mapScreen implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(MainActivity.this,MapActivity.class));
        }
    }

    private class simulationScreen implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,SimulationActivity.class));
        }
    }

    private class earthquakeListScreen implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,EarthquakeListActivity.class));
        }
    }

    private class optionsScreen implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,OptionsActivity.class));
        }
    }
}

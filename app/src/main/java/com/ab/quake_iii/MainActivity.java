package com.ab.quake_iii;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mapButton;
    private Button simulationButton;
    private Button earthquakeListButton;
    private Button optionsButton;
    private static List<Ping> pingList;
    private static List<MarkerOptions> markerList;
    //private static WebListener webListener;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        MapsInitializer.initialize(getApplicationContext());

        Creator creator = new Creator();
        creator.yarat();

        //webListener = Creator.getObject("webListener");

        getDataFromWeb();

        mapButton = findViewById(R.id.mapButton);
        simulationButton = findViewById(R.id.simulationButton);
        earthquakeListButton = findViewById(R.id.earthquakeListButton);
        optionsButton = findViewById(R.id.optionsButton);

        mapButton.setOnClickListener(new mapScreen());
        simulationButton.setOnClickListener(new simulationScreen());
        earthquakeListButton.setOnClickListener(new earthquakeListScreen());
        optionsButton.setOnClickListener(new optionsScreen());

    }


    public static void getDataFromWeb() {
        WebListener webListener = (WebListener) new WebListener().execute();
        /*Veri çekme işi background'da olmalı ve çakışma olduğu için bir süre veri çekilmesi beklenmeli
          Başka çözümler bulunabilir. Fakat verinin tam gelmemesi, geç gelmesi gibi durumlara test ile
          çözümler üretilmeli */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webListener.getDataFromWeb();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class mapScreen implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, MapActivity.class);
            startActivity(i);
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

    public static List<Ping> getPingList() {
        return pingList;
    }

    public static List<MarkerOptions> getMarkerList() {
        return markerList;
    }

    public static void setPingList(List<Ping> pingList) {
        MainActivity.pingList = pingList;
    }

    public static void setMarkerList(List<MarkerOptions> markerList) {
        MainActivity.markerList = markerList;
    }

}

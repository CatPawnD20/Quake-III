package com.ab.quake_iii;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeListActivity extends AppCompatActivity {

    private static List<Ping> pingList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquakelist);
        pingList = MainActivity.getPingList();

        ListView list =(ListView) findViewById(R.id.locationListView);

        ArrayAdapter<Ping> adapter = new ArrayAdapter<Ping>
                (this, R.layout.configuration_listview, android.R.id.text1,pingList);

        list.setAdapter(adapter);

    }

    public List<String> makeList(List<Ping> pingList){
        List<String> temp = new ArrayList<>();
        for (Ping ping: pingList){
            temp.add(ping.getMagnitudeMD());
        }
        return temp;
    }

    public static List<Ping> getPingList() {
        return pingList;
    }

    public static void setPingList(List<Ping> pingList) {
        EarthquakeListActivity.pingList = pingList;
    }

}

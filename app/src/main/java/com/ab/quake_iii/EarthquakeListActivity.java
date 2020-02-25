package com.ab.quake_iii;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EarthquakeListActivity extends AppCompatActivity {

    private static List<Ping> pingList;
    String[] zaa = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquakelist);
        

        ListView list =(ListView) findViewById(R.id.earthquakeListListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,R.layout.configuration_listview,android.R.id.text1,zaa);

        list.setAdapter(adapter);

    }

    public static List<Ping> getPingList() {
        return pingList;
    }

    public static void setPingList(List<Ping> pingList) {
        EarthquakeListActivity.pingList = pingList;
    }

}

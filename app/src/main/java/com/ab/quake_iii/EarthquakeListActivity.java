package com.ab.quake_iii;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class EarthquakeListActivity extends AppCompatActivity {

    private String[] pingList = {
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",
            "18.02.2020   18:30:45        2.0         1.3 1.5 1.7   oludeniz aciklari - mugla (akdeniz)",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquakelist);


        ListView list =(ListView) findViewById(R.id.earthquakeListListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,R.layout.configuration_listview,android.R.id.text1,pingList);

        list.setAdapter(adapter);

    }
}

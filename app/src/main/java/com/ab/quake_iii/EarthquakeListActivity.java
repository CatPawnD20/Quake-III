package com.ab.quake_iii;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class EarthquakeListActivity extends AppCompatActivity {

    private String[] pingList = {
            "adana 3.5",
            "izmir 9.9"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquakelist);


        ListView list =(ListView) findViewById(R.id.earthquakeListListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,android.R.id.text1,pingList);

        list.setAdapter(adapter);

    }
}

package com.ab.quake_iii;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeListActivity extends AppCompatActivity {

    private static List<Ping> pingList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquakelist);
        pingList = MainActivity.getPingList();

        ListView pingListView =(ListView) findViewById(R.id.pingListView);
        PingListAdapter adapter = new PingListAdapter(this,R.layout.configuration_listview,pingList);
        pingListView.setAdapter(adapter);

    }

    public List<String> makeStringList(List<Ping> pingList){
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

    private class PingListAdapter extends ArrayAdapter<Ping> {
        Context context;
        int resource;
        public PingListAdapter(@NonNull Context context, int resource, List<Ping> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LocalDate date = getItem(position).getDate();
            LocalTime time = getItem(position).getTime();
            Double depth = getItem(position).getDepth();
            List<String> location = getItem(position).getLocation();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource,parent,false);

            TextView tvDate = (TextView) convertView.findViewById(R.id.dateView);
            TextView tvTime = (TextView) convertView.findViewById(R.id.timeView);
            TextView tvDepth = (TextView) convertView.findViewById(R.id.depthView);
            TextView tvLocation = (TextView) convertView.findViewById(R.id.locationView);

            tvDate.setText(String.valueOf(date));
            tvTime.setText(String.valueOf(time));
            tvDepth.setText(String.valueOf(depth));
            tvLocation.setText(String.valueOf(location));
            return convertView;
        }
    }
}



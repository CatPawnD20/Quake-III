package com.ab.quake_iii;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeListActivity extends AppCompatActivity {

    private static List<Ping> pingList;
    private RadioGroup radioGroupML;
    private RadioButton fromZeroRadio;
    private RadioButton fromOneRadio;
    private RadioButton fromTwoRadio;
    private RadioButton fromThreeRadio;
    private RadioButton fromFourRadio;
    private RadioButton fromFiveRadio;
    private RadioGroup radioGroupTime;
    private RadioButton allTimeRadio;
    private RadioButton oneDayRadio;
    private RadioButton twelveHoursRadio;
    private RadioButton sixHoursRadio;
    private RadioButton threeHoursRadio;
    private RadioButton oneHourRadio;
    private static double mlLimit = 0;
    private static int timeLimit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquakelist);
        pingList = MainActivity.getPingList();

        AndroidThreeTen.init(this);

        ListView pingListView =(ListView) findViewById(R.id.pingListView);
        PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, timeLimit,mlLimit));
        pingListView.setAdapter(adapter);

        radioGroupML = findViewById(R.id.radioGroupML);
        fromZeroRadio = findViewById(R.id.fromZeroRadio);
        fromOneRadio = findViewById(R.id.fromOneRadio);
        fromTwoRadio = findViewById(R.id.fromTwoRadio);

        fromFourRadio = findViewById(R.id.fromFourRadio);
        fromFiveRadio = findViewById(R.id.fromFiveRadio);


        radioGroupTime = findViewById(R.id.radioGroupTime);
        allTimeRadio = findViewById(R.id.allTimeRadio);
        oneDayRadio = findViewById(R.id.oneDayRadio);
        twelveHoursRadio = findViewById(R.id.twelveHoursRadio);
        sixHoursRadio = findViewById(R.id.sixHoursRadio);
        threeHoursRadio = findViewById(R.id.threeHoursRadio);
        oneHourRadio = findViewById(R.id.oneHourRadio);

        radioGroupML.setOnCheckedChangeListener(new RadioMLListener(pingListView));
        radioGroupTime.setOnCheckedChangeListener(new RadioTimeListener(pingListView));

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

        @SuppressLint("ResourceAsColor")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LocalDate date = getItem(position).getDate();
            LocalTime time = getItem(position).getTime();
            Double depth = getItem(position).getDepth();
            List<String> location = getItem(position).getLocation();
            Double magnitudeML = getItem(position).getMagnitudeML();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource,parent,false);

            TextView tvDate = (TextView) convertView.findViewById(R.id.dateView);
            TextView tvTime = (TextView) convertView.findViewById(R.id.timeView);
            TextView tvDepth = (TextView) convertView.findViewById(R.id.depthView);
            TextView tvLocation = (TextView) convertView.findViewById(R.id.locationView);
            TextView tvMagnitudeML = (TextView) convertView.findViewById(R.id.magnitudeMLView);

            tvDate.setText(String.valueOf(date));
            tvTime.setText(String.valueOf(time));
            tvDepth.setText("Derinlik :" + String.valueOf(depth) + "km");
            tvLocation.setText(String.valueOf(location));
            tvMagnitudeML.setText(String.valueOf(magnitudeML));
            tvMagnitudeML.setBackgroundResource(defineBackgroundColor(magnitudeML));
            return convertView;
        }
    }

    
    private static int defineBackgroundColor (Double ml){
        if (0<=ml && ml<1.0) return R.color.colorMLWhite;
        else if (1.0<=ml && ml<2.0) return R.color.colorMLLightBlue;
        else if (2.0<=ml && ml<3.0) return R.color.colorMLGreen;
        else if (3.0<=ml && ml<4.0) return R.color.colorMLOrange;
        else if (4.0<=ml && ml<5.0) return R.color.colorMLRed;
        else return R.color.colorMLDarkGrey;

    }

    private class RadioMLListener implements RadioGroup.OnCheckedChangeListener {
        ListView pingListView;

        public RadioMLListener(ListView pingListView) {
            this.pingListView = pingListView;
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.fromZeroRadio){
                mlLimit = 0;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, mlLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.fromOneRadio){
                mlLimit = 1;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, mlLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.fromTwoRadio){
                mlLimit = 2;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, mlLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.fromThreeRadio){
                mlLimit = 3;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, mlLimit));
                pingListView.setAdapter(adapter);

            }
            if(checkedId == R.id.fromFourRadio){
                mlLimit = 4;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, mlLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.fromFiveRadio){
                mlLimit = 5;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, mlLimit));
                pingListView.setAdapter(adapter);

            }

        }
    }

    private class RadioTimeListener implements RadioGroup.OnCheckedChangeListener {
        ListView pingListView;


        public RadioTimeListener(ListView pingListView) {
            this.pingListView = pingListView;

        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.allTimeRadio){
                timeLimit = 5;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, timeLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.oneDayRadio){
                timeLimit = 1;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, timeLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.twelveHoursRadio){
                timeLimit = 12;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, timeLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.sixHoursRadio){
                timeLimit = 6;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, timeLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.threeHoursRadio){
                timeLimit = 3;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, timeLimit));
                pingListView.setAdapter(adapter);

            }
            else if(checkedId == R.id.oneHourRadio){
                timeLimit = 10;
                PingListAdapter adapter = new PingListAdapter(EarthquakeListActivity.this,R.layout.configuration_listview,makeSelectedList(pingList, timeLimit));
                pingListView.setAdapter(adapter);

            }
        }
    }

    private List<Ping> makeSelectedList(List<Ping> pingList,double mlLimit){
        List<Ping> temp = new ArrayList<>();
            for (Ping p:pingList) {
                if(p.getMagnitudeML()>mlLimit){
                    temp.add(p);
                }
            }
            return temp;

    }

    private List<Ping> makeSelectedList(List<Ping> pingList,int timeLimit,double mlLimit){
        List<Ping> temp = new ArrayList<>();
        if (timeLimit==5){
            for (Ping p:pingList) {
                if(p.getMagnitudeML()>mlLimit){
                    temp.add(p);
                }
            }
            return temp;
        }else if(timeLimit == 1){
            LocalDate localDateD = LocalDate.now().minusDays(timeLimit);
            LocalTime localTimeD = LocalTime.now();
            for(Ping p : pingList){
                if(p.getDate().isAfter(localDateD) ||
                        (p.getDate().isEqual(localDateD) && p.getTime().isAfter(localTimeD))){
                    temp.add(p);
                }
            }
            return temp;
        }
        else{
            return pingList;
        }
    }
}



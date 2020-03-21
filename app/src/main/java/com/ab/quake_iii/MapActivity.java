package com.ab.quake_iii;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gMap;
    private RadioGroup radioGroupDepth;
    private RadioGroup radioGroupTime;
    private Button satelliteMapButton;
    private Button terrainMapButton;
    private Button normalMapButton;
    private Button mapStyleMenu;

    private List<Ping> pingList;
    private List<Ping> tempPingList;

    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyARLi5lVDsohtSSY2d0pCBCDIMlnl3K_Kg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        AndroidThreeTen.init(this);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            //Bundle'da Key'e karşılık mapViewBundle tutuluyor.Gereksiz, key göstermek yerine başka kelime konabilir
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);

        radioGroupDepth = findViewById(R.id.radioGroupDepth);
        radioGroupTime = findViewById(R.id.radioGroupTime);

        radioGroupDepth.setOnCheckedChangeListener(new RadioDepthListener());
        radioGroupTime.setOnCheckedChangeListener(new RadioTimeListener());

        mapStyleMenu = findViewById(R.id.mapStyleMenu);
        mapStyleMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(normalMapButton.getVisibility() == View.INVISIBLE){
                    normalMapButton.setVisibility(View.VISIBLE);
                    terrainMapButton.setVisibility(View.VISIBLE);
                    satelliteMapButton.setVisibility(View.VISIBLE);
                    mapStyleMenu.setBackgroundResource(R.drawable.cancel);
                }else{
                    normalMapButton.setVisibility(View.INVISIBLE);
                    terrainMapButton.setVisibility(View.INVISIBLE);
                    satelliteMapButton.setVisibility(View.INVISIBLE);
                    mapStyleMenu.setBackgroundResource(R.drawable.settings);
                }
            }
        });

        normalMapButton = findViewById(R.id.normalButton);
        normalMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        terrainMapButton = findViewById(R.id.terrainButton);
        terrainMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        satelliteMapButton = findViewById(R.id.satelliteButton);
        satelliteMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        pingList = MainActivity.getPingList();

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mapView.onResume()/onDestroy() gibi tüm lifecycle için ilgili method vardı ben sildim onları, bir daha düşün
        mapView.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(MapActivity.this, MainActivity.class));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.getUiSettings().setMapToolbarEnabled(false);
        gMap.setInfoWindowAdapter(new InfoWindowConfigure(MapActivity.this));
        addMarkerToMapWithDepth(3.0);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(38,34), 5));
    }

    private class InfoWindowConfigure implements GoogleMap.InfoWindowAdapter{

        private final View mWindow;
        private Context context;

        public InfoWindowConfigure(Context context) {
            this.context = context;
            this.mWindow = LayoutInflater.from(context).inflate(R.layout.configuration_mapinfo, null);
        }

        private void fillWindowText(Marker marker, View view){
            String[] title = marker.getTitle().split("/");
            String[] snippet = marker.getSnippet().split("/");

            TextView tvLocation = view.findViewById(R.id.locationTitle);
            tvLocation.setText(title[0]);

            TextView tvMagnitude = view.findViewById(R.id.magnitudeTitle);
            tvMagnitude.setText(title[1]);

            TextView tvDepth = view.findViewById(R.id.depthTitle);
            tvDepth.setText(snippet[0]);

            TextView tvDate = view.findViewById(R.id.dateTitle);
            tvDate.setText(snippet[1]);

            TextView tvTime = view.findViewById(R.id.timeTitle);
            tvTime.setText(snippet[2]);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            fillWindowText(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            fillWindowText(marker, mWindow);
            return mWindow;
        }
    }

    private class RadioDepthListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            if(i == R.id.fromOneRadio){
                addMarkerToMapWithDepth(1.0);
            }else if( i == R.id.fromTwoRadio){
                addMarkerToMapWithDepth(2.0);
            }else if( i == R.id.fromThreeRadio){
                addMarkerToMapWithDepth(3.0);
            }else if( i == R.id.fromFourRadio){
                addMarkerToMapWithDepth(4.0);
            }else if( i == R.id.fromFiveRadio){
                addMarkerToMapWithDepth(5.0);
            }else if( i == R.id.fromSixRadio){
                addMarkerToMapWithDepth(6.0);
            }else if( i == R.id.fromZeroRadio){
                addMarkerToMapWithDepth(0.0);
            }
        }
    }

    private void addMarkerToMapWithDepth(Double filterNum){
        tempPingList = new ArrayList<>();
        for(Ping p: pingList){
            if(p.getMagnitudeML() >= filterNum){
                tempPingList.add(p);
            }
        }
        new RadioTimeListener().onCheckedChanged(radioGroupTime, radioGroupTime.getCheckedRadioButtonId());
    }

    private class RadioTimeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.allTimeRadio){
                addMarkerToMapWithTime("D", 5);
            }else if( i == R.id.twoDaysRadio){
                addMarkerToMapWithTime("D",2);
            }else if( i == R.id.oneDayRadio){
                addMarkerToMapWithTime("D", 1);
            }else if( i == R.id.twelveHoursRadio){
                addMarkerToMapWithTime("T", 12);
            }else if( i == R.id.sixHoursRadio){
                addMarkerToMapWithTime("T",6);
            }else if( i == R.id.threeHoursRadio){
                addMarkerToMapWithTime("T",3);
            }else if( i == R.id.oneHourRadio){
                addMarkerToMapWithTime("T",1);
            }
        }
    }

    private void addMarkerToMapWithTime(String type, int timeOrDay){
        gMap.clear();
        if(type.equals("D")){
            LocalDate localDateD = LocalDate.now().minusDays(timeOrDay);
            LocalTime localTimeD = LocalTime.now();
            for(Ping p: tempPingList) {
                if(p.getDate().isAfter(localDateD) ||
                        (p.getDate().isEqual(localDateD) && p.getTime().isAfter(localTimeD))){
                    gMap.addMarker(p.getMarkerOptions());
                }
            }
        }else{
            LocalDate localDateT1 = LocalDate.now();
            LocalDate localDateT2 = LocalDate.of(1970,02,20);
            LocalTime localTimeNow = LocalTime.now();
            LocalTime localTimeT = localTimeNow.minusHours(timeOrDay);
            if(localTimeNow.getHour()<timeOrDay){
                localDateT2 = localDateT1;
                localDateT1 = localDateT1.minusDays(1);
            }
            for(Ping p: tempPingList){
                if((p.getTime().isAfter(localTimeT) && p.getDate().isEqual(localDateT1)) ||
                        (p.getDate().isEqual(localDateT2))){
                    gMap.addMarker(p.getMarkerOptions());
                }
            }
        }
    }
}

package com.ab.quake_iii;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gMap;
    private RadioButton fromOneRadio;
    private RadioButton fromTwoRadio;
    private RadioButton fromThreeRadio;
    private RadioButton fromFourRadio;
    private RadioButton fromFiveRadio;
    private RadioButton fromSixRadio;
    private RadioButton fromSevenRadio;
    private RadioGroup radioGroup;

    private List<Ping> pingList;

    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyARLi5lVDsohtSSY2d0pCBCDIMlnl3K_Kg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            //Bundle'da Key'e karşılık mapViewBundle tutuluyor.Gereksiz, key göstermek yerine başka kelime konabilir
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        fromOneRadio = findViewById(R.id.fromOneRadio);
        fromTwoRadio = findViewById(R.id.fromTwoRadio);
        fromThreeRadio = findViewById(R.id.fromThreeRadio);
        fromFourRadio = findViewById(R.id.fromFourRadio);
        fromFiveRadio = findViewById(R.id.fromFiveRadio);
        fromSixRadio = findViewById(R.id.fromSixRadio);
        fromSevenRadio = findViewById(R.id.fromSevenRadio);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioListener());

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
        fromThreeRadio.setChecked(true);
        addMarkerToMap(3.0);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(38,34), 5));
    }

    private void addMarkerToMap(Double filterNum){
        gMap.clear();
        for(Ping p: pingList){
            if(p.getMagnitudeML() > filterNum){
                p.getMarkerOptions().visible(true);
                gMap.addMarker(p.getMarkerOptions());
            }else{
                p.getMarkerOptions().visible(false);
            }
        }
    }

    private class RadioListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.fromOneRadio){
                addMarkerToMap(1.0);
            }else if( i == R.id.fromTwoRadio){
                addMarkerToMap(2.0);
            }else if( i == R.id.fromThreeRadio){
                addMarkerToMap(3.0);
            }else if( i == R.id.fromFourRadio){
                addMarkerToMap(4.0);
            }else if( i == R.id.fromFiveRadio){
                addMarkerToMap(5.0);
            }else if( i == R.id.fromSixRadio){
                addMarkerToMap(6.0);
            }else if( i == R.id.fromSevenRadio){
                addMarkerToMap(7.0);
            }
        }
    }
}

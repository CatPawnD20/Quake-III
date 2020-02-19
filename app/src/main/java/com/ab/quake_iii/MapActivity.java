package com.ab.quake_iii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private Button refreshButton;
    private GoogleMap gMap;

    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyARLi5lVDsohtSSY2d0pCBCDIMlnl3K_Kg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = (MapView) findViewById(R.id.mapView);
        refreshButton = (Button) findViewById(R.id.refreshButton);

        refreshButton.setOnClickListener(new ButtonListener());

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


    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //test etmek için kondu değişecek, sadece haritanın yenilenmesi gibi bir seçenek de olabilir
            //Bunun için haritaya tekrardan bundle vermen gerekebilir.Ama hangisi bilmiyorum?
            //mapView.onStop();
            //mapView.onCreate(savedInstanceState);
            //Şimdilik komple yeniliyor
            startActivity(new Intent(MapActivity.this, MapActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume(); //mapView.onResume() tüm lifecycle için vardı ben sildim onları, bir daha düşün
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(38,34), 5));
    }
}

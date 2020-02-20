package com.ab.quake_iii;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private MarkerCreator markerCreator;
    private List<Ping> pingList = new ArrayList<>();
    private List<MarkerOptions> markerList = new ArrayList<>();

    public List<Ping> getPingList() {
        return pingList;
    }

    //Set fonksiyonunu karıştırdım biraz bunu düzenle. sadece pingList eşitlesin
    public void setPingList(List<Ping> pingList) {
        this.pingList = pingList;
        markerCreator = Creator.getObject("markerCreator");
        MainActivity.setPingList(pingList);
        markerCreator.createMarkers();
    }

    public List<MarkerOptions> getMarkerList() {
        return markerList;
    }

    //Set fonksiyonunu karıştırdım biraz bunu düzenle. sadece markerList eşitlesin
    public void setMarkerList(List<MarkerOptions> markerList) {
        this.markerList = markerList;
        MainActivity.setMarkerList(markerList);
    }

}

package com.ab.quake_iii;

import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class Container {

    private MarkerCreator markerCreator;
    private List<Ping> pingList = new ArrayList<>();
    private List<MarkerOptions> markerList = new ArrayList<>();

    private boolean isService = false;

    public void setPingList(List<Ping> pingList) {
        this.pingList = pingList;
        if(!isService){
            MainActivity.setPingList(pingList);
            markerCreator = Creator.getObject("markerCreator");
            markerCreator.createMarkers();
        }
    }

    public List<MarkerOptions> getMarkerList() {
        return markerList;
    }

    public List<Ping> getPingList() {
        return pingList;
    }

    //Set fonksiyonunu karıştırdım biraz bunu düzenle. sadece markerList eşitlesin
    public void setMarkerList(List<MarkerOptions> markerList) {
        this.markerList = markerList;
        MainActivity.setMarkerList(markerList);
    }

    public void setService(boolean service) {
        isService = service;
    }
}

package com.ab.quake_iii;

import com.google.android.gms.maps.model.LatLng;

public class Ping {

    LatLng point;

    public Ping(double x, double y){
        point = new LatLng(x,y);
    }

    public LatLng getPoint() {
        return point;
    }
}

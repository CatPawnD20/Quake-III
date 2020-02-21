package com.ab.quake_iii;

import com.google.android.gms.maps.model.LatLng;

public class Ping {

    private final String[] location;
    private final double depth;
    private LatLng point;
    private double[] magnitude;


    public Ping(double x, double y, double[] magnitude, double depth, String[] location){
        this.location = location;
        this.depth = depth;
        this.magnitude = magnitude;
        point = new LatLng(x,y);
    }

    public LatLng getPoint() {
        return point;
    }
}

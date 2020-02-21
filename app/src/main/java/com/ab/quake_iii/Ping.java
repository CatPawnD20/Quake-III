package com.ab.quake_iii;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class Ping {

    private final String location;
    private final double depth;
    private LatLng point;
    private double magnitude;
    private final Date date;
    private final Date time;
    private final double latitude;
    private final double longtidue;

    public Ping(Date date, Date time, double latitude, double longtidue, double depth, double magnitude, String location){
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longtidue = longtidue;
        this.location = location;
        this.depth = depth;
        this.magnitude = magnitude;
        point = new LatLng(latitude,longtidue);
    }

    public LatLng getPoint() {
        return point;
    }

    public double getLongtidue() {
        return longtidue;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLocation() {
        return location;
    }

    public double getDepth() {
        return depth;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }
}

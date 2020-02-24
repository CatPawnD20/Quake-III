package com.ab.quake_iii;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.List;

public class Ping {

    private final List<String> location;
    private final double depth;
    private String magnitudeMD;
    private double magnitudeML;
    private String magnitudeMW;
    private final Date date;
    private final Date time;
    private final double latitude;
    private final double longtidue;
    private LatLng point;
    private MarkerOptions markerOptions;

    public Ping(Date date, Date time, double latitude, double longtidue, double depth, double magnitudeML, List<String> location){
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longtidue = longtidue;
        this.location = location;
        this.depth = depth;
        this.magnitudeML = magnitudeML;
        point = new LatLng(latitude,longtidue);

    }
    public Ping (Date date, Date time, double latitude, double longtidue, double depth, String magnitudeMD, double magnitudeML, String magnitudeMW, List<String> location){
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longtidue = longtidue;
        this.location = location;
        this.depth = depth;
        this.magnitudeMD = magnitudeMD;
        this.magnitudeML = magnitudeML;
        this.magnitudeMW = magnitudeMW;
        point = new LatLng(latitude,longtidue);
    }
    public LatLng getPoint() {
        return point;
    }

    public String getMagnitudeMD() {
        return magnitudeMD;
    }

    public String getMagnitudeMW() {
        return magnitudeMW;
    }

    public double getLongtidue() {
        return longtidue;
    }

    public double getLatitude() {
        return latitude;
    }

    public List<String> getLocation() {
        return location;
    }

    public double getDepth() {
        return depth;
    }

    public double getMagnitudeML() {
        return magnitudeML;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }
}


package com.ab.quake_iii;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*import java.time.LocalDate;
import java.time.LocalTime;*/
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import java.util.List;

public class Ping {

    private final List<String> location;
    private final double depth;
    private String magnitudeMD;
    private double magnitudeML;
    private String magnitudeMW;
    private final LocalDate date;
    private final LocalTime time;
    private final double latitude;
    private final double longtidue;
    private LatLng point;
    private MarkerOptions markerOptions;

    public Ping (LocalDate date, LocalTime time, double latitude, double longtidue, double depth, String magnitudeMD, double magnitudeML, String magnitudeMW, List<String> location){
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

    //Geçici Çözüm
//    @Override
//    public String toString(){
//        return dateFormat.format(date) +
//                " " + timeFormat.format(time) +
//                "                " + String.format(String.valueOf(depth)) +
//                "          " + magnitudeMD +
//                " " + magnitudeML +
//                " " + magnitudeMW +
//                "      " + settings;
//    }

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

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }
}


package com.ab.quake_iii;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MarkerCreator {

    private List<MarkerOptions> markerList = new ArrayList<>();
    private Container container;
    private List<Ping> pingList;

    public void createMarkers(){
        container = Creator.getObject("container");
        pingList = container.getPingList();
        fillMarkerList();
        container.setMarkerList(markerList);
    }

    //PingList ile gelen Ping verileri MarkerOptions tipine çevrilir.
    private void fillMarkerList(){
        for(Ping p : pingList){
            //Pop-up ve verilen bilgiler düzenlenecek
            String title = p.getLocationProperString() + "/Magnitude: " + p.getMagnitudeML();
            MarkerOptions markerOptions = new MarkerOptions().position(p.getPoint()).title(title)
                    .snippet("Depth: " + p.getDepth() + "/Date: " + p.getDate() + "/Time: " + p.getTime())
                    .icon(bitmapDescriptorFromVector (MainActivity.context, R.drawable.dot));
            p.setMarkerOptions(markerOptions);
            markerList.add(markerOptions);
        }
    }

    //Custom oluşturulan marker görselinin ayarlanması
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}

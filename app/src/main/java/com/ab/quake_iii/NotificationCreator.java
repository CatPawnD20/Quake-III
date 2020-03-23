package com.ab.quake_iii;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;

public class NotificationCreator extends Application{

    public static final String CHANNEL_ID = "EarthQuakeNotification";
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        LocalTime localTime = LocalTime.now();
        NotificationJobService.localTime = localTime;
        createNotificationChannel();

        sharedPref = this.getSharedPreferences("Quake3", Context.MODE_PRIVATE);

    }

    public void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "EarthQuakeNotification Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is notification channel for EarthQuake.");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public Notification createNotification(PendingIntent pendingIntent, NotificationJobService notificationJobService, Ping lastPing) {
        Notification notification = new NotificationCompat.Builder(notificationJobService, CHANNEL_ID)
                .setContentTitle("Location: " + lastPing.getLocationProperString())
                .setContentText("Magnitude: " + lastPing.getMagnitudeML() + " Date: " + lastPing.getDate() + " Time: " + lastPing.getTime())
                .setSmallIcon(R.drawable.normal_map)
                .setContentIntent(pendingIntent)
                .build();
        return notification;
    }
}

package com.ab.quake_iii;

import android.app.Notification;
import android.app.PendingIntent;

import androidx.core.app.NotificationCompat;

public class NotificationCreator {


    public Notification createNotification(PendingIntent pendingIntent, ExampleJobService exampleJobService, Ping lastPing) {
        Notification notification = new NotificationCompat.Builder(exampleJobService, App.CHANNEL_ID)
                .setContentTitle(lastPing.getLocation().toString())
                .setContentText(lastPing.getMagnitudeML() + " " + lastPing.getDate() + " " + lastPing.getTime())
                .setSmallIcon(R.drawable.normal_map)
                .setContentIntent(pendingIntent)
                .build();
        return notification;
    }
}

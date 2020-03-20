package com.ab.quake_iii;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class NotificationCreator extends Application{

    public static final String CHANNEL_ID = "NotificationCreator";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    public void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notification Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is notification channel for earthQuake.");
            Toast.makeText(getApplicationContext(), "channel", Toast.LENGTH_SHORT).show();
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public Notification createNotification(PendingIntent pendingIntent, ExampleJobService exampleJobService, Ping lastPing) {
        Notification notification = new NotificationCompat.Builder(exampleJobService, CHANNEL_ID)
                .setContentTitle(lastPing.getLocation().toString())
                .setContentText(lastPing.getMagnitudeML() + " " + lastPing.getDate() + " " + lastPing.getTime())
                .setSmallIcon(R.drawable.normal_map)
                .setContentIntent(pendingIntent)
                .build();
        return notification;
    }
}

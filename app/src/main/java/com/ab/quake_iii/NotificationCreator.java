package com.ab.quake_iii;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraMailSender;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.MailSenderConfigurationBuilder;
import org.acra.data.StringFormat;
import org.threeten.bp.LocalTime;

@AcraMailSender(mailTo = "bariskaan4@gmail.com")
@AcraCore(buildConfigClass = BuildConfig.class)
public class NotificationCreator extends Application{

    public static final String CHANNEL_ID = "EarthQuakeNotification";
    private static final String TAG = "NotificationCreator";
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor editor;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this)
                .setBuildConfigClass(BuildConfig.class)
                .setReportFormat(StringFormat.JSON);
        builder.getPluginConfigurationBuilder(MailSenderConfigurationBuilder.class)
                .setMailTo("bariskaan4@gmail.com")
                .setReportAsFile(true);
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        LocalTime localTime = LocalTime.now();
        NotificationJobService.localTime = localTime;
        createNotificationChannel();

        Log.i(TAG, "KendimeLog: NotificationCreator çalıştı");

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
        Log.i(TAG, "KendimeLog: Bildirim gönderildi.");
        Notification notification = new NotificationCompat.Builder(notificationJobService, CHANNEL_ID)
                .setContentTitle("Location: " + lastPing.getLocationProperString())
                .setContentText("Magnitude: " + lastPing.getMagnitudeML() + " Date: " + lastPing.getDate() + " Time: " + lastPing.getTime())
                .setSmallIcon(R.drawable.normal_map)
                .setContentIntent(pendingIntent)
                .build();
        return notification;
    }
}

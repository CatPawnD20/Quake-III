package com.ab.quake_iii;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import org.threeten.bp.LocalTime;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;
    private Container container;
    public static LocalTime localTime;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Schedule Job Started.");
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(jobCancelled){
                    return;
                }else{
                    Creator creator = new Creator();
                    creator.create();

                    //WebListener webListener = (WebListener) new WebListener().execute();
                    WebListener webListener = new WebListener();
                    webListener.getData();
                    webListener.getDataFromWeb();

                    container = Creator.getObject("container");

                    Ping lastPing = container.getPingList().get(0);
                    if(lastPing.getTime().isAfter(localTime.minusMinutes(30))){
                        startNotify(lastPing);
                    }
                    jobFinished(params, false);
                }
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Schedule Job Stopped.");
        jobCancelled = true;
        return true;
    }

    public int startNotify(Ping lastPing) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,0 );

        NotificationCreator nc = new NotificationCreator();
        Notification notification = nc.createNotification(pendingIntent, this, lastPing);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);

        return START_NOT_STICKY;
    }
}

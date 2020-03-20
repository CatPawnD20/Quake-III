package com.ab.quake_iii;

import android.app.Notification;
import android.app.PendingIntent;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

import org.threeten.bp.LocalTime;

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    public static LocalTime localTime;
    BackgroundTask backgroundTask;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Schedule Job Started.");
        backgroundTask = new BackgroundTask(params);

        backgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Schedule Job Stopped.");
        return true;
    }

    public int startNotify(Ping lastPing) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,0 );

        NotificationCreator nc = new NotificationCreator();
        Notification notification = nc.createNotification(pendingIntent, this, lastPing);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(1377,notification);

        return START_NOT_STICKY;
    }

    public class BackgroundTask extends AsyncTask<Void, Void, Void>{
        private Container container;
        private JobParameters params;
        private WebListener webListener;

        public BackgroundTask(JobParameters params) {
            this.params = params;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Creator creator = new Creator();
            creator.create();
            container = Creator.getObject("container");
            container.setService(true);
            webListener = new WebListener();
            webListener.getData();

            webListener.getDataFromWeb();

            Ping lastPing = container.getPingList().get(0);

            if(lastPing.getTime().isAfter(localTime.minusMinutes(30))){
                startNotify(lastPing);
            }

            jobFinished(params, false);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}

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

import java.util.ArrayList;
import java.util.List;

public class NotificationJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    public static LocalTime localTime;
    private BackgroundTask backgroundTask;
    private int selectedMagnitude;
    private String selectedCity;

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
        Intent notificationIntent = new Intent(this, SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,0);

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
        private Ping notifyPing;
        private List<Ping> last5eq = new ArrayList<>();
        private String lastPingInformation;
        private Boolean isListEmpty = false;

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

            List<Ping> allPings = container.getPingList();

            for(int i=0; i<5; i++){
                if(allPings.get(i).getMagnitudeML() >= (double) selectedMagnitude) {
                    last5eq.add(allPings.get(i));
                }
            }
            if(last5eq.isEmpty()){
                isListEmpty = true;
                jobFinished(params, false);
                return null;
            }else{
                notifyPing = last5eq.get(0);
            }

            if(!(lastPingInformation.equals(notifyPing.toString())) && notifyPing.getTime().isAfter(localTime.minusMinutes(60))){
                startNotify(notifyPing);
            }

            jobFinished(params, false);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            NotificationCreator.editor = NotificationCreator.sharedPref.edit();
            selectedMagnitude = NotificationCreator.sharedPref.getInt("selectedMagnitude", 1);
            selectedCity = NotificationCreator.sharedPref.getString("selectedCity", "Çanakkale");
            lastPingInformation = NotificationCreator.sharedPref.getString("lastPing", "Nothing");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(isListEmpty == false){
                NotificationCreator.editor = NotificationCreator.sharedPref.edit();
                NotificationCreator.editor.putString("lastPing", notifyPing.toString());
                NotificationCreator.editor.commit();
            }
            WebListener.flag = false;
        }
    }
}

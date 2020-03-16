package com.ab.quake_iii;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;

public class OptionsActivity extends AppCompatActivity {
    private static final String TAG = "OptionsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        AndroidThreeTen.init(this);
    }

    public void startScheduleJob(View view) {
        LocalTime localTime = LocalTime.now();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ExampleJobService.localTime = localTime;
        }
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobInfo info = new JobInfo.Builder(123, componentName)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPersisted(true)
                    .setPeriodic(15*60*1000)
                    .build();
            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            int resultCode = scheduler.schedule(info);

            if(resultCode == JobScheduler.RESULT_SUCCESS)
                Log.d(TAG, "Job Scheduled.");
            else
                Log.d(TAG, "Job Scheduling Failed.");
        }
    }

    public void cancelJob(View v){
        JobScheduler scheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.cancel(123);
        }
    }
}

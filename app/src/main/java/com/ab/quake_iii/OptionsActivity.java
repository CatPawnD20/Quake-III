package com.ab.quake_iii;

import android.app.job.JobInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;

public class OptionsActivity extends AppCompatActivity {
    public static final String TAG = "OptionsActivity";
    private FirebaseJobDispatcher dispatcher;
    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        AndroidThreeTen.init(this);
        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void startScheduleJob(View view) {
        LocalTime localTime = LocalTime.now();
        //Eğer SDK yetersiz olursa EXampleJobService'de localtime null olur patlar dikkat et düzelt
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ExampleJobService.localTime = localTime;
        }
        Job myJob = dispatcher.newJobBuilder()
                .setService(ExampleJobService.class)
                .setTag("my-unique-tag")
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(false)
                .setTrigger(Trigger.executionWindow(1, 15))
                .setRecurring(true)
                .build();

        dispatcher.mustSchedule(myJob);
    }

    public void cancelJob(View v){
        dispatcher.cancel("muy-unique-tag");
    }
}

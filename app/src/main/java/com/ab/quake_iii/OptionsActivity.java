package com.ab.quake_iii;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class OptionsActivity extends AppCompatActivity {
    private FirebaseJobDispatcher dispatcher;
    public NotificationManagerCompat notificationManagerCompat;
    private Switch notificationSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        notificationManagerCompat = NotificationManagerCompat.from(this);

        notificationSwitch = findViewById(R.id.notificationSwitch);
        if(NotificationCreator.sharedPref.getBoolean("isNotificationOn", false) == true){
            notificationSwitch.setChecked(true);
        }

        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    NotificationCreator.editor = NotificationCreator.sharedPref.edit();
                    NotificationCreator.editor.putBoolean("isNotificationOn", true);
                    NotificationCreator.editor.commit();
                    Intent intent = new Intent(OptionsActivity.this, PopUpNotificationActivity.class);
                    startActivity(intent);
                    startScheduleJob();
                }else{
                    NotificationCreator.editor = NotificationCreator.sharedPref.edit();
                    NotificationCreator.editor.putBoolean("isNotificationOn", false);
                    NotificationCreator.editor.commit();
                    cancelJob();
                }
            }
        });
    }

    public void startScheduleJob() {
        Job myJob = dispatcher.newJobBuilder()
                .setService(NotificationJobService.class)
                .setTag("notification_job")
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setTrigger(Trigger.executionWindow(300, 320))
                .setRecurring(true)
                .build();

        dispatcher.mustSchedule(myJob);
    }

    public void cancelJob(){
        dispatcher.cancel("notification_job");
    }

}


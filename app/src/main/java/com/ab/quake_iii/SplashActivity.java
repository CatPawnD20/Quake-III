package com.ab.quake_iii;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.ab.quake_iii.MainActivity.context;

public class SplashActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imageView = findViewById(R.id.logoImageView);
        textView = findViewById(R.id.logoTextView);

        imageView.setAnimation(topAnim);
        textView.setAnimation(bottomAnim);

        if(!isNetworkAvailable(getApplicationContext()) || !isInternetAvailable()){
            //İnternet bağlantısı yoksa uyarmalı.
            new AlertDialog.Builder(SplashActivity.this)
                    .setTitle("Internet Connection!")
                    .setMessage("It seems that you don't have internet connection!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    })
                    .show();
        }else{
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //Bir network bağlantısı var mı kontrol ediyor.
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    //Network bağlantısı olsa dahi internete bağlanamıyor olabilir. Bağlanıyorsa true döner.
    public boolean isInternetAvailable() {

        final String command = "ping -c 1 google.com";
        try {
            return Runtime.getRuntime().exec(command).waitFor() == 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
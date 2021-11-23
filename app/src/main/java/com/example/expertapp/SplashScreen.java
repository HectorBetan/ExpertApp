package com.example.expertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    private SharedPreferences settings;
    private  static final long SPLASH_SCREEN_DELAY = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences("id", Context.MODE_PRIVATE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!settings.getBoolean("token", false)) {
                    Intent newIntent = new Intent(getApplicationContext(), Login.class);
                    startActivity(newIntent);
                    finish();
                }
                else {
                    Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(newIntent);
                    finish();
                }

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, SPLASH_SCREEN_DELAY);
    }
}
package com.zhirova.task_1;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler = new Handler(Looper.getMainLooper());

    private Runnable runnable = () -> {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        launchNextActivity();
    }


    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }


    private void launchNextActivity() {
        handler.postDelayed(runnable, 2000);
    }


}

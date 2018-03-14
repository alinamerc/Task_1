package com.zhirova.task_1;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
   boolean flag = false;
    private FragmentManager fragmentManager;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable  runnable = new Runnable() {
        @Override
        public void run() {
            flag = true;
            RecyclerviewFragment recyclerviewFragment = new RecyclerviewFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, recyclerviewFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            StartFragment curFragment = new StartFragment();
            fragmentTransaction.replace(R.id.container, curFragment);
            fragmentTransaction.commit();

            launchNextFragment();
        }
    }



    private void launchNextFragment() {
        handler.postDelayed(runnable, 2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}

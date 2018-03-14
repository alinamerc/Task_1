package com.zhirova.task_1;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            StartFragment curFragment = new StartFragment();
            fragmentTransaction.add(R.id.container, curFragment);
            fragmentTransaction.commit();

            launchNextFragment();
        }
    }


    private void launchNextFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerviewFragment recyclerviewFragment = new RecyclerviewFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, recyclerviewFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                Log.d("MAIN", "TROLOLOLO");
            }
        }, 2000);
    }


}

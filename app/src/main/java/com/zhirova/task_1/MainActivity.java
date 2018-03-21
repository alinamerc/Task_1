package com.zhirova.task_1;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity implements FillingFormFragment.NewItemListener {

    private static final String STATE_MANAGER = "fragmentManager";
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            ItemsFragment curFragment = new ItemsFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, curFragment, ItemsFragment.TAG);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onBackPressed() {
        if (fragmentManager != null) {
            if (fragmentManager.getBackStackEntryCount() == 0) {
                super.onBackPressed();
            } else {
                fragmentManager.popBackStack();
            }
        }
    }


    @Override
    public void onNewItem(String id) {
        Fragment fragment = fragmentManager.findFragmentByTag(ItemsFragment.TAG);
        if(fragment != null){
            ((ItemsFragment)fragment).setNeedScroll(id);
        }
    }


}

package com.zhirova.task_1;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initUI();

        View root = inflater.inflate(R.layout.fragment_start, container, false);
        return root;
    }


    private void initUI() {
        ActionBar toolbar = ((AppCompatActivity)getContext()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.start_title);
        }
    }


}

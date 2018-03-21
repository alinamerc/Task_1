package com.zhirova.task_1.fab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class FabHideOnSnackbar extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public FabHideOnSnackbar(Context context, AttributeSet attrs) {
        super();
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return (dependency instanceof Snackbar.SnackbarLayout);
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float movementY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(movementY);
        return true;
    }


}

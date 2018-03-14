package com.zhirova.task_1.model;

import android.graphics.Color;

import java.util.ArrayList;

public class ItemList {
    private int id;
    private boolean isExistCircle;
    private int circleColor;

    public static ArrayList<ItemList> items = new ArrayList<ItemList>();


    public ItemList(int id, boolean isExistCircle, int circleColor) {
        this.id = id;
        this.isExistCircle = isExistCircle;
        this.circleColor = circleColor;
        items.add(this);
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public boolean isExistCircle() {
        return isExistCircle;
    }


    public void setExistCircle(boolean existCircle) {
        isExistCircle = existCircle;
    }


    public int getCircleColor() {
        return circleColor;
    }


    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }


}

package com.zhirova.task_1.model;

import com.zhirova.task_1.R;

import java.util.ArrayList;
import java.util.List;

public class Note {

    private int id;
    private boolean isExistCircle;
    private int image;

    private static List<Note> allNotes = new ArrayList<Note>();
    private static final int COUNT = 50;


    public Note(int id, boolean isExistCircle, int pictureResource) {
        this.id = id;
        this.isExistCircle = isExistCircle;
        this.image = pictureResource;
    }


    public static void init() {
        int RESORCES[] = {R.drawable.circle_red, R.drawable.circle_orange, R.drawable.circle_yellow,
        R.drawable.circle_green, R.drawable.circle_blue, R.drawable.circle_darkblue, R.drawable.circle_purple};

        int resourceID = 1;
        for (int i = 0; i < COUNT; i++) {
            Note curNote;
            if (resourceID % 8 == 0) {
                curNote = new Note(i + 1, false, 0);
                resourceID = 0;
            } else {
                curNote = new Note(i + 1, true, RESORCES[resourceID - 1]);
            }
            allNotes.add(curNote);
            resourceID++;
        }
    }


    public static List<Note> getAllNotes() {
        return allNotes;
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


    public void setExistCircle(boolean isExistCircle) {
        this.isExistCircle = isExistCircle;
    }


    public int getImage() {
        return image;
    }


    public void setImage(int image) {
        this.image = image;
    }


}

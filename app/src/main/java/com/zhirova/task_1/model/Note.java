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
        for (int i = 0; i < COUNT; i++) {
            Note curNote = new Note(i + 1, true, R.drawable.circle_blue);
            allNotes.add(curNote);
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

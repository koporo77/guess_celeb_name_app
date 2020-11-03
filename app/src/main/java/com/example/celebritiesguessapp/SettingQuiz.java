package com.example.celebritiesguessapp;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Random;

public class SettingQuiz {
    private List<String> nameList;
    private List<Bitmap> imageList;

    public SettingQuiz(List<String> nameList, List<Bitmap> imageList) {
        this.nameList = nameList;
        this.imageList = imageList;
    }

    public List<Object> getQuizItems() {
        //get 1 <name> matches movie, 3 random name;
        //get 1 image matches <name>
        Random random = new Random();

        int correctInt = random.nextInt(nameList.size());
        String correctName = nameList.get(correctInt);
        Bitmap correctImage = imageList.get(correctInt);
        //get other 3 random name

    }
}

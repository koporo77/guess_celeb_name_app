package com.example.celebritiesguessapp;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SettingQuiz {
    private List<String> nameList;
    private List<Bitmap> imageList;

    public SettingQuiz(List<String> nameList, List<Bitmap> imageList) {
        this.nameList = nameList;
        this.imageList = imageList;
    }

    //get 4 names and 1 match image
    //first name is matched with image
    //List(ListName, Image)
    public List<Object> getQuizItems() {
        Random random = new Random();
        List<String> nameList = new ArrayList<>();

        int correctInt = random.nextInt(nameList.size());
        String correctName = nameList.get(correctInt);
        Bitmap correctImage = imageList.get(correctInt);
        List<Object> returnList = new ArrayList<>();

        nameList.add(correctName);

        //get other 3 random name
        for (int i = 0; i < 3; i++) {
            int inCorrectNumber = random.nextInt(nameList.size());
            while (inCorrectNumber == correctInt) {
                inCorrectNumber = random.nextInt(nameList.size());
            }
            nameList.add(nameList.get(inCorrectNumber));
        }
        returnList.add(nameList);
        returnList.add(correctImage);

        return returnList;
    }
}

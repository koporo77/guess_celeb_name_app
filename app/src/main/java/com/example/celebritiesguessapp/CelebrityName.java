package com.example.celebritiesguessapp;

import android.util.Log;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelebrityName {

    private String html;

    public CelebrityName(String html) {
        this.html = html;
    }

    public List<String> getNameList() {

        List<String> nameList = null;

        Pattern pattern = Pattern.compile("<span class=\"profile-info__item profile-info__item--name\">(.*?)</span>");
        Matcher matcher = pattern.matcher(html);

        //problem point
        while (matcher.find()) {
            nameList.add(matcher.group());
            Log.i("TAG", "nameList: " + matcher.group());
        }
        Log.i("TAG", "getNameList: " + nameList);
        return nameList;
    }

}



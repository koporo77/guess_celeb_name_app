package com.example.celebritiesguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String html;
    private List<Bitmap> imageList;
    private List<String> nameList;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHtml("https://www.instagram.com/little.reislin/");
//                setImageList();
//                setNameList();
//
                Log.i("checking", "html: " + html);
//                Log.i("checking", "imageList: " + imageList);
//                Log.i("checking", "nameList: " + nameList);
            }
        });

        //callble 없애고 메인에서 비동기 실행
        //problem point 해결





    }



    public void buttonClicked(View view) {
        //if clicked btn's id is equal to answer button
    }

    private void setImageList() {
        CelebrityImage celebrityImage = new CelebrityImage(html);
        try {
            imageList = celebrityImage.getImageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNameList() {
        CelebrityName celebrityName = new CelebrityName(html);
        celebrityName.getNameList();
    }

    private void setHtml(String url) {
        HtmlResource htmlResource = new HtmlResource(url);

        try {
            html = htmlResource.getHtml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
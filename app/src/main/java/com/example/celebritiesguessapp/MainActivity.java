package com.example.celebritiesguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

//    private String html;
    private List<String> imageUrlList;
    private List<String> titleUrlList;

    private List<Bitmap> imageBitmapList;

    ImageView imageView;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    private ExecutorService backgroundThread = Executors.newFixedThreadPool(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Callable<Void> callable = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        setResourceURL("https://www.cinemaqatar.com/");
//                        setImageList();
//                        imageView.setImageBitmap(imageBitmapList.get(0));

                        return null;
                    }
                };
                backgroundThread.submit(callable);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Callable<Void> callable = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        setImageList();
                        return null;
                    }
                };
                backgroundThread.submit(callable);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(imageBitmapList.get(1));
                Log.i("TAG", "setImage: " + imageBitmapList.get(1));
            }
        });



    }

    public void buttonClicked(View view) {
        //if clicked btn's id is equal to answer button
    }

    private void setResourceURL(String url) {
        HtmlResource htmlResource = new HtmlResource(url);

        try {
            List<List<String>> imageAndTitleList = htmlResource.getHtml();
            imageUrlList = imageAndTitleList.get(0);
            titleUrlList = imageAndTitleList.get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageList() {
        CelebrityImage celebrityImage = new CelebrityImage(imageUrlList);
        try {
            imageBitmapList = celebrityImage.getImageList();
            Log.i("TAG", "setImageList: " + imageBitmapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}








//    private void setNameList() {
//        CelebrityName celebrityName = new CelebrityName(html);
//        celebrityName.getNameList();
//    }





//                Callable<Void> callable = new Callable<Void>() {
//                    @Override
//                    public Void call() throws Exception {
//                        setHtml("https://www.instagram.com/little.reislin/");
//                        return null;
//                    }
//                };
//                backgroundThread.submit(callable);



                //                setHtml("https://www.instagram.com/little.reislin/");
////                setImageList();
////                setNameList();
////
//                Log.i("checking", "html: " + html);
////                Log.i("checking", "imageList: " + imageList);
////                Log.i("checking", "nameList: " + nameList);
//            }
//        });
//


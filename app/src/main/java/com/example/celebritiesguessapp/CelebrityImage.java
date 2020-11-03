package com.example.celebritiesguessapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//비동기 처리
public class CelebrityImage {
    //받은 url list 로 bitmap list 만든후 반환

    private List<String> imageUrlList;

    public CelebrityImage(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public List<Bitmap> getImageList() throws Exception {
        List<Bitmap> imageList = new ArrayList<>();

        for (String imageUrl : imageUrlList) {
            imageList.add(getImage(imageUrl));
        }
        imageList.removeAll(Collections.singleton(null));
        return imageList;
    }

    private Bitmap getImage(String imageURL) {
        try {
            URL url = new URL(imageURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


//    private List<String> getImageResPart(String html) {
//
//        List<String> imageParts = null;
//
//        Pattern pattern = Pattern.compile("background-image: url(\"(.*?)\");");
//        Matcher matcher = pattern.matcher(html);
//
//        while (matcher.find()) {
//            imageParts.add(matcher.group());
//            Log.i("TAG", "imagePartsList: " + matcher.group());
//
//        }
//        Log.i("TAG", "getImageResPart: " + imageParts);
//        return imageParts;
//    }


//        Callable<List<Bitmap>> callable = new Callable<List<Bitmap>>() {
//            @Override
//            public List<Bitmap> call() throws Exception {
//
//                List<String> imageResParts = getImageResPart(imageUrlList);
//                List<Bitmap> imageList = null;
//
//                for (String imageURL: imageResParts) {
//                    imageList.add(getImage(imageURL));
//                }
//                return imageList;
//
//            }
//        };
//        Future<List<Bitmap>> future = threads.submit(callable);
//
//        return future.get();

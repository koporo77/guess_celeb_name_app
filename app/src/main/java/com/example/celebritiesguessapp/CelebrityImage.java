package com.example.celebritiesguessapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//비동기 처리
public class CelebrityImage {
    private static final int threadNum = 1;
    private ExecutorService threads = Executors.newFixedThreadPool(threadNum);

    private String html;

    public CelebrityImage(String html) {
        this.html = html;
    }

    public List<Bitmap> getImageList() throws Exception {

        Callable<List<Bitmap>> callable = new Callable<List<Bitmap>>() {
            @Override
            public List<Bitmap> call() throws Exception {

                List<String> imageResParts = getImageResPart(html);
                List<Bitmap> imageList = null;

                for (String imageURL: imageResParts) {
                    imageList.add(getImage(imageURL));
                }
                return imageList;

            }
        };
        Future<List<Bitmap>> future = threads.submit(callable);

        return future.get();


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

    private List<String> getImageResPart(String html) {

        List<String> imageParts = null;

        Pattern pattern = Pattern.compile("background-image: url(\"(.*?)\");");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            imageParts.add(matcher.group());
            Log.i("TAG", "imagePartsList: " + matcher.group());

        }
        Log.i("TAG", "getImageResPart: " + imageParts);
        return imageParts;
    }
}

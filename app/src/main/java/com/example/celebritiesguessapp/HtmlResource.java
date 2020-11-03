package com.example.celebritiesguessapp;


import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HtmlResource {

    private static final int threadNum = 1;
    private ExecutorService threads = Executors.newFixedThreadPool(threadNum);

    private String input;

    public HtmlResource(String input) {
        this.input = input;
    }

    public String getHtml() throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getHtmlFromInternet();
            }
        };
        Future<String> future = threads.submit(callable);

        Log.i("TAG", "html result: " + future.get());
        return future.get();
    }

    private String getHtmlFromInternet() {

        try {
            String result = "";

            URL url = new URL(input);

            URLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            int data = reader.read();

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }

            return result;


        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }

    }




}

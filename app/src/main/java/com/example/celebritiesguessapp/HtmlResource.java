package com.example.celebritiesguessapp;


import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
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

    private ExecutorService backgroundThread = Executors.newFixedThreadPool(1);


    private String input;

    public HtmlResource(String input) {
        this.input = input;
    }

    public String getHtml() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getHtmlResource();
            }
        };

        Future<String> future = backgroundThread.submit(callable);
        try {
            String result = future.get();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }


    private String getHtmlResource() {


        try {
            Document document = Jsoup.connect(input).get();
            Log.i("TAG", "getHtml: " + document);
            Log.i("TAG", "getHtml: " + document.html());

            return document.html();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }


//        try {
//            String result = "";
//
//            URL url = new URL(input);
//
//            URLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = urlConnection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream);
//            int data = reader.read();
//
//            while (data != -1) {
//                char current = (char) data;
//                result += current;
//                data = reader.read();
//            }
//
//            return result;
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Failed";
//        }

    }

}

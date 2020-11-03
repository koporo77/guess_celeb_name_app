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
import java.util.ArrayList;
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

    public List<List<String>> getHtml() {
        return getHtmlResource();
    }


    private List<List<String>> getHtmlResource() {


        try {
            Document document = Jsoup.connect(input).get();
            Elements datas = document.select("span.thumbnail");

            List<String> imageUrlList = new ArrayList<>();
            List<String> titleUrlList = new ArrayList<>();
            List<List<String>> list = new ArrayList<>();

            for (int i = 0; i < datas.size(); i++) {
                String imageURL  = datas.select("span.thumbnail")
                        .select("img").eq(i).attr("src");
                String titleURL = datas.select("h4.gridminfotitle")
                        .select("span").eq(i).text();

                imageUrlList.add(imageURL);
                titleUrlList.add(titleURL);
            }
            Log.i("TAG", "titleList: " + titleUrlList);

            list.add(imageUrlList);
            list.add(titleUrlList);

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


// try {
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

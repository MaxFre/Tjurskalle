package com.example.tjurskalle;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;


public class Controller {
    private MainActivity mainActivity;



    Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        initializeStartPage();
    }

    private void initializeStartPage() {
        StartPage startPage = (StartPage) mainActivity.getFragment("StartPage");
        if (startPage == null) {
            startPage = new StartPage();
        }
        startPage.setMain(mainActivity);
        startPage.setController(this);
        mainActivity.setFragment(startPage);
    }


}

package com.example.mafr.p3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.Normalizer;

public class SendToServer {

    Controller controller;


    public SendToServer(Controller controller){
        this.controller = controller;
    }

    void sendResults(String results){

        results = results.replace(",","QZ");

        final String sendToserverString = results;

        new Thread(new Runnable() {
            public void run() {
                try {

                    System.out.println("sendToserverString - " + sendToserverString);
                    URL url = new URL("http://XXXXXXXXXXXXXX/addNewGame/"+sendToserverString);
                    HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                    httpConn.setRequestMethod("GET");

                    InputStream inputStream = httpConn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = bufferedReader.readLine();

                    httpConn.disconnect();
                    System.out.println("sent to server!");
                    controller.backToMain();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }
}

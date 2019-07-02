package com.example.mafr.p3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWorstGame {

    Controller controller;
    private String[] results;

    public GetWorstGame(Controller controller) {
        this.controller = controller;
    }



    public void gettingTheWorstRound(){

        new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://XXXXXXXXXXXXXXX/worstGame");
                    HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                    httpConn.setRequestMethod("GET");

                    InputStream inputStream = httpConn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = bufferedReader.readLine();

                    httpConn.disconnect();


                    results = line.split(",");

                    for (int x = 0; x < results.length; x++) {
                        results[x] = results[x].replace("[", "");
                        results[x] = results[x].replace("]", "");
                        results[x] = results[x].replace("\"", "");
                    }

                    System.out.println("this - " + results[0] + " " + results[1]);

                    sendTheResults();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }
    public void sendTheResults(){
        controller.worstGameResults(results);
    }

}

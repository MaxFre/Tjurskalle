package com.example.mafr.p3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetOldGamesIds {
    Controller controller;

    String[] ids;

    public GetOldGamesIds(Controller controller){
        this.controller = controller;
    }

    public void getIDs() {

        new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://XXXXXXXXXXXXXXXX/getOldGamesIds");
                    HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                    httpConn.setRequestMethod("GET");

                    InputStream inputStream = httpConn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = bufferedReader.readLine();

                    httpConn.disconnect();


                    ids = line.split(",");

                    for (int x = 0; x < ids.length; x++) {
                        ids[x] = ids[x].replace("[", "");
                        ids[x] = ids[x].replace("\"", "");
                        ids[x] = ids[x].replace("]", "");
                    }


                    giveOutIds();

                    } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }

    public void giveOutIds(){
        controller.givingTheIds(ids);
    }

}

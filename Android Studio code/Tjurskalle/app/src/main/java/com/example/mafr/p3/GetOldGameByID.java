package com.example.mafr.p3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class GetOldGameByID {

  ArrayList<String[]> oldrecords = new ArrayList<String[]>();
  private String id = "1";
  Controller controller;

  public GetOldGameByID(Controller controller){
    this.controller = controller;
  }


  public void getQuote(String id) {
    oldrecords.clear();
    this.id = id;

    final String theId = id;
    new Thread(new Runnable() {
      public void run() {
        try {
          URL url = new URL("http://XXXXXXXXXXXXXXXX/getOldGames/"+theId);
          HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
          httpConn.setRequestMethod("GET");

          InputStream inputStream = httpConn.getInputStream();
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
          String line = bufferedReader.readLine();

          httpConn.disconnect();


          String[] allPlayers = line.split("],");

          for (int x = 0; x < allPlayers.length; x++) {
            allPlayers[x] = allPlayers[x].replace("[", "");
            allPlayers[x] = allPlayers[x].replace("\"", "");
            allPlayers[x] = allPlayers[x].replace("]", "");
          }

          for (int x = 0; x < allPlayers.length; x++) {
            String[] aPlayer = allPlayers[x].split(",");
            oldrecords.add(aPlayer);
          }

          giveOldRecords();
        } catch (Exception e) {
          e.printStackTrace();
        }


      }
    }).start();


  }

  public void giveOldRecords(){
      controller.givingTheOldRecords(oldrecords);
  }

}

package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.utils.IOUtils;

public class PostingTester {

	public PostingTester(){
	try {

//		InputStream data = null;
//		Reader reader = null;
//		
//			HttpClient httpclient = HttpClients.createDefault();
//			
//			HttpGet httpGet = new HttpGet("http://192.168.0.8:5000/worstRound"); 
//	
//			//Execute and get the response.
//			HttpResponse response = httpclient.execute(httpGet);
//			
//			HttpEntity entity = response.getEntity();
//			
//			
//			if (entity != null) {
//			    try (InputStream instream = entity.getContent()) {
//
//			    	data = entity.getContent();
//			    	reader = new InputStreamReader(data);		
//			    	
//			    	StringWriter writer = new StringWriter();
//			    	IOUtils.copy(reader, writer);
//			    	String theString = writer.toString();
//			    	
//			    	
//	    	
//			    	
//			     	System.out.println("res - " + theString);
//			     	
//			     	
//					String[] results = theString.split(",");
//									
//					for (int x = 0; x < results.length; x++) {
//						results[x] = results[x].replace("[", "");
//						results[x] = results[x].replace("]", "");
//						results[x] = results[x].replace("\"", "");
//					}
//					
//					String toShow = results[0] + results[1];
//					System.out.println(toShow);
		
		
		ArrayList<String[]> oldGame = new ArrayList<String[]>();
		
		String results = "11npEmma,9,9,14,19npMalin,3,12,17,22npUlrika,23,49,54,109npKarin,23,46,51,56npJosefin,17,33,38,43np2019-06-21";
//		results = results.replace("QZ",",");
//		System.out.println(results);
		
		String[] playersScore = results.split("np");
		
		for(int i = 0; i<playersScore.length; i++){	
//				System.out.println(playersScore[i]);
				
				String[] aPlayersScore = playersScore[i].split(",");
				oldGame.add(aPlayersScore);
			
		}
		
		
		
		String stringtoStore = "";
		
		String nameOfHighestPoints = "";
		String nameOfhighestPointInAround = "";
		
		int highestPoints = 0;
		int highestPointInAround = 0;
			
		int lowestScore = 1000;
		String nameOfLowestScore = "";
		
		for(int i = 1; i<oldGame.size(); i++){			
			
			for(int x = 0; x<oldGame.get(i).length; x++){	
				if( x > 1 && highestPointInAround<Integer.parseInt(oldGame.get(i)[x]) - Integer.parseInt(oldGame.get(i)[x-1])){
					highestPointInAround= Integer.parseInt(oldGame.get(i)[x]) - Integer.parseInt(oldGame.get(i)[x-1]);
					nameOfhighestPointInAround = oldGame.get(i)[0];
				}
				
				if( x > 0 && highestPoints<Integer.parseInt(oldGame.get(i)[x])){
					highestPoints = Integer.parseInt(oldGame.get(i)[x]);
					nameOfHighestPoints = oldGame.get(i)[0];
				}
				
				if( i<oldGame.size()-1 && x == oldGame.get(i).length-1 && lowestScore>Integer.parseInt(oldGame.get(i)[x])){
					lowestScore = Integer.parseInt(oldGame.get(i)[x]);
					nameOfLowestScore = oldGame.get(i)[0];
				}		
				System.out.print(oldGame.get(i)[x] +"		");
			}
			System.out.println();
		}
		
	
		System.out.println();System.out.println();	
		System.out.println("Winner : " + nameOfLowestScore + "  " +lowestScore );
		System.out.println("Loser : " + nameOfHighestPoints + "  " +highestPoints );
		System.out.println("nameOfHighestPoints : " + nameOfHighestPoints + "  " +highestPoints );
		System.out.println("highestPointInARound : " + nameOfhighestPointInAround + "  " +highestPointInAround );
		
//		for(int i = 1; i<playersFinalScore.size(); i++){	
//			stringtoStore += "np";
//			for(int x = 0; x<playersFinalScore.get(i).length; x++){	
//				if( x > 0 && highestPointInAround<Integer.parseInt(playersFinalScore.get(i)[x])){
//					highestPointInAround = Integer.parseInt(playersFinalScore.get(i)[x]);
//					nameOfhighestPointInAround = playersFinalScore.get(i)[0];
//				}
//				System.out.print("  " + playersFinalScore.get(i)[x]);
//				if(x == 0){
//					stringtoStore += playersFinalScore.get(i)[x];
//				}
//				else{
//					stringtoStore += ","+ playersFinalScore.get(i)[x];
//				}
//				
//			}
//			
//		}
		
	
		
		

//		File file = new File("resources/oldGames.txt");
//		FileWriter fr = new FileWriter(file, true);
//		BufferedWriter br = new BufferedWriter(fr);
//		br.write("\n"+stringtoStore);
//
//		br.close();
//		fr.close();
		

		
		
//		String[] players = new String[]{"apa", "banan", "bjrön"};
//		String[][] test;
//		
//		
//		int index = players.length;
//		int currentRound = 7;
//		
//		test = new String[index][10];
//		
//		
//		for(int i = 0; i<players.length; i++){
//			test[i][0] = players[i];
//		}
//		
//		
//		
//		for(int i = 0; i<index; i++){
//			for(int x = 0; x<currentRound; x++){
//				if(test[i][x] == null){
//					System.out.print("[ ]	");
//				}	
//				else{
//					System.out.print("["+test[i][x]+"]	");
//				}
//			}
//			System.out.println("");
//		}
		
		
		
					
//			        	
//					
//				 ArrayList<String[]> oldrecords = new ArrayList<String[]>();
//					
//					for (int x = 0; x < allPlayers.length; x++) {										
//						String[] aPlayer = allPlayers[x].split(",");
//						oldrecords.add(aPlayer);					
//					}
//					
//					String toShow = "";
//					for (int x = 0; x < oldrecords.size(); x++) {										
//					 System.out.print("a player ");
//					
//						for (int i = 0; i < oldrecords.get(x).length; i++) {		
////							System.out.print(" " + oldrecords.get(x)[i]);
//							 toShow += " " + oldrecords.get(x)[i];
//						}
////						 System.out.println("");
//						 toShow += "\n";
//						
//					}
//					
//					
//					System.out.println(toShow);
					
//			     	JSONArray jsonArray = new JSONArray(theString);
//			    	
//			    	System.out.println(jsonArray.get(0));
//			    	
//			    	String firstPlayer =  jsonArray.get(0).toString();
//			    	
//			    	
//			    	String[] aPlayer = firstPlayer.split(",");		    	
//			    	System.out.print("a Player: ");
//			    	for(int x = 0; x<aPlayer.length; x++){			    		
//			    		aPlayer[x] = aPlayer[x].replace("[", "");
//			    		aPlayer[x] = aPlayer[x].replace("\"", "");
//			    		aPlayer[x] = aPlayer[x].replace("]", "");
//			    		
//			    		System.out.print(aPlayer[x] + "  ");			    		
//			    	}
			    	
			    	
//			    	System.out.println(res.getJSONObject("0").toString());
			    	
			    	
//			    	 String[] oldScores;
//			     	 oldScores = res.split("[");	
			    	
		
			     	 
			     	 
//			     	 String[] oldScores;
//			     	 oldScores = theString.split(",");	
////			    	
//			     	 
//				     System.out.println(oldScores[2]);
//			     	Gson gson=new GsonBuilder().create();
//			     	String jsonArray= gson.toJson(theString);
//			     	
//			     	 System.out.println(jsonArray.);
			     	 
//			    	System.out.println(res.get("totalOwnCapital").toString());
			    	
			   
//			    		System.out.println(theString.contains("theString"));

//			    	procent = res.getDouble("totalPerformancePercent");
//			    	String shorten = String.valueOf(procent);
			 	
			    	
			    	// ändra tbx efter idag.
//			    	procent = Double.parseDouble(shorten.substring(0,4));
//			    	procent = 0;
			    	
//					procent  = Double.parseDouble(res.get("performancePercent").toString());
//					double summa = Double.parseDouble(res.get("totalOwnCapital").toString());
					

			
//
//
			} catch (Exception e) {
			// Something didn't go well. No calls for us.
			e.printStackTrace();
			System.out.println("Failed to execute proper request or bad answer");
		}

	}

	public static void main(String[] args) {
		PostingTester run = new PostingTester();

	}
}

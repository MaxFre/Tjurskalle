package controller;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import spark.Request;

public class Controller {
	Bookkeeper bookkeeper;
    Server server;
    
	public Controller() {
		bookkeeper = new Bookkeeper();
		server = new Server();
		
		try {
			server.startServer(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		bookkeeper.getWorstGame();
//		bookkeeper.getWorstRound();
//		bookkeeper.getOldGames();
//		bookkeeper.findAllGamesId();

//		System.out.println(bookkeeper.getOldGames("1"));
	}
	
	
	public void gotNewResults(String string) {
		ArrayList<String[]> playersFinalScore = new ArrayList<String[]>();
		String results = string;
		results = results.replace("QZ", ",");
		String[] playersScore = results.split("np");
		for (int i = 0; i < playersScore.length; i++) {
			String[] aPlayersScore = playersScore[i].split(",");
			playersFinalScore.add(aPlayersScore);
		}

		String stringtoStore = "";		
		for (int i = 1; i < playersFinalScore.size(); i++) {
			stringtoStore += "np";
			for (int x = 0; x < playersFinalScore.get(i).length; x++) {
				System.out.println("  " + playersFinalScore.get(i)[x]);
				if (x == 0) {
					stringtoStore += playersFinalScore.get(i)[x];
				} else {
					stringtoStore += "," + playersFinalScore.get(i)[x];
				}

			}
		}
		
		String nameOfHighestPoints = "";
		String nameOfhighestPointInAround = "";
		
		int highestPoints = 0;
		int highestPointInAround = 0;
			
		for(int i = 1; i<playersFinalScore.size(); i++){	
//			System.out.print("player" + i);
			for(int x = 0; x<playersFinalScore.get(i).length; x++){	
				if( x > 1 && highestPointInAround<Integer.parseInt(playersFinalScore.get(i)[x]) - Integer.parseInt(playersFinalScore.get(i)[x-1])){
					highestPointInAround= Integer.parseInt(playersFinalScore.get(i)[x]) - Integer.parseInt(playersFinalScore.get(i)[x-1]);
					nameOfhighestPointInAround = playersFinalScore.get(i)[0];
				}
				if( x > 0 && highestPointInAround<Integer.parseInt(playersFinalScore.get(i)[x])){
					highestPoints = Integer.parseInt(playersFinalScore.get(i)[x]);
					nameOfHighestPoints = playersFinalScore.get(i)[0];
				}
//				System.out.print("  " + playersFinalScore.get(i)[x]);
			}
//			System.out.println();
		}
		
		checkIfnewWorstGame(nameOfHighestPoints, highestPoints);
		checkIfnewWorstRound(nameOfhighestPointInAround, highestPointInAround);
		bookkeeper.addNewGameToRecords(stringtoStore);
	}
	
	
	public ArrayList<String[]> findAllGamesId(){
		return bookkeeper.findAllGamesId();
	}
	
	public ArrayList getOldGames(String id){
		return bookkeeper.getOldGames(id);
	}	
	
	public String[] getWorstRound(){
		return bookkeeper.getWorstRound();
	}
	
	public String[] getWorstGame(){
		return bookkeeper.getWorstGame();
	}
	
	public void checkIfnewWorstGame(String name, int points){
		if(points>Integer.parseInt(bookkeeper.getWorstGame()[1])){
			bookkeeper.setWorstGame(name, points);
		}
	}

	public void checkIfnewWorstRound(String name, int points){
		if(points>Integer.parseInt(bookkeeper.getWorstRound()[1])){
			bookkeeper.setWorstRound(name,points);
		}
	}
	
	public void addPlayedGame(String game){
		bookkeeper.addPlayedGame(game);
	}
	
	
	public static void main(String[] args) {

		Controller run = new Controller();
		
	}

}

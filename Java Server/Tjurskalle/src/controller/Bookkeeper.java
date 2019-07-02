package controller;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Bookkeeper {

	public Bookkeeper()  {
//		getWorstGame();
//		getWorstRound();
//		setWorstRound("Max", 123);
//		setWorstGame("Josse", 321);
//		System.out.println("after");
//		getWorstGame();
//		getWorstRound();
//		addPlayedGame("Johannes,0,13,16,24,34npJosse,16,17,19,29,44npFrida,22,25,32,42,68npAndreas,3,13,24,33,44np2019-06-17");
//		getOldGames();
	}


	
	
	
	public void addPlayedGame(String game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("resources/oldGames.txt", true));
			writer.newLine();
			writer.write(game);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList getOldGames(String id){
		
		ArrayList<String[]> oldrecords = new ArrayList<String[]>();
		String[] oldScores;
		try {
			File file = new File("resources/oldGames.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			 while ((st = br.readLine()) != null){		
				 oldScores = st.split("np");				 			
				 if(oldScores[0].equals(id)){
//				 ArrayList<String> aPlayersScore = null;
				 
				 
				 for(int z = 1; z<oldScores.length; z++){					 
					 String test = oldScores[z];
					 String[] aPlayersScore = test.split(",");
					 oldrecords.add(aPlayersScore);								 

					 
					 
//					 if(oldScores[z].equals("np")){			
//					 String[] player = aPlayersScore.toArray(new String[aPlayersScore.size()]);  
//					 oldrecords.add(player);	
//					 }
					 
					 
				 }
				 
//				  =  Arrays.copyOf(oldScores, oldScores.length);
//				 oldrecords.add(aPlayersScore);		
			}}
	
//			 for(int x = 0; x<oldrecords.size(); x++){
//				 System.out.println("newGame");
//				 for(int i = 0; i<oldrecords.get(x).length; i++){
//						System.out.println(oldrecords.get(x)[i]);
//					}
//			 }
			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldrecords;	
	}
	
	public String[] getWorstGame() {
		String[] parts = null;
		try {
			File file = new File("resources/worstGame.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			st = br.readLine();
			parts = st.split(",");
			String name = parts[0];
			String points = parts[1];
//			System.out.println("worst game : " + name + "  :  " + points + " points");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parts;
	}

	public String[] getWorstRound() {
		String[] parts = null;
	
		try {
			File file = new File("resources/worstRound.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			st = br.readLine();
			parts = st.split(",");
			String name = parts[0];
			String points = parts[1];
//			System.out.println("worst round : " + name + "  :  " + points + " points");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parts;
	}
	
	public void setWorstRound(String name, int points) {

		File file = new File("resources/worstRound.txt");
		file.delete();
		file = new File("resources/worstRound.txt");
		try (FileWriter writer = new FileWriter(file); 
		 BufferedWriter bw = new BufferedWriter(writer)) {
			bw.write(name + "," + points);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}
	
	public void setWorstGame(String name, int points) {

		File file = new File("resources/worstGame.txt");
		file.delete();
		file = new File("resources/worstGame.txt");
		try (FileWriter writer = new FileWriter(file); 
		 BufferedWriter bw = new BufferedWriter(writer)) {
			bw.write(name + "," + points);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	public void addNewGameToRecords(String toAdd) {
		try {
			File file = new File("resources/oldGames.txt");
			FileWriter fr;
			fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			
			ArrayList<String[]> testar = findAllGamesId();
			
			String lastId = testar.get(testar.size()-1)[0];
			int newID = Integer.parseInt(lastId) + 1;
			
			br.write("\n" + newID+ toAdd);
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public ArrayList<String[]> findAllGamesId() {
		ArrayList<String[]> oldrecords = new ArrayList<String[]>();
		String[] oldScores;
		try {
			File file = new File("resources/oldGames.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			 while ((st = br.readLine()) != null){		
				 oldScores = st.split("np");	
				 String[] aPlayersScore =  Arrays.copyOf(oldScores, 1);
//				 System.out.println("kollar - " + aPlayersScore[0]);
				 oldrecords.add(aPlayersScore);		
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldrecords;
		
		
	}

	
}

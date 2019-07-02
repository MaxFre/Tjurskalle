package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Server {

	Controller controller;
	
	public void startServer(Controller controller) throws Exception {
		this.controller = controller;
		port(5000);
			
			
		
		get("/addNewGame/:results", (req, res) -> {
			res.type("application/json");
			res.header("Access-Control-Allow-Origin", "*");
			String results = req.params(":results");
			controller.gotNewResults(results);
			 System.out.println("A new game was sent in!");
			return "thank you";		
		});

			
		get("/getOldGamesIds", (req, res) -> {
			res.type("application/json");
			res.header("Access-Control-Allow-Origin", "*");
			Gson gson=new GsonBuilder().create();
			String jsonArray=gson.toJson(controller.findAllGamesId());
		    System.out.println("Someone wanted all the ID:s " + jsonArray);
			return jsonArray;		
		});
		
		get("/getOldGames/:id", (req, res) -> {
			res.type("application/json");
			res.header("Access-Control-Allow-Origin", "*");
			String id = req.params(":id");
			System.out.println("From app: " + id);
			Gson gson=new GsonBuilder().create();
			 String jsonObject = gson.toJson(controller.getOldGames(id));
		    System.out.println("Someone wanted a game by the id: "+ id+" - " + jsonObject);
			return jsonObject;		
		});
					
		
		get("/worstRound", (req, res) -> {
			res.type("application/json");
			res.header("Access-Control-Allow-Origin", "*");
			Gson gson=new GsonBuilder().create();;
		    String jsonObject = gson.toJson(controller.getWorstRound());
		    System.out.println("Someone wanted worst round - " + jsonObject);
			return jsonObject;		
		});
			
		
		get("/worstGame", (req, res) -> {
			res.type("application/json");
			res.header("Access-Control-Allow-Origin", "*");
			Gson gson=new GsonBuilder().create();;
		    String jsonObject = gson.toJson(controller.getWorstGame());
		    System.out.println("Someone wanted worst game - " + jsonObject);
			return jsonObject;		
		});
		
		get("/Time", (req, res) -> {
			res.type("application/json");
			res.header("Access-Control-Allow-Origin", "*");
			Date date = new Date();
			String hour = String.valueOf(date.getHours());
			String minutes = String.valueOf(date.getMinutes());
			String seconds = String.valueOf(date.getSeconds());		
			if(date.getHours()<10){
				hour = "0" + date.getHours();
			}
			if(date.getMinutes()<10){
				minutes = "0" + date.getMinutes();
			}
			if(date.getSeconds()<10){
				seconds = "0" + date.getSeconds();
			}
			
			String time = hour +":" + minutes +":"+ seconds;
			Gson gson=new GsonBuilder().create();;
		    String jsonObject = gson.toJson(time);
			return jsonObject;	
		});
		

	}

}

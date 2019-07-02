package com.example.mafr.p3;

import java.util.ArrayList;


class Controller {
  private MainActivity mainActivity;
  private String[] roundResults;
  private String[] gameResults;
  private ArrayList<String> players;
  private boolean gameisON = false;

  AddPlayers addPlayers;
  NewGame newGame;
  StartPage startPage;
  GetOldGamesIds getOldGamesIds = new GetOldGamesIds(this);
  GetOldGameByID getOldGameByID = new GetOldGameByID(this);
  ShowWorstRound showWorstRound;
  ShowWorstGame showWorstGame;
  GetWorstRound getWorstRound = new GetWorstRound(this);
  GetWorstGame getWorstGame = new GetWorstGame(this);

  Controller(MainActivity mainActivity) {
    this.mainActivity = mainActivity;

    initializeStartPage();
    iniShowWorstRound();
    iniShowWorstGame();
    iniNewGame();
    iniAddPlayer();
  }

  private void initializeStartPage() {
     startPage = (StartPage) mainActivity.getFragment("StartPage");
    if (startPage == null) {
      startPage = new StartPage();
    }
    startPage.setMain(mainActivity);
    startPage.setController(this);
    mainActivity.setFragment(startPage);
  }
  private void iniShowWorstRound() {
    showWorstRound = (ShowWorstRound)mainActivity.getFragment("showWorstRound");
    if(showWorstRound == null) {
      showWorstRound = new ShowWorstRound();
    }
    showWorstRound.setController(this);
    showWorstRound.setMain(mainActivity);
  }

  private void iniNewGame() {
    newGame = (NewGame)mainActivity.getFragment("newGame");
    if(newGame == null) {
      newGame = new NewGame();
    }
    newGame.setController(this);
    newGame.setMain(mainActivity);
  }

  private void iniShowWorstGame() {
    showWorstGame = (ShowWorstGame)mainActivity.getFragment("showWorstGame");
    if(showWorstGame == null) {
      showWorstGame = new ShowWorstGame();
    }
    showWorstGame.setController(this);
    showWorstGame.setMain(mainActivity);
  }

  private void iniAddPlayer() {
    addPlayers = (AddPlayers)mainActivity.getFragment("addPlayers");
    if(addPlayers == null) {
      addPlayers = new AddPlayers();
    }
    addPlayers.setController(this);
    addPlayers.setMain(mainActivity);
  }


  /** GO TO MAIN SCREEN**/
  void backToMain(){
    startPage.setMain(mainActivity);
    startPage.setController(this);
    mainActivity.setFragment(startPage);
  }

   void goToShowWorstRound(){
     getWorstRound.gettingTheWorstRound();
  }
   void goToShowWorstGame(){
     getWorstGame.gettingTheWorstRound();
  }

  void goToNewGame(){
    mainActivity.setFragment(newGame);
  }
  void goToNewGame(ArrayList<String> players){
    this.players = players;
    mainActivity.setFragment(newGame);
  }

  void goToAddPlayer(){
    mainActivity.setFragment(addPlayers);
  }

  public void showTheRoundResulsts(){
    showWorstRound.showResults(roundResults);
  }

  public void showTheGameResulsts(){
   showWorstGame.showResults(gameResults);
  }
  public void showPlayers(){
    newGame.getThePlayer(players);
  }

  /** FIND WORST ROUND AND GAME**/
  void worstRoundResults(String[] results){
    this.roundResults = results;
    mainActivity.setFragment(showWorstRound);

  }
  void worstGameResults(String[] results){
    this.gameResults = results;
    mainActivity.setFragment(showWorstGame);
  }


  void startGameWithThesePlayers(ArrayList<String> players){
    goToNewGame(players);
  }


  void sendToServer(String results){
    addPlayers.resetPlayers();
    SendToServer send = new SendToServer(this);
    send.sendResults(results);
  }
  /** FIND IDS AND RECCORDS**/
  void givingTheIds(String[] oldIds){
    startPage.setIds(oldIds);
  }

  void givingTheOldRecords( ArrayList<String[]> oldrecords){
    startPage.getOldRecords(oldrecords);
  }

  void getOldRecordsID() {
    System.out.println("getting old Records ID");
    new Thread(new Runnable() {
      public void run() {
        try {
          mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              getOldGamesIds.getIDs();
            }
          });

        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  void getOldRecords(String id) {
    System.out.println("get specific old records:" + id);
    final String theId = id;
    new Thread(new Runnable() {
      public void run() {
        try {
          mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              getOldGameByID.getQuote(theId);
            }
          });

        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }


}

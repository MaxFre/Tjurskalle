package com.example.mafr.p3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class NewGame extends Fragment {
    private Controller controller;
    private MainActivity main;


    private Button btnBackFromNewGame;
    private Button btnNextPlayer;
    private Button btnPrevPlayer;
    private Button btnSubmit;
    private Button btnDone;
    private TextView tvCurrentGame;
    private TextView tvWhosTurn;
    private EditText ipPoints;

    boolean started = false;
    ArrayList<String> players;
    private int index = 0;
    private String[][] playersPoints;
    private int currentRound = 0;
    private int howManyHaveGottenPoints  = 0;
    private boolean foundALoser = false;
    private int loserIndex = 0;
    public void setMain(MainActivity main){
        this.main = main;
    }
    public void setController(Controller controller){
        this.controller = controller;
    }

    public NewGame() {
        // Required empty public constructor
    }

    public void getThePlayer(ArrayList<String> players){
        String toShow = "";
        this.players = players;
        for (int x = 0; x < players.size(); x++) {

                toShow += " " + players.get(x) + "  0  \n";


            }
            toShow += "\n\n";

        tvCurrentGame.setText(toShow);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);
        initializeComponents(view);

        return view;
    }

    private void initializeComponents(View view) {


        btnBackFromNewGame = view.findViewById(R.id.btnBackFromNewGame);
        btnNextPlayer = view.findViewById(R.id.btnNextPlayer);
        btnPrevPlayer = view.findViewById(R.id.btnPrevPlayer);
        btnDone = view.findViewById(R.id.btnDone);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        tvCurrentGame = view.findViewById(R.id.tvCurrentGame);
        tvWhosTurn = view.findViewById(R.id.tvWhosTurn);
        ipPoints = view.findViewById(R.id.ipPoints);
        tvCurrentGame.setMovementMethod(LinkMovementMethod.getInstance());

        controller.showPlayers();

        playersPoints = new String[players.size()][10];
        for(int i = 0; i<players.size(); i++){
            playersPoints[i][0] = players.get(i);
        }

        tvWhosTurn.setText("Press start to begin the game");
        btnBackFromNewGame.setEnabled(false);
        btnNextPlayer.setEnabled(false);
        btnPrevPlayer.setEnabled(false);
        ipPoints.setEnabled(false);
        btnSubmit.setEnabled(false);

        tvWhosTurn.setMovementMethod(LinkMovementMethod.getInstance());


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(started){
                    if(!foundALoser) {
                        btnDone.setText("Done this turn");
                        index = 0;
                        tvWhosTurn.setText("Input points for " + players.get(index));
                        btnNextPlayer.setEnabled(true);
                        btnPrevPlayer.setEnabled(true);
                        ipPoints.setEnabled(true);
                        btnDone.setEnabled(false);
                    }
                    else{
                        tvWhosTurn.setText("Player " + players.get(loserIndex) + " lost!");
                        btnNextPlayer.setEnabled(false);
                        btnPrevPlayer.setEnabled(false);
                        btnDone.setEnabled(false);
                        btnSubmit.setEnabled(true);
                        finalText();
                    }
                }
                if(!started){
                    if(!foundALoser){
                    tvWhosTurn.setText("Press enter points to continue");
                    currentRound++;
                    btnDone.setText("Enter points");
                    btnNextPlayer.setEnabled(false);
                    btnPrevPlayer.setEnabled(false);
                    ipPoints.setEnabled(false);
                    }
                    else{
                        tvWhosTurn.setText("Player " + players.get(loserIndex) + " lost!");
                        btnNextPlayer.setEnabled(false);
                        btnPrevPlayer.setEnabled(false);
                        btnDone.setEnabled(false);
                        btnSubmit.setEnabled(true);
                        ipPoints.setEnabled(false);
                        finalText();
                    }
                }
                started = !started;
            }

        });

        btnNextPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromInput = String.valueOf(ipPoints.getText());
                if(!fromInput.equals("")){
                    if(currentRound>1){
                        int current = (Integer.parseInt(fromInput));
                        int prev = Integer.parseInt(playersPoints[index][currentRound-1]);
                        if(current+ prev>65){
                            foundALoser = true;
                            loserIndex = index;
                            System.out.println("FOUND A LOSER!");
                        }
                        String newAnswer = String.valueOf(current+ prev);
                        playersPoints[index][currentRound] = newAnswer;
                    }
                    else{
                        playersPoints[index][currentRound ] = fromInput;
                    }
                    howManyHaveGottenPoints++;
                    if( howManyHaveGottenPoints >= players.size()){
                        btnDone.setEnabled(true);
                        howManyHaveGottenPoints = 0;
                    }
                if(index == players.size()-1){
                    index = 0;
                }
                else{
                    index++;
                }
                tvWhosTurn.setText("Input points for " + players.get(index));
                updateText();
                }
                else{
                    if(index == players.size()-1){
                        index = 0;
                    }
                    else{
                        index++;
                    }

                    tvWhosTurn.setText("Input points for " + players.get(index));
                }
            }
        });

        btnPrevPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromInput = String.valueOf(ipPoints.getText());

                if(!fromInput.equals("")){
                    if(currentRound>1){
                        int current = (Integer.parseInt(fromInput));
                        int prev = Integer.parseInt(playersPoints[index][currentRound-1]);
                        if(current+ prev>65){
                            foundALoser = true;
                            loserIndex = index;
                            System.out.println("FOUND A LOSER!");
                        }
                        String newAnswer = String.valueOf(current+ prev);
                        playersPoints[index][currentRound] = newAnswer;
                    }
                    else{
                        playersPoints[index][currentRound ] = fromInput;
                    }
                    howManyHaveGottenPoints++;
                    if( howManyHaveGottenPoints >= players.size()){
                        btnDone.setEnabled(true);
                        howManyHaveGottenPoints = 0;
                    }
                    if(index == 0){
                        index = players.size()-1;
                    }
                    else{
                        index--;
                    }
                    tvWhosTurn.setText("Input points for " + players.get(index));
                    updateText();
                }
                else{
                    if(index == 0){
                        index = players.size()-1;
                    }
                    else{
                        index--;
                    }
                    tvWhosTurn.setText("Input points for " + players.get(index));
                }
            }
        });

        btnBackFromNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backToMain();
            }

        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String results = "np";

                for(int i = 0; i<players.size(); i++){
                    for(int x = 0; x<currentRound+1; x++){
                        if(playersPoints[i][x] == null){
                            results += " ";
                        }
                        else{
                            results += playersPoints[i][x]+ ",";
                        }
                    }
                    results += "np";
                }

                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c);
                results += formattedDate;

                btnBackFromNewGame.setEnabled(false);

                started = false;
                players.clear();
                index = 0;
                playersPoints = null;
                currentRound = 0;
                howManyHaveGottenPoints  = 0;
                foundALoser = false;
                loserIndex = 0;

                controller.sendToServer(results);
            }

        });


    }


    public void finalText(){
        ipPoints.setText("");
        String toShow = "";

        for(int i = 0; i<players.size(); i++){
            for(int x = 0; x<currentRound+1; x++){
                if(playersPoints[i][x] == null){
                    toShow += " ";
                }
                else{
                    toShow += playersPoints[i][x]+ "  ";
                    if(i == loserIndex && x == currentRound){
                        toShow += "Loser!";
                    }
                }
            }
            toShow += "\n";
        }
        tvCurrentGame.setText(toShow);

    }

    public void updateText(){
        ipPoints.setText("");
        String toShow = "";

        for(int i = 0; i<players.size(); i++){
            for(int x = 0; x<currentRound+1; x++){
                if(playersPoints[i][x] == null){
                    toShow += " ";
                }
                else{
                    toShow += playersPoints[i][x]+ "  ";
                }
            }
            toShow += "\n";
        }
        tvCurrentGame.setText(toShow);
    }
}

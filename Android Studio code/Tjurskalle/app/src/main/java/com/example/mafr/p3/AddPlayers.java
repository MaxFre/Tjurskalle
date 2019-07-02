package com.example.mafr.p3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AddPlayers extends Fragment {

    private Controller controller;
    private MainActivity main;

    private Button btnDone;
    private Button btnAddPlayer;
    private Button btnRemove;
    private Button btnBackFromAddPlayer;
    private TextView tvPlayersAdd;
    private EditText eTInputPlayer;

    private ArrayList<String> players = new ArrayList<String>();

    public AddPlayers() {
        // Required empty public constructor
    }

    public void setMain(MainActivity main){
        this.main = main;
    }
    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_players, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view) {


        btnBackFromAddPlayer = view.findViewById(R.id.btnBackFromAddPlayer);
        btnDone = view.findViewById(R.id.btnDone);
        btnAddPlayer = view.findViewById(R.id.btnAddPlayer);
        btnRemove = view.findViewById(R.id.btnRemove);
        tvPlayersAdd = view.findViewById(R.id.tvPlayersAdd);
        eTInputPlayer = view.findViewById(R.id.eTInputPlayer);

        tvPlayersAdd.setMovementMethod(LinkMovementMethod.getInstance());


        btnBackFromAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players.clear();
                controller.backToMain();
            }

        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eTInputPlayer.getText().length()>1){

                    String add = String.valueOf(eTInputPlayer.getText());
                    players.add(add);
                    eTInputPlayer.setText("");
                    String toShow = "";

                    for(int i = 0; i<players.size(); i++){

                        toShow += players.get(i) + "\n";
                    }
                    tvPlayersAdd.setText(toShow);


            }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(players.size()>0){
                    String toShow = "";
                    players.remove(players.size()-1);
                    for(int i = 0; i<players.size(); i++){

                        toShow += players.get(i) + "\n";
                    }
                    tvPlayersAdd.setText(toShow);
                }

            }

        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(players.size()>1){
                    controller.startGameWithThesePlayers(players);
                }

            }

        });
    }

    public void resetPlayers(){
        players.clear();
    }
}

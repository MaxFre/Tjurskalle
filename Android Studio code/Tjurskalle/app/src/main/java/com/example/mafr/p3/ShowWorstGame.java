package com.example.mafr.p3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ShowWorstGame extends Fragment {
    private MainActivity main;
    private Controller controller;
    private Button btnBackFromWG;
    private TextView tvWorstGame;

    public ShowWorstGame() {
        // Required empty public constructor
    }

    public void setMain(MainActivity main){
        this.main = main;
    }
    public void setController(Controller controller){
        this.controller = controller;
    }

    public void showResults(String[] results){
        String toShow = results[1] + "\n\n by \n\n" + results[0];
        tvWorstGame.setText("Worst result of  a whole game: \n\n"+ toShow);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_worst_game, container, false);
        initializeComponents(view);

        return view;
    }

    private void initializeComponents(View view) {

        btnBackFromWG = view.findViewById(R.id.btnBackFromWG);
        tvWorstGame = view.findViewById(R.id.tvWorstGame);


        btnBackFromWG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backToMain();
            }

        });


        controller.showTheGameResulsts();
    }
}
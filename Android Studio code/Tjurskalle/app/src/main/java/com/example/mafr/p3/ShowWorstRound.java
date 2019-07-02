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

public class ShowWorstRound extends Fragment {

    private MainActivity main;
    private Controller controller;
    private Button btnBackFromWR;
    private TextView tvWorstRound;

    public ShowWorstRound() {
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
        tvWorstRound.setText("Worst result in a single round: \n\n"+ toShow);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_worst_round, container, false);
        initializeComponents(view);

        return view;
    }

    private void initializeComponents(View view) {

        btnBackFromWR = view.findViewById(R.id.btnBackFromWR);
        tvWorstRound = view.findViewById(R.id.tvWorstRound);


        btnBackFromWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backToMain();
            }

        });


        controller.showTheRoundResulsts();
    }
}

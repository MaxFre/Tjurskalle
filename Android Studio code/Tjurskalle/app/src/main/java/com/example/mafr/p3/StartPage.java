package com.example.mafr.p3;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sebastian Andersson
 */

public class StartPage extends android.support.v4.app.Fragment {
    private Controller controller;
    private Button btnGetWorstGame;
    private Button btnGetWorstRound;
    private Button btnNext;
    private Button btnPrev;
    private Button btnStartNewGame;
    private TextView tvOldGame;
    private TextView tvIds;
    private MainActivity main;



    String[] ids;
    int pointer = 1;
    boolean next = false;

    public StartPage() {
        // Required empty public constructor
    }

    public void setMain(MainActivity main){
        this.main = main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_page, container, false);
        initializeComponents(view);

        return view;
    }

    public void getOldRecords(ArrayList<String[]> oldGame){
        String toShow = "";
        String nameOfHighestPoints = "";
        String nameOfhighestPointInAround = "";

        int highestPoints = 0;
        int highestPointInAround = 0;

        int lowestScore = 1000;
        String nameOfLowestScore = "";

        for(int i = 0; i<oldGame.size(); i++){

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
                if(i == oldGame.size()-1){
                    toShow +=  nameOfLowestScore + ": Winner!" + "\n" + nameOfHighestPoints + ": Loser!\n\n";
                    tvOldGame.setText(toShow);
                }
                toShow += oldGame.get(i)[x] +"  ";
            }
            toShow += "\n\n";
        }
        tvOldGame.setText(toShow);
    }

    public void setIds(String[] ids){
        this.ids = ids;
        pointer = ids.length-1;
        controller.getOldRecords(ids[pointer]);
        tvIds.setText(pointer + "/"+(ids.length-1));
    }



    private void initializeComponents(View view) {

        btnStartNewGame = view.findViewById(R.id.btnStartNewGame);
        btnGetWorstGame = view.findViewById(R.id.btnGetWorstGame);
        btnGetWorstRound = view.findViewById(R.id.btnGetWorstRound);
        btnNext = view.findViewById(R.id.btnNext);
        btnPrev = view.findViewById(R.id.btnPrev);
        tvOldGame = view.findViewById(R.id.tvOldGames);
        tvIds = view.findViewById(R.id.tvIds);

        tvOldGame.setMovementMethod(LinkMovementMethod.getInstance());


        controller.getOldRecordsID();

        btnStartNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { controller.goToAddPlayer();
            }

        });

        btnGetWorstGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.goToShowWorstGame();
            }

        });

        btnGetWorstRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.goToShowWorstRound();
            }

        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pointer==1){ pointer = ids.length-1;}
                else{ pointer --; }
                tvIds.setText(pointer + "/"+(ids.length-1));
                controller.getOldRecords(ids[pointer]);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvIds.setText(pointer + "/"+(ids.length-1));
                if(pointer==ids.length-1){ pointer = 1;}
                else{ pointer ++; }
                tvIds.setText(pointer + "/"+(ids.length-1));
                controller.getOldRecords(ids[pointer]);

            }

        });


    }


 /**
    public void fadeInAnimiation(){
        ObjectAnimator fadeInPictureStart = ObjectAnimator.ofFloat(ivStart, "alpha", 0f, 1f);
        ObjectAnimator fadeInButtonStart = ObjectAnimator.ofFloat(btnStart, "alpha", 0f, 1f);

        AnimatorSet as = new AnimatorSet();
        as.play(fadeInPictureStart).before(fadeInButtonStart);
        as.setDuration(2000);
        as.start();

        fadeInPictureStart.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        fadeInButtonStart.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                okeyToPress = true;
                alreadyPressed();
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
  **/

    public void setController(Controller controller) {
        this.controller = controller;
    }

}


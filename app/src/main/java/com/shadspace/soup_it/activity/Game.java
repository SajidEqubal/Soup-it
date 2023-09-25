package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shadspace.soup_it.R;

import soup.neumorphism.NeumorphTextView;

public class Game extends AppCompatActivity {

    LinearLayout play1, play2, play3, play4, play5, play6, play7, play8, play9, play10, play11, play12, play13, play14, play15, play16;


    LinearLayout LinMind, LinRace, LinArcade, LinAction, showAll;
    NeumorphTextView Headline;

    LinearLayout Racing, Mind, Arcade, Action;

    ImageView Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_game);


        String url1 = "https://play.famobi.com/stack-smash/A-5J2LC";
        String url2 = "https://play.famobi.com/bubble-woods/A-5J2LC";
        String url3 = "https://play.famobi.com/high-hills/A-5J2LC";
        String url4 = "https://play.famobi.com/dunk-brush/A-5J2LC";
        String url5 = "https://play.famobi.com/monkey-bounce/A-5J2LC";
        String url6 = "https://play.famobi.com/knife-rain/A-5J2LC";
        String url7 = "https://play.famobi.com/3d-darts/A-5J2LC";
        String url8 = "https://play.famobi.com/banana-run/A-5J2LC";
        String url9 = "https://play.famobi.com/slice-rush/A-5J2LC";
        String url10 = "https://play.famobi.com/alien-quest/A-5J2LC";
        String url11 = "https://play.famobi.com/moto-x3m-pool-party/A-5J2LC";
        String url12 = "https://play.famobi.com/1-sound-1-word/A-5J2LC";
        String url13 = "https://play.famobi.com/cannon-balls-3d/A-5J2LC";
        String url14 = "https://play.famobi.com/7-words/A-5J2LC";
        String url15 = "https://play.famobi.com/sweet-hangman/A-5J2LC";
        String url16 = "https://play.famobi.com/archery-world-tour/A-5J2LC";


        LinMind = findViewById(R.id.LinMind);
        LinRace = findViewById(R.id.LinRace);
        LinArcade = findViewById(R.id.LinArcade);
        LinAction = findViewById(R.id.LinAction);

        Racing = findViewById(R.id.Racing);
        Mind = findViewById(R.id.Mind);
        Arcade = findViewById(R.id.Arcade);
        Action = findViewById(R.id.Action);

        Profile = findViewById(R.id.Profile);

        Headline = findViewById(R.id.Headline);
        showAll = findViewById(R.id.showAll);

        showAll.setVisibility(View.GONE);


        LinMind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAll.setVisibility(View.VISIBLE);

                Headline.setText("Mind Peace");

                Mind.setVisibility(View.VISIBLE);
                Racing.setVisibility(View.GONE);
                Arcade.setVisibility(View.GONE);
                Action.setVisibility(View.GONE);


            }
        });

        LinRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Headline.setText("Racing");

                showAll.setVisibility(View.VISIBLE);


                Mind.setVisibility(View.GONE);
                Racing.setVisibility(View.VISIBLE);
                Arcade.setVisibility(View.GONE);
                Action.setVisibility(View.GONE);

            }
        });

        LinArcade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAll.setVisibility(View.VISIBLE);

                Headline.setText("Arcade");

                Mind.setVisibility(View.GONE);
                Racing.setVisibility(View.GONE);
                Arcade.setVisibility(View.VISIBLE);
                Action.setVisibility(View.GONE);
            }
        });

        LinAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAll.setVisibility(View.VISIBLE);

                Headline.setText("Action");

                Mind.setVisibility(View.GONE);
                Racing.setVisibility(View.GONE);
                Arcade.setVisibility(View.GONE);
                Action.setVisibility(View.VISIBLE);

            }
        });


        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Headline.setText("New Games");
                showAll.setVisibility(View.GONE);

                Mind.setVisibility(View.VISIBLE);
                Racing.setVisibility(View.VISIBLE);
                Arcade.setVisibility(View.VISIBLE);
                Action.setVisibility(View.VISIBLE);


            }
        });


        play1 = findViewById(R.id.play1);
        play2 = findViewById(R.id.play2);
        play3 = findViewById(R.id.play3);
        play4 = findViewById(R.id.play4);
        play5 = findViewById(R.id.play5);
        play6 = findViewById(R.id.play6);
        play7 = findViewById(R.id.play7);
        play8 = findViewById(R.id.play8);
        play9 = findViewById(R.id.play9);
        play10 = findViewById(R.id.play10);
        play11 = findViewById(R.id.play11);
        play12 = findViewById(R.id.play12);
        play13 = findViewById(R.id.play13);
        play14 = findViewById(R.id.play14);
        play15 = findViewById(R.id.play15);
        play16 = findViewById(R.id.play16);


        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url1);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url2);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url3);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url4);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url5);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url6);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url7);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url8);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url9);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url10);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url11);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url12);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url13);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url14);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url15);
                startActivity(intent);
                startActivity(intent);

            }
        });


        play16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, OpenGames.class);
                intent.putExtra("url1", url16);
                startActivity(intent);
                startActivity(intent);

            }
        });


    }
}
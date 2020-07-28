package com.example.forestadventures;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Locale;

public class MainActivity extends Activity {

    private ImageView HelloCharacter;
    private AnimationDrawable HelloAnimation;

    private ImageView OwlCharacter;
    private AnimationDrawable OwlAnimation;

    private Sound sound;
    MediaPlayer mediaPlayer;

    Button StartBtn;
    Button ScoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Locale.getDefault().getLanguage().equals("iw"))
        {
            RelativeLayout layout = findViewById(R.id.background_main);
            layout.setBackground(getResources().getDrawable(R.drawable.mainback_heb));
        }

        HelloCharacter = findViewById(R.id.character_hello);
        HelloAnimation = (AnimationDrawable) HelloCharacter.getDrawable();
        HelloAnimation.start();

        OwlCharacter = findViewById(R.id.owl_character);
        OwlAnimation = (AnimationDrawable) OwlCharacter.getDrawable();
        OwlAnimation.start();

        mediaPlayer = MediaPlayer.create(this, R.raw.mainsound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        StartBtn = findViewById(R.id.start_btn);
        ScoreBtn = findViewById(R.id.score_btn);

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);
            }
        });

        ScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent intent = new Intent(MainActivity.this, Scoreboard.class);
                startActivity(intent);
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.release();
        play();
    }

    protected void play(){
        mediaPlayer = MediaPlayer.create(this, R.raw.mainsound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    protected void stop(){
        mediaPlayer.pause();
        mediaPlayer.release();
    }
}

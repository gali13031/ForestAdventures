package com.example.forestadventures;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOver extends Activity {

    int[] scoreBoardPoints;
    String[] names;

    private Button okBtn;
    private EditText nameEt;
    private TextView scoreTv;

    int score;
    String name;
    MediaPlayer mediaPlayer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_layout);

        mediaPlayer = MediaPlayer.create(this, R.raw.gameover);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

        scoreTv = findViewById(R.id.score_tv);

        score = getIntent().getExtras().getInt("score");
        scoreTv.setText(getResources().getString(R.string.scoreResult_str) + " " + score);

        okBtn = findViewById(R.id.ok_btn);
        nameEt = findViewById(R.id.name_et);


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameEt.getText().toString().isEmpty()) // if entered a name
                {
                    name = nameEt.getText().toString();

                    SharedPreferences firstPlace = getSharedPreferences("FIRST_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences secondPlace = getSharedPreferences("SEC_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences thirdPlace = getSharedPreferences("THIRD_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences fourPlace = getSharedPreferences("FOUR_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences fivePlace = getSharedPreferences("FIVE_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences sixPlace = getSharedPreferences("SIX_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences sevenPlace = getSharedPreferences("SEVEN_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences eightPlace = getSharedPreferences("EIGHT_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences ninePlace = getSharedPreferences("NINE_PLACE", Context.MODE_PRIVATE);
                    SharedPreferences tenPlace = getSharedPreferences("TEN_PLACE", Context.MODE_PRIVATE);

                    SharedPreferences firstName = getSharedPreferences("FIRST_NAME", Context.MODE_PRIVATE);
                    SharedPreferences secondName = getSharedPreferences("SEC_NAME", Context.MODE_PRIVATE);
                    SharedPreferences thirdName = getSharedPreferences("THIRD_NAME", Context.MODE_PRIVATE);
                    SharedPreferences fourName = getSharedPreferences("FOUR_NAME", Context.MODE_PRIVATE);
                    SharedPreferences fiveName = getSharedPreferences("FIVE_NAME", Context.MODE_PRIVATE);
                    SharedPreferences sixName = getSharedPreferences("SIX_NAME", Context.MODE_PRIVATE);
                    SharedPreferences sevenName = getSharedPreferences("SEVEN_NAME", Context.MODE_PRIVATE);
                    SharedPreferences eightName = getSharedPreferences("EIGHT_NAME", Context.MODE_PRIVATE);
                    SharedPreferences nineName = getSharedPreferences("NINE_NAME", Context.MODE_PRIVATE);
                    SharedPreferences tenName = getSharedPreferences("TEN_NAME", Context.MODE_PRIVATE);

                    scoreBoardPoints = new int [10];
                    names = new String[10];

                    scoreBoardPoints[0] = firstPlace.getInt("FIRST_SCORE",0);
                    scoreBoardPoints[1] = secondPlace.getInt("SEC_SCORE",0);
                    scoreBoardPoints[2] = thirdPlace.getInt("THIRD_SCORE",0);
                    scoreBoardPoints[3] = fourPlace.getInt("FOUR_SCORE",0);
                    scoreBoardPoints[4] = fivePlace.getInt("FIVE_SCORE",0);
                    scoreBoardPoints[5] = sixPlace.getInt("SIX_SCORE",0);
                    scoreBoardPoints[6] = sevenPlace.getInt("SEVEN_SCORE",0);
                    scoreBoardPoints[7] = eightPlace.getInt("EIGHT_SCORE",0);
                    scoreBoardPoints[8] = ninePlace.getInt("NINE_SCORE",0);
                    scoreBoardPoints[9] = tenPlace.getInt("TEN_SCORE",0);

                    names[0] = firstName.getString("FIRST_NAME"," ");
                    names[1] = secondName.getString("SEC_NAME"," ");
                    names[2] = thirdName.getString("THIRD_NAME"," ");
                    names[3] = fourName.getString("FOUR_NAME"," ");
                    names[4] = fiveName.getString("FIVE_NAME"," ");
                    names[5] = sixName.getString("SIX_NAME"," ");
                    names[6] = sevenName.getString("SEVEN_NAME"," ");
                    names[7] = eightName.getString("EIGHT_NAME"," ");
                    names[8] = nineName.getString("NINE_NAME"," ");
                    names[9] = tenName.getString("TEN_NAME"," ");

                    if(score > scoreBoardPoints[9]) {

                        if (score >= scoreBoardPoints[0]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = scoreBoardPoints[6];
                            scoreBoardPoints[6] = scoreBoardPoints[5];
                            scoreBoardPoints[5] = scoreBoardPoints[4];
                            scoreBoardPoints[4] = scoreBoardPoints[3];
                            scoreBoardPoints[3] = scoreBoardPoints[2];
                            scoreBoardPoints[2] = scoreBoardPoints[1];
                            scoreBoardPoints[1] = scoreBoardPoints[0];
                            scoreBoardPoints[0] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = names[6];
                            names[6] = names[5];
                            names[5] = names[4];
                            names[4] = names[3];
                            names[3] = names[2];
                            names[2] = names[1];
                            names[1] = names[0];
                            names[0] = name;

                        } else if (score >= scoreBoardPoints[1]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = scoreBoardPoints[6];
                            scoreBoardPoints[6] = scoreBoardPoints[5];
                            scoreBoardPoints[5] = scoreBoardPoints[4];
                            scoreBoardPoints[4] = scoreBoardPoints[3];
                            scoreBoardPoints[3] = scoreBoardPoints[2];
                            scoreBoardPoints[2] = scoreBoardPoints[1];
                            scoreBoardPoints[1] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = names[6];
                            names[6] = names[5];
                            names[5] = names[4];
                            names[4] = names[3];
                            names[3] = names[2];
                            names[2] = names[1];
                            names[1] = name;

                        } else if (score >= scoreBoardPoints[2]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = scoreBoardPoints[6];
                            scoreBoardPoints[6] = scoreBoardPoints[5];
                            scoreBoardPoints[5] = scoreBoardPoints[4];
                            scoreBoardPoints[4] = scoreBoardPoints[3];
                            scoreBoardPoints[3] = scoreBoardPoints[2];
                            scoreBoardPoints[2] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = names[6];
                            names[6] = names[5];
                            names[5] = names[4];
                            names[4] = names[3];
                            names[3] = names[2];
                            names[2] = name;

                        } else if (score >= scoreBoardPoints[3]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = scoreBoardPoints[6];
                            scoreBoardPoints[6] = scoreBoardPoints[5];
                            scoreBoardPoints[5] = scoreBoardPoints[4];
                            scoreBoardPoints[4] = scoreBoardPoints[3];
                            scoreBoardPoints[3] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = names[6];
                            names[6] = names[5];
                            names[5] = names[4];
                            names[4] = names[3];
                            names[3] = name;

                        } else if (score >= scoreBoardPoints[4]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = scoreBoardPoints[6];
                            scoreBoardPoints[6] = scoreBoardPoints[5];
                            scoreBoardPoints[5] = scoreBoardPoints[4];
                            scoreBoardPoints[4] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = names[6];
                            names[6] = names[5];
                            names[5] = names[4];
                            names[4] = name;

                        } else if (score >= scoreBoardPoints[5]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = scoreBoardPoints[6];
                            scoreBoardPoints[6] = scoreBoardPoints[5];
                            scoreBoardPoints[5] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = names[6];
                            names[6] = names[5];
                            names[5] = name;

                        } else if (score >= scoreBoardPoints[6]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = scoreBoardPoints[6];
                            scoreBoardPoints[6] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = names[6];
                            names[6] = name;

                        } else if (score >= scoreBoardPoints[7]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = scoreBoardPoints[7];
                            scoreBoardPoints[7] = score;

                            names[9] = names[8];
                            names[8] = names[7];
                            names[7] = name;

                        } else if (score >= scoreBoardPoints[8]) {
                            scoreBoardPoints[9] = scoreBoardPoints[8];
                            scoreBoardPoints[8] = score;

                            names[9] = names[8];
                            names[8] = name;

                        } else {
                            names[9] = name;
                            names[9] = name;
                        }

                        SharedPreferences.Editor editor1 = firstPlace.edit();
                        SharedPreferences.Editor editor2 = secondPlace.edit();
                        SharedPreferences.Editor editor3 = thirdPlace.edit();
                        SharedPreferences.Editor editor4 = fourPlace.edit();
                        SharedPreferences.Editor editor5 = fivePlace.edit();
                        SharedPreferences.Editor editor6 = sixPlace.edit();
                        SharedPreferences.Editor editor7 = sevenPlace.edit();
                        SharedPreferences.Editor editor8 = eightPlace.edit();
                        SharedPreferences.Editor editor9 = ninePlace.edit();
                        SharedPreferences.Editor editor10 = tenPlace.edit();

                        editor1.putInt("FIRST_SCORE", scoreBoardPoints[0]);
                        editor1.commit();
                        editor2.putInt("SEC_SCORE", scoreBoardPoints[1]);
                        editor2.commit();
                        editor3.putInt("THIRD_SCORE", scoreBoardPoints[2]);
                        editor3.commit();
                        editor4.putInt("FOUR_SCORE", scoreBoardPoints[3]);
                        editor4.commit();
                        editor5.putInt("FIVE_SCORE", scoreBoardPoints[4]);
                        editor5.commit();
                        editor6.putInt("SIX_SCORE", scoreBoardPoints[5]);
                        editor6.commit();
                        editor7.putInt("SEVEN_SCORE", scoreBoardPoints[6]);
                        editor7.commit();
                        editor8.putInt("EIGHT_SCORE", scoreBoardPoints[7]);
                        editor8.commit();
                        editor9.putInt("NINE_SCORE", scoreBoardPoints[8]);
                        editor9.commit();
                        editor10.putInt("TEN_SCORE", scoreBoardPoints[9]);
                        editor10.commit();

                        SharedPreferences.Editor editor12 = firstName.edit();
                        SharedPreferences.Editor editor22 = secondName.edit();
                        SharedPreferences.Editor editor32 = thirdName.edit();
                        SharedPreferences.Editor editor42 = fourName.edit();
                        SharedPreferences.Editor editor52 = fiveName.edit();
                        SharedPreferences.Editor editor62 = sixName.edit();
                        SharedPreferences.Editor editor72 = sevenName.edit();
                        SharedPreferences.Editor editor82 = eightName.edit();
                        SharedPreferences.Editor editor92 = nineName.edit();
                        SharedPreferences.Editor editor102 = tenName.edit();

                        editor12.putString("FIRST_NAME", names[0]);
                        editor12.commit();
                        editor22.putString("SEC_NAME", names[1]);
                        editor22.commit();
                        editor32.putString("THIRD_NAME", names[2]);
                        editor32.commit();
                        editor42.putString("FOUR_NAME", names[3]);
                        editor42.commit();
                        editor52.putString("FIVE_NAME", names[4]);
                        editor52.commit();
                        editor62.putString("SIX_NAME", names[5]);
                        editor62.commit();
                        editor72.putString("SEVEN_NAME", names[6]);
                        editor72.commit();
                        editor82.putString("EIGHT_NAME", names[7]);
                        editor82.commit();
                        editor92.putString("NINE_NAME", names[8]);
                        editor92.commit();
                        editor102.putString("TEN_NAME", names[9]);
                        editor102.commit();
                    }
                    Intent intent = new Intent(GameOver.this, Scoreboard.class);
                    intent.putExtra("name", name);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }

                else { // didnt entered a name - make a toast
                    Toast.makeText(GameOver.this, getResources().getString(R.string.entername_str), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}

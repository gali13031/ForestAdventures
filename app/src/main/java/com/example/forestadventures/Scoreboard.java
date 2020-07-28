package com.example.forestadventures;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.Locale;

public class Scoreboard extends Activity {

    SharedPreferences firstPlace;
    SharedPreferences secondPlace;
    SharedPreferences thirdPlace;
    SharedPreferences fourPlace;
    SharedPreferences fivePlace;
    SharedPreferences sixPlace;
    SharedPreferences sevenPlace;
    SharedPreferences eightPlace;
    SharedPreferences ninePlace;
    SharedPreferences tenPlace;

    SharedPreferences firstName;
    SharedPreferences secondName;
    SharedPreferences thirdName;
    SharedPreferences fourName;
    SharedPreferences fiveName;
    SharedPreferences sixName;
    SharedPreferences sevenName;
    SharedPreferences eightName;
    SharedPreferences nineName;
    SharedPreferences tenName;

    String[] scoreBoard;
    String[] names;
    ListView scoreBoardView;

    Button homeBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard_layout);

        if(Locale.getDefault().getLanguage().equals("iw"))
        {
            RelativeLayout layout = findViewById(R.id.score_layout);
            layout.setBackground(getResources().getDrawable(R.drawable.scoreboard_heb));
        }

        homeBtn = findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scoreboard.this, MainActivity.class);
                startActivity(intent);
            }

        });

        firstPlace = getSharedPreferences("FIRST_PLACE", Context.MODE_PRIVATE);
        secondPlace = getSharedPreferences("SEC_PLACE", Context.MODE_PRIVATE);
        thirdPlace = getSharedPreferences("THIRD_PLACE", Context.MODE_PRIVATE);
        fourPlace = getSharedPreferences("FOUR_PLACE", Context.MODE_PRIVATE);
        fivePlace = getSharedPreferences("FIVE_PLACE", Context.MODE_PRIVATE);
        sixPlace = getSharedPreferences("SIX_PLACE", Context.MODE_PRIVATE);
        sevenPlace = getSharedPreferences("SEVEN_PLACE", Context.MODE_PRIVATE);
        eightPlace = getSharedPreferences("EIGHT_PLACE", Context.MODE_PRIVATE);
        ninePlace = getSharedPreferences("NINE_PLACE", Context.MODE_PRIVATE);
        tenPlace = getSharedPreferences("TEN_PLACE", Context.MODE_PRIVATE);

        firstName = getSharedPreferences("FIRST_NAME", Context.MODE_PRIVATE);
        secondName = getSharedPreferences("SEC_NAME", Context.MODE_PRIVATE);
        thirdName = getSharedPreferences("THIRD_NAME", Context.MODE_PRIVATE);
        fourName = getSharedPreferences("FOUR_NAME", Context.MODE_PRIVATE);
        fiveName = getSharedPreferences("FIVE_NAME", Context.MODE_PRIVATE);
        sixName = getSharedPreferences("SIX_NAME", Context.MODE_PRIVATE);
        sevenName = getSharedPreferences("SEVEN_NAME", Context.MODE_PRIVATE);
        eightName = getSharedPreferences("EIGHT_NAME", Context.MODE_PRIVATE);
        nineName = getSharedPreferences("NINE_NAME", Context.MODE_PRIVATE);
        tenName = getSharedPreferences("TEN_NAME", Context.MODE_PRIVATE);

        scoreBoard = new String[10];
        names = new String[10];

        scoreBoard[0] = firstPlace.getInt("FIRST_SCORE",0)+"";
        scoreBoard[1] = secondPlace.getInt("SEC_SCORE",0)+"";
        scoreBoard[2] = thirdPlace.getInt("THIRD_SCORE",0)+"";
        scoreBoard[3] = fourPlace.getInt("FOUR_SCORE",0)+"";
        scoreBoard[4] = fivePlace.getInt("FIVE_SCORE",0)+"";
        scoreBoard[5] = sixPlace.getInt("SIX_SCORE",0)+"";
        scoreBoard[6] = sevenPlace.getInt("SEVEN_SCORE",0)+"";
        scoreBoard[7] = eightPlace.getInt("EIGHT_SCORE",0)+"";
        scoreBoard[8] = ninePlace.getInt("NINE_SCORE",0)+"";
        scoreBoard[9] = tenPlace.getInt("TEN_SCORE",0)+"";

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

        scoreBoardView = findViewById(R.id.listview);
        GameAdapter adapter = new GameAdapter(this, names ,scoreBoard);
        scoreBoardView.setAdapter(adapter);
    }
}


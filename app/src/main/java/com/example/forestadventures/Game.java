package com.example.forestadventures;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.GONE;

public class Game extends Activity {

    ImageView backgroundOne;
    ImageView backgroundTwo;

    MediaPlayer mediaPlayer;
    MediaPlayer jumpSound;
    Sound sound;

    private Button LevelBtn;

    private ImageView MainCharacter;
    private AnimationDrawable MainCharacterAnimation;

    private ImageView Banana;
    private AnimationDrawable BananaAnimation;

    private ImageView GoldCoin;
    private AnimationDrawable GoldAnimation;

    private ImageView Water;
    private AnimationDrawable WaterAnimation;

    private ImageView Crow;
    private AnimationDrawable CrowAnimation;

    private ImageView Snake;
    private AnimationDrawable SnakeAnimation;

    private ImageView Bat;
    private AnimationDrawable BatAnimation;

    private ImageView LifePotion;

    private int chancesLeft = 3;

    //Hearts
    private ImageView HeartOne;
    private ImageView HeartTwo;
    private ImageView HeartThree;

    private Button pauseBtn;

    private boolean start_flg = false;
    private boolean pause_flg = false;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    private TextView scoreTV;

    //Screen
    private int screenWidth;
    private int screenHeight;
    private int frameHeight;

    //Speed
    private int playerSpeed;
    private int bananaSpeed;
    private int goldCoinSpeed;
    private int crowSpeed;
    private int snakeSpeed;
    private int batSpeed;
    private int waterSpeed;
    private int potionSpeed;

    private int playerY;

    //Points
    private int goldCoinX;
    private int goldCoinY;
    private int bananaX;
    private int bananaY;
    private int waterX;
    private int waterY;
    private int potionX;
    private int potionY;

    //Enemies
    private int crowX;
    private int crowY;
    private int snakeX;
    private int snakeY;
    private int batX;
    private int batY;

    private int score = 0;
    private boolean action_flg = false;
    private boolean isFlagLevelTwo = false;
    private boolean isFlagLevelThree = false;

    private int playerSize;
    private boolean isOver = true;
    private boolean isWin = true;
    private boolean isPotionGiven = false;

    private int goldPoints = 30;
    private int waterPoints = 20;
    private int bananaPoints = 25;

    private ValueAnimator animator;

    private boolean flagLevelTwo = false;
    private boolean flagLevelThree = false;

    ImageView backgroundFade;

    ImageView BrokenHeart;
    AlphaAnimation alphaAnimation;

    Button homeBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        mediaPlayer = MediaPlayer.create(this, R.raw.level_one);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        jumpSound = MediaPlayer.create(this, R.raw.jetpack_jump);

        LevelBtn = findViewById(R.id.level_btn);
        if(Locale.getDefault().getLanguage().equals("iw"))
        {
            LevelBtn.setBackgroundResource(R.drawable.levelone_heb);
        }
        else {
            LevelBtn.setBackgroundResource(R.drawable.levelone);
        }

        LevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start_flg == false) {
                    start_flg = true;

                    GoldCoin.setVisibility(View.VISIBLE);
                    Banana.setVisibility(View.VISIBLE);
                    Water.setVisibility(View.VISIBLE);
                    Snake.setVisibility(View.VISIBLE);
                    Crow.setVisibility(View.VISIBLE);
                    Bat.setVisibility(View.VISIBLE);
                    MainCharacter.setVisibility(View.VISIBLE);
                    MainCharacterAnimation.start();
                    CrowAnimation.start();
                    SnakeAnimation.start();
                    BatAnimation.start();
                    GoldAnimation.start();
                    WaterAnimation.start();
                    BananaAnimation.start();

                    FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
                    frameHeight = frame.getHeight();

                    playerY = (int) MainCharacter.getY();
                    playerSize = MainCharacter.getHeight();

                    LevelBtn.setVisibility(GONE);
                    animator.start();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    moveObjects();
                                }
                            });
                        }
                    }, 0, 20);
                }
            }
        });

        sound = new Sound(this);

        homeBtn = findViewById(R.id.home_button);

        backgroundOne = (ImageView) findViewById(R.id.background_one);
        backgroundTwo = (ImageView) findViewById(R.id.background_two);
        backgroundFade = (ImageView) findViewById(R.id.background_fade);
        backgroundFade.setVisibility(GONE);

        MainCharacter = findViewById(R.id.character_walking);
        MainCharacterAnimation = (AnimationDrawable) MainCharacter.getDrawable();

        Banana = findViewById(R.id.banana);
        BananaAnimation = (AnimationDrawable) Banana.getDrawable();

        GoldCoin = findViewById(R.id.gold_coin);
        GoldAnimation = (AnimationDrawable) GoldCoin.getDrawable();

        Water = findViewById(R.id.water);
        WaterAnimation = (AnimationDrawable) Water.getDrawable();

        Crow = findViewById(R.id.crow);
        CrowAnimation = (AnimationDrawable) Crow.getDrawable();

        Snake = findViewById(R.id.snake);
        SnakeAnimation = (AnimationDrawable) Snake.getDrawable();

        Bat = findViewById(R.id.bat);
        BatAnimation = (AnimationDrawable) Bat.getDrawable();

        scoreTV = (TextView) findViewById(R.id.score_tv);

        LifePotion = findViewById(R.id.life_potion);

        //FADE IN-OUT ANIMATION
        alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        HeartOne = findViewById(R.id.heart_one);
        HeartTwo = findViewById(R.id.heart_two);
        HeartThree = findViewById(R.id.heart_three);

        BrokenHeart = findViewById(R.id.broken_heart);
        BrokenHeart.setVisibility(GONE);

        animator = ValueAnimator.ofFloat(1.0f, 0.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });

        pauseBtn = findViewById(R.id.pause_btn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (start_flg) {
                    if (pause_flg == false) {

                        pause_flg = true;
                        timer.cancel();
                        timer = null;
                        animator.pause();
                        MainCharacterAnimation.stop();
                        jumpSound.pause();

                        pauseBtn.setBackgroundResource(R.drawable.playbutton);
                    } else {
                        pause_flg = false;
                        pauseBtn.setBackgroundResource(R.drawable.pausebutton);
                        animator.resume();
                        MainCharacterAnimation.start();
                        jumpSound.start();

                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        moveObjects();
                                    }
                                });
                            }
                        }, 0, 20);
                    }
                }
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeBtn.setEnabled(false);

                if(pause_flg == false) {
                    timer.cancel();
                    timer = null;
                    animator.pause();
                    MainCharacterAnimation.stop();

                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.exit_dialog, null);

                    AlertDialog.Builder alert = new AlertDialog.Builder(Game.this);
                    alert.setView(alertLayout);

                    alert.setCancelable(false);

                    alert.setPositiveButton(getResources().getString(R.string.yes_str), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Game.this, MainActivity.class);
                            startActivity(intent);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            finish();
                        }

                    });

                    alert.setNegativeButton(getResources().getString(R.string.no_str), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            moveObjects();
                                        }
                                    });
                                }
                            }, 0, 20);
                            animator.resume();
                            MainCharacterAnimation.start();
                        }

                    });

                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
                homeBtn.setEnabled(true);
            }
        });

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        playerSpeed = Math.round(screenHeight / 80);
        changeSpeed(150);

        initializeCharacters();

        scoreTV.setText(getResources().getString(R.string.score_str) + " 0");
    }


    public void initializeCharacters() {

        GoldCoin.setX(-85);
        GoldCoin.setY(-85);
        Banana.setX(-85);
        Banana.setY(-85);
        Water.setX(-85);
        Water.setY(-85);
        Crow.setX(-200);
        Crow.setY(-200);
        Snake.setX(-200);
        Snake.setY(-200);
        Bat.setX(-200);
        Bat.setY(-200);
        LifePotion.setX(-200);
        LifePotion.setY(-200);

        MainCharacterAnimation.stop();
        CrowAnimation.stop();
        SnakeAnimation.stop();
        BatAnimation.stop();
        GoldAnimation.stop();
        WaterAnimation.stop();
        BananaAnimation.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop(); //music

        if (pause_flg == false) {

            try {
                pause_flg = true;

                timer.cancel();
                timer = null;

                animator.pause();
                MainCharacterAnimation.stop();

                pauseBtn.setBackgroundResource(R.drawable.playbutton);

            } catch (Exception e) { }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.release();
        play();
    }

    protected void play(){
        if(isFlagLevelTwo){
            mediaPlayer = MediaPlayer.create(this, R.raw.level_two);
        }

        if(isFlagLevelThree){
            mediaPlayer = MediaPlayer.create(this, R.raw.level_three);
        }

        else {
            mediaPlayer = MediaPlayer.create(this, R.raw.level_one);
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    protected void stop(){
        mediaPlayer.pause();
        mediaPlayer.release();
    }

    public void moveObjects() {

        charcaterTouchObjetcts();

        goldCoinX -= goldCoinSpeed;
        if (goldCoinX < 0) {
            goldCoinX = screenWidth + 20;
            goldCoinY = (int) Math.floor(Math.random() * (frameHeight - GoldCoin.getHeight()));
        }
        GoldCoin.setX(goldCoinX);
        GoldCoin.setY(goldCoinY);


        bananaX -= bananaSpeed;
        if (bananaX < 0) {
            bananaX = screenWidth + 5000;
            bananaY = (int) Math.floor(Math.random() * (frameHeight - Banana.getHeight()));
        }
        Banana.setX(bananaX);
        Banana.setY(bananaY);


        waterX -= waterSpeed;
        if (waterX < 0) {
            waterX = screenWidth + 4000;
            waterY = (int) Math.floor(Math.random() * (frameHeight - Water.getHeight()));
        }
        Water.setX(waterX);
        Water.setY(waterY);


        crowX -= crowSpeed;
        if (crowX < 0) {
            crowX = screenWidth + 50;
            crowY = (int) Math.floor(Math.random() * (frameHeight - Crow.getHeight()));
        }
        Crow.setX(crowX);
        Crow.setY(crowY);


        if (isFlagLevelTwo) {
            snakeX -= snakeSpeed;
            if (snakeX < 0) {
                snakeX = screenWidth + 300;
                snakeY = (int) Math.floor(frameHeight - Snake.getHeight() + 30);
            }
            Snake.setX(snakeX);
            Snake.setY(snakeY);
        }


        if (isFlagLevelThree) {
            batX -= batSpeed;
            if (batX < 0) {
                batX = screenWidth + 10;
                batY = (int) Math.floor(Math.random() * (frameHeight - Bat.getHeight()));
            }
            Bat.setX(batX);
            Bat.setY(batY);
        }


        if (score >= 700) {
            if (flagLevelTwo == false) { // if we didn't started level 2 yet, then ...

                animator.end();
                jumpSound.pause();
                isFlagLevelTwo = true;

                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, R.raw.level_two);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();

                BrokenHeart.setVisibility(GONE);

                backgroundOne.setVisibility(GONE);
                backgroundTwo.setVisibility(GONE);

                homeBtn.setBackgroundResource(R.drawable.homebtn_two);


                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        backgroundFade.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        backgroundFade.setVisibility(GONE);

                        backgroundOne.setImageResource(R.drawable.two_background);
                        backgroundTwo.setImageResource(R.drawable.two_background);

                        backgroundOne.setVisibility(View.VISIBLE);
                        backgroundTwo.setVisibility(View.VISIBLE);

//                        animator.start();
                        if(Locale.getDefault().getLanguage().equals("iw"))
                        {
                            LevelBtn.setBackgroundResource(R.drawable.leveltwo_heb);
                        }
                        else {
                            LevelBtn.setBackgroundResource(R.drawable.leveltwo);
                        }
                        LevelBtn.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
                backgroundFade.startAnimation(alphaAnimation);

                start_flg = false;

                timer.cancel();
                timer = null;

                animator.pause();

                goldCoinX = -150;
                goldCoinY = -150;
                bananaX = -150;
                bananaY = -150;
                waterX = -150;
                waterY = -150;
                crowX = -1000;
                crowY = -1000;
                snakeX = -400;
                snakeY = -400;
                batX = -400;
                batY = -400;
                potionX = -400;
                potionY = -400;

                initializeCharacters();
                MainCharacter.setVisibility(GONE);

                flagLevelTwo = true;

                timer = new Timer();

                changeSpeed(130);
            }
        }


        if (score >= 1500) {
            if (flagLevelThree == false) // level 3
            {
                animator.end();
                jumpSound.pause();

                isFlagLevelThree = true;

                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, R.raw.level_three);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();

                BrokenHeart.setVisibility(GONE);

                backgroundOne.setVisibility(GONE);
                backgroundTwo.setVisibility(GONE);

                homeBtn.setBackgroundResource(R.drawable.homebtn_three);

                backgroundFade.setImageResource(R.drawable.two_background);

                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        backgroundFade.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        backgroundFade.setVisibility(GONE);

                        backgroundOne.setImageResource(R.drawable.three_background);
                        backgroundTwo.setImageResource(R.drawable.three_background);

                        backgroundOne.setVisibility(View.VISIBLE);
                        backgroundTwo.setVisibility(View.VISIBLE);

//                        animator.start();

                        if(Locale.getDefault().getLanguage().equals("iw"))
                        {
                            LevelBtn.setBackgroundResource(R.drawable.levelthree_heb);
                        }
                        else {
                            LevelBtn.setBackgroundResource(R.drawable.levelthree);
                        }
                        LevelBtn.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
                backgroundFade.startAnimation(alphaAnimation);

                start_flg = false;

                timer.cancel();
                timer = null;

                animator.pause();

                goldCoinX = -150;
                goldCoinY = -150;
                bananaX = -150;
                bananaY = -150;
                waterX = -150;
                waterY = -150;
                crowX = -1000;
                crowY = -1000;
                snakeX = -400;
                snakeY = -400;
                batX = -400;
                batY = -400;
                potionX = -400;
                potionY = -400;

                initializeCharacters();
                MainCharacter.setVisibility(GONE);

                flagLevelThree = true;

                timer = new Timer();

                changeSpeed(120);
            }
        }


        if (score >= 2000 && isWin == true) {
            isWin = false;
            Intent intent = new Intent(Game.this, WinningActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            finish();
        }

        // Move player
        if (action_flg == true) {
            // Touching
            playerY -= playerSpeed;

        } else {
            // Releasing
            playerY += playerSpeed;
        }

        // Check player position.
        if (playerY < 0)
            playerY = 0;

        if (playerY > frameHeight - playerSize)
            playerY = frameHeight - playerSize;

        MainCharacter.setY(playerY);


        scoreTV.setText(getResources().getString(R.string.score_str) + " " + score);
    }

    public void charcaterTouchObjetcts() {

        int goldCenterX = goldCoinX + GoldCoin.getWidth() / 2;
        int goldCenterY = goldCoinY + GoldCoin.getHeight() / 2;

        int waterCenterX = waterX + Water.getWidth() / 2;
        int waterCenterY = waterY + Water.getHeight() / 2;

        int bananaCenterX = bananaX + Banana.getWidth() / 2;
        int bananaCenterY = bananaY + Banana.getHeight() / 2;

        int crowCenterX = crowX + Crow.getWidth() / 2;
        int crowCenterY = crowY + Crow.getHeight() / 2;

        int snakeCenterX = snakeX + Snake.getWidth() / 2;
        int snakeCenterY = snakeY + Snake.getHeight() / 2;

        int batCenterX = batX + Bat.getWidth() / 2;
        int batCenterY = batY + Bat.getHeight() / 2;

        int potionCenterX = potionX + LifePotion.getWidth() / 2;
        int potionCenterY = potionY + LifePotion.getHeight() / 2;

        if (0 <= goldCenterX && goldCenterX <= playerSize &&
                playerY <= goldCenterY && goldCenterY <= playerY + playerSize) {

            score += goldPoints;
            goldCoinX = -10;

            sound.playEatSound();

        }

        if (0 <= waterCenterX && waterCenterX <= playerSize &&
                playerY <= waterCenterY && waterCenterY <= playerY + playerSize) {

            score += waterPoints;
            waterX = -10;

            sound.playEatSound();
        }

        if (0 <= bananaCenterX && bananaCenterX <= playerSize &&
                playerY <= bananaCenterY && bananaCenterY <= playerY + playerSize) {

            score += bananaPoints;
            bananaX = -10;

            sound.playEatSound();
        }

        if (HeartOne.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && HeartTwo.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && HeartThree.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && isPotionGiven == false) { //Give LOVE POTION

            potionX -= potionSpeed;
            if (potionX < 0) {
                potionX = screenWidth + 5;
                potionY = (int) Math.floor(Math.random() * (frameHeight - LifePotion.getHeight()));
            }
            LifePotion.setX(potionX);
            LifePotion.setY(potionY);

            if (0 <= potionCenterX && potionCenterX <= playerSize &&
                    playerY <= potionCenterY && potionCenterY <= playerY + playerSize) {

                sound.playLifeSound();
                HeartThree.setImageResource(R.drawable.heart);
                chancesLeft++;
                isPotionGiven = true;

                potionX = -10;
                potionX -= potionSpeed;
                if (potionX < 0) {
                    potionX = screenWidth + 5;
                    potionY = (int) Math.floor(Math.random() * (frameHeight - LifePotion.getHeight()));
                }
                LifePotion.setX(potionX);
                LifePotion.setY(potionY);
            }
        }

        if (0 <= crowCenterX && crowCenterX <= playerSize &&
                playerY <= crowCenterY && crowCenterY <= playerY + playerSize) {

            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    BrokenHeart.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    BrokenHeart.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }
            });
            BrokenHeart.startAnimation(alphaAnimation);
            checkHearts();
            crowX = -10;
            MainCharacter.startAnimation(alphaAnimation);

            sound.playHitSound();
        }

        if (0 <= snakeCenterX && snakeCenterX <= playerSize &&
                playerY <= snakeCenterY && snakeCenterY <= playerY + playerSize && score >= 700) {

            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    BrokenHeart.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    BrokenHeart.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }
            });
            BrokenHeart.startAnimation(alphaAnimation);
            checkHearts();
            snakeX = -10;
            MainCharacter.startAnimation(alphaAnimation);

            sound.playHitSound();
        }

        if (0 <= batCenterX && batCenterX <= playerSize &&
                playerY <= batCenterY && batCenterY <= playerY + playerSize && score >= 1500) {

            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    BrokenHeart.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    BrokenHeart.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }
            });
            BrokenHeart.startAnimation(alphaAnimation);
            checkHearts();
            batX = -10;
            MainCharacter.startAnimation(alphaAnimation);

            sound.playHitSound();
        }
    }

    public void checkHearts() {
        if (HeartOne.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.heart).getConstantState())
        {
            HeartOne.setImageResource(R.drawable.grayheart); // if the first heart appears
            chancesLeft--; //down to 3
        }

        else if (HeartTwo.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.heart).getConstantState())
        {
            HeartTwo.setImageResource(R.drawable.grayheart); // if the second heart appears
            chancesLeft--; //down to 2

        }
        else if (HeartThree.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.heart).getConstantState())
        {
            HeartThree.setImageResource(R.drawable.grayheart); // if the third heart appears
            chancesLeft--; //down to 1
        }

        else if (HeartOne.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && HeartTwo.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && HeartThree.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && chancesLeft == 1)
        { // if all of the hearts dont appear, Give LOVE POTION
            chancesLeft--; // down to 0
        }

        else if (HeartOne.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && HeartTwo.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && HeartThree.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.grayheart).getConstantState()
                && chancesLeft <= 0 && isOver == true)
        {
            isOver = false;
            Intent intent = new Intent(Game.this, GameOver.class);
            intent.putExtra("score", score);
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            finish();
        }
    }

    public boolean onTouchEvent(MotionEvent me) {
        if (start_flg && pauseBtn.getBackground().getConstantState() == getResources().getDrawable(R.drawable.pausebutton).getConstantState()) {
            if (me.getAction() == MotionEvent.ACTION_DOWN) { // Pressing with finger
                action_flg = true;
                MainCharacter.setImageDrawable(getResources().getDrawable(R.drawable.jumpingcharacterfire));
                jumpSound.setLooping(true);
                jumpSound.start();

            } else if (me.getAction() == MotionEvent.ACTION_UP) { // Releasing finger
                action_flg = false;
                jumpSound.pause();
                MainCharacter.setImageDrawable(getResources().getDrawable(R.drawable.walking_character));
                MainCharacterAnimation = (AnimationDrawable) MainCharacter.getDrawable();
                MainCharacterAnimation.start();
            }
        }
        return true;
    }

    public void changeSpeed(int speed) {
        bananaSpeed = Math.round(screenWidth / speed);
        goldCoinSpeed = Math.round(screenWidth / speed);
        waterSpeed = Math.round(screenWidth / speed);
        crowSpeed = Math.round(screenWidth / speed);
        snakeSpeed = Math.round(screenWidth / speed);
        batSpeed = Math.round(screenWidth / speed);
        potionSpeed = Math.round(screenWidth / speed);
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
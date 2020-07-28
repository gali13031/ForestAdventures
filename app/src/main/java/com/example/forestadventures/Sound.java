package com.example.forestadventures;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class Sound {

    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;

    private static SoundPool soundBackground;
    private static int hitSound;
    private static int foodSound;
    private static int jumpSound;
    private static int lifeSound;


    public Sound(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundBackground = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();

        }
        else {
            soundBackground = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        }

        hitSound = soundBackground.load(context, R.raw.hit, 1);
        foodSound = soundBackground.load(context, R.raw.eat, 1);
        jumpSound = soundBackground.load(context, R.raw.jetpack_sound, 1);
        lifeSound = soundBackground.load(context, R.raw.lifepotion, 1);

    }

    public void playHitSound() {
        soundBackground.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playEatSound() {
        soundBackground.play(foodSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playJumpSound() {
        soundBackground.play(jumpSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playLifeSound() {
        soundBackground.play(lifeSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}

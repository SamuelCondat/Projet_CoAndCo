package com.corp.tajine.la_cacalculatrice;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import static android.content.ContentValues.TAG;

public class EnAvantLaMusique {

    private MediaPlayer mediaPlayer;
    private String typeCalc;

    public EnAvantLaMusique(MediaPlayer mediaPlayer, String typeCalc) {
        this.mediaPlayer = mediaPlayer;
        this.typeCalc = typeCalc;
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void play(String tag, Context context) {

        if (typeCalc == "marron") {
            switch (tag) {
                case "0":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_0);
                    break;
                case "1":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_1);
                    break;
                case "2":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_2);
                    break;
                case "3":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_3);
                    break;
                case "4":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_4);
                    break;
                case "5":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_5);
                    break;
                case "6":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_6);
                    break;
                case "7":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_7);
                    break;
                case "8":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_8);
                    break;
                case "9":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_9);
                    break;
                case "/":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_div);
                    break;
                case "x":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_mult);
                    break;
                case "+":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_plus);
                    break;
                case "-":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_moins);
                    break;
                case ".":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_point);
                    break;
                case "=":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fartz_numeric);
                    break;
                case "C":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_cancel);
                    break;
                case "caca":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_caca);
                    break;
                case "trophy":
                    mediaPlayer = MediaPlayer.create(context, R.raw.fart_troph);
                    break;
            }
        } else if (typeCalc == "standard") {
            mediaPlayer = MediaPlayer.create(context, R.raw.tap);
        }
        mediaPlayer.start();
    }
}





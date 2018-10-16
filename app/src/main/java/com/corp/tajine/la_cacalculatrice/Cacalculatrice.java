package com.corp.tajine.la_cacalculatrice;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;

public class Cacalculatrice extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacalculatrice);
        getSupportActionBar().hide();
        MediaPlayer mediaPlayer = new MediaPlayer();
        String typeCalc = "marron";
        magique = new EnAvantLaMusique(mediaPlayer, typeCalc);

        btn0 = (ImageButton) findViewById(R.id.btn_0);
        btn1 = (ImageButton) findViewById(R.id.btn_1);
        btn2 = (ImageButton) findViewById(R.id.btn_2);
        btn3 = (ImageButton) findViewById(R.id.btn_3);
        btn4 = (ImageButton) findViewById(R.id.btn_4);
        btn5 = (ImageButton) findViewById(R.id.btn_5);
        btn6 = (ImageButton) findViewById(R.id.btn_6);
        btn7 = (ImageButton) findViewById(R.id.btn_7);
        btn8 = (ImageButton) findViewById(R.id.btn_8);
        btn9 = (ImageButton) findViewById(R.id.btn_9);
        btnDot = (ImageButton) findViewById(R.id.btn_dot);
        btnDiv = (ImageButton) findViewById(R.id.btn_div);
        btnMult = (ImageButton) findViewById(R.id.btn_mult);
        btnPlus = (ImageButton) findViewById(R.id.btn_plus);
        btnMinus = (ImageButton) findViewById(R.id.btn_minus);
        btnCancel = (ImageButton) findViewById(R.id.btn_cancel);
        btnEqual = (ImageButton) findViewById(R.id.btn_equal);
        btnTrophy = (ImageButton) findViewById(R.id.btn_trophy);
        btnCaca = (ImageButton) findViewById(R.id.btn_caca);
        ligne1 = (LinearLayout) findViewById(R.id.affichage1);
        ligne2 = (LinearLayout) findViewById(R.id.affichage2);


        btn0.setOnTouchListener(numberButtonListener);
        btn1.setOnTouchListener(numberButtonListener);
        btn2.setOnTouchListener(numberButtonListener);
        btn3.setOnTouchListener(numberButtonListener);
        btn4.setOnTouchListener(numberButtonListener);
        btn5.setOnTouchListener(numberButtonListener);
        btn6.setOnTouchListener(numberButtonListener);
        btn7.setOnTouchListener(numberButtonListener);
        btn8.setOnTouchListener(numberButtonListener);
        btn9.setOnTouchListener(numberButtonListener);
        btnDot.setOnTouchListener(dotButtonListener);
        btnDiv.setOnTouchListener(operatorButtonListener);
        btnMult.setOnTouchListener(operatorButtonListener);
        btnPlus.setOnTouchListener(operatorButtonListener);
        btnMinus.setOnTouchListener(operatorButtonListener);
        btnCancel.setOnTouchListener(cancelButtonListener);
        btnEqual.setOnTouchListener(equalButtonListener);
        btnTrophy.setOnTouchListener(this);
        btnCaca.setOnTouchListener(this);

        displayResult(aAfficherLigne2, 2);
        //lign2.setText(ligneResult);
        //    Log.i("______ligneResult_OC_", ligneResult);

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Context context = getApplicationContext();
        String btnVal = (String) v.getTag();

        switch (v.getId()){
            case R.id.btn_trophy :
                intent = new Intent(this, ViewCalculActivity.class);
                bouton = btnTrophy;
                pathBtnNormal = R.drawable.btn_trophee_caca;
                pathBtnPush = R.drawable.btn_trophee1;
                break;
            case R.id.btn_caca :
                intent = new Intent(this, Cacalculatrice.class);
                bouton = btnCaca;
                pathBtnNormal = R.drawable.btn_caca_caca;
                pathBtnPush = R.drawable.btn_caca_push;
                break;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            magique.stop();
            magique.play(btnVal, context);
            //On lance directement l'activité ViewCalcul(historique) plutôt que le sommaire, en attendant d'éttofer l'apply

            //On lance directement l'activité Cacalculatrice plutôt que le sommaire, en attendant d'éttofer l'apply
            bouton.setBackgroundResource(pathBtnNormal);

        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //On lance directement l'activité ViewCalcul(historique) plutôt que le sommaire, en attendant d'éttofer l'apply
            bouton.setBackgroundResource(pathBtnPush);
            if (v.getId() == R.id.btn_trophy ){
                startActivity(intent);
            } else {
                finish();
            }
        }
        return true;
    }
    ///////////////////////////////
    //Bouton listener pour nombres
    ///////////////////////////////

    protected View.OnTouchListener numberButtonListener = new View.OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {

            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();

            switch(btnVal) {
                case "0" : bouton = btn0;
                    pathBtnNormal = R.drawable.btn_0_caca;
                    pathBtnPush = R.drawable.btn_0_push;
                    break;
                case "1" : bouton = btn1;
                    pathBtnNormal = R.drawable.btn_1_caca;
                    pathBtnPush = R.drawable.btn_1_push;
                    break;
                case "2" : bouton = btn2;
                    pathBtnNormal = R.drawable.btn_2_caca;
                    pathBtnPush = R.drawable.btn_2_push;
                    break;
                case "3" : bouton = btn3;
                    pathBtnNormal = R.drawable.btn_3_caca;
                    pathBtnPush = R.drawable.btn_3_push;
                    break;
                case "4" : bouton = btn4;
                    pathBtnNormal = R.drawable.btn_4_caca;
                    pathBtnPush = R.drawable.btn_4_push;
                    break;
                case "5" : bouton = btn5;
                    pathBtnNormal = R.drawable.btn_5_caca;
                    pathBtnPush = R.drawable.btn_5_push;
                    break;
                case "6" : bouton = btn6;
                    pathBtnNormal = R.drawable.btn_6_caca;
                    pathBtnPush = R.drawable.btn_6_push;
                    break;
                case "7" : bouton = btn7;
                    pathBtnNormal = R.drawable.btn_7_caca;
                    pathBtnPush = R.drawable.btn_7_push;
                    break;
                case "8" : bouton = btn8;
                    pathBtnNormal = R.drawable.btn_8_caca;
                    pathBtnPush = R.drawable.btn_8_push;
                    break;
                case "9" : bouton = btn9;
                    pathBtnNormal = R.drawable.btn_9_caca;
                    pathBtnPush = R.drawable.btn_9_push;
                    break;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {

                if ((btnVal.equals("0")) && (aAfficherLigne2.equals("0"))) {
                } else {
                    aAfficherLigne2 = newVal(aAfficherLigne2, btnVal);
                }
                displayResult(aAfficherLigne2, 2);

                bouton.setBackgroundResource(pathBtnNormal);

            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                magique.stop();
                magique.play(btnVal, context);
                bouton.setBackgroundResource(pathBtnPush);
            }
            return true;
        }
    };

    ///////////////////////////////
    //Bouton listener pour virgule
    //////////////////////////////
    protected View.OnTouchListener dotButtonListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();
            bouton = btnDot;
            Log.i(TAG, "onTouch: ______________" + btnVal +"-------------------");

            if (event.getAction() == MotionEvent.ACTION_UP) {
                Log.i(TAG, "onTouch: _____RELACHEY");
                magique.stop();
                magique.play(btnVal, context);


                aAfficherLigne2 = newVal(aAfficherLigne2, btnVal);
                displayResult(aAfficherLigne2, 2);
                //   Log.i("______ligneResult___BL", ligneResult);
                //   Log.i("______ligneCalcul_____", ligneCalcul);
                //   Log.i("_________total________", total);

                bouton.setBackgroundResource(R.drawable.btn_point_caca);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.i(TAG, "onTouch: _____TOUCHEY");
                magique.stop();
                magique.play(btnVal, context);
                bouton.setBackgroundResource(R.drawable.btn_point_push);
                return true;
            }
            return true;
        }
    };
    /////////////////////////////////
    //Bouton listener pour opérateurs
    /////////////////////////////////
    protected View.OnTouchListener operatorButtonListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();

            switch(btnVal) {
                case "+":
                    bouton = btnPlus;
                    pathBtnNormal = R.drawable.btn_plus_caca;
                    pathBtnPush = R.drawable.btn_plus_push;
                    break;
                case "-":
                    bouton = btnMinus;
                    pathBtnNormal = R.drawable.btn_moins_caca;
                    pathBtnPush = R.drawable.btn_moins_push;
                    break;
                case "x":
                    bouton = btnMult;
                    pathBtnNormal = R.drawable.btn_mult_caca;
                    pathBtnPush = R.drawable.btn_mult_push;
                    break;
                case "/":
                    bouton = btnDiv;
                    pathBtnNormal = R.drawable.btn_div_caca;
                    pathBtnPush = R.drawable.btn_div_push;
                    break;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                magique.stop();
                magique.play(btnVal, context);
                bouton.setBackgroundResource(pathBtnNormal);
                //   Log.i("______ligneResult_LA__", ligneResult);
          /*  if (ligneResult.equals("")){
                ligneResult = "0";
                newNumber = true;
            }*/


                    aAfficherLigne1 = aAfficherLigne1 + aAfficherLigne2 + btnVal;
                    total = calc(total, operateur, aAfficherLigne2);
                    displayResult(total, 2);


           /* //if(!ligneCalcul.equals("")) {
            String dernierChar = ligneCalcul;
            //ligneCalcul.substring(ligneCalcul.length() - 1);
            Log.i("Dernier char______", dernierChar);
            //}*/
                //     Log.i("______ligneResult___la", ligneResult);
                //     Log.i("______ligneCalcul___la", ligneCalcul);
                //     Log.i("_________total_______la", total);
                operateur = btnVal;
                displayResult(aAfficherLigne1, 1);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.i(TAG, "onTouch: _____TOUCHEY");
                bouton.setBackgroundResource(pathBtnPush);
                return true;
            }
            return true;
        }
    };

    ///////////////////////////
    //Bouton listener pour égal
    ///////////////////////////
    protected View.OnTouchListener equalButtonListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {

            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();
            bouton = btnEqual;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                magique.stop();
                magique.play(btnVal, context);
                bouton.setBackgroundResource(R.drawable.btn_egal_caca);

                total = calc(total, operateur, aAfficherLigne2);
                displayResult(total, 2);
                displayResult(" ", 1);
                String newCalcul = aAfficherLigne1 + aAfficherLigne2;
                insertCalcul(newCalcul, total, 0);
                aAfficherLigne2 = "0";
                aAfficherLigne1 = "";
                total = "";

                //       Log.i("______ligneResult_____", ligneResult);
                //     Log.i("______ligneCalcul_____", ligneCalcul);
                //     Log.i("_________total________", total);
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                bouton.setBackgroundResource(R.drawable.btn_egal_push);
            }
            return true;
        }
    };

    //////////////////////////////
    //Bouton listener pour effacer
    //////////////////////////////
    protected View.OnTouchListener cancelButtonListener = new View.OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {

            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();
            bouton = btnCancel;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                magique.stop();
                magique.play(btnVal, context);
                aAfficherLigne2 = "0";
                aAfficherLigne1 = "";
                total = "";
                displayResult(aAfficherLigne1, 1);
                displayResult(aAfficherLigne2, 2);

                bouton.setBackgroundResource(R.drawable.btn_cancel_caca);

            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                bouton.setBackgroundResource(R.drawable.btn_cancel_push);
            }
            return true;
        }
    };
}

package com.corp.tajine.la_cacalculatrice;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;

import static android.content.ContentValues.TAG;

/////////////////////////
//Création des variables
/////////////////////////

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    static Intent intent;
    static ImageButton bouton;
    static Integer pathBtnNormal;
    static Integer pathBtnPush;
    static String retourNewVal;
    static String dernierCaractereAAfficher1;
    static String dernierCaractereAAfficher2;
    static Boolean numLigne2Gardey = false;
    static BigDecimal resultatBD = new BigDecimal("0.0");

    boolean isFloat = false;
    String aAfficherLigne1 = "";
    String aAfficherLigne2 = "0";
    String total = "";
    String operateur = null;
    ImageButton btn0 = null;
    ImageButton btn1 = null;
    ImageButton btn2 = null;
    ImageButton btn3 = null;
    ImageButton btn4 = null;
    ImageButton btn5 = null;
    ImageButton btn6 = null;
    ImageButton btn7 = null;
    ImageButton btn8 = null;
    ImageButton btn9 = null;
    ImageButton btnDot = null;
    ImageButton btnDiv = null;
    ImageButton btnMult = null;
    ImageButton btnPlus = null;
    ImageButton btnMinus = null;
    ImageButton btnCancel = null;
    ImageButton btnEqual = null;
    ImageButton btnTrophy = null;
    ImageButton btnCaca = null;
    LinearLayout ligne1 = null;
    LinearLayout ligne2 = null;

    //////////////////////////////////////////////////////////////
    //Attribution d'une vue pour les boutons et pour les résultats
    //////////////////////////////////////////////////////////////
    EnAvantLaMusique magique;

    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ON EST DANS : ", "||||onCreate|||");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        String typeCalc = "standard";
        MediaPlayer mediaPlayer = new MediaPlayer();
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
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("ON EST DANS : ", "||||trophy/caca listener|||");
        Context context = getApplicationContext();
        String btnVal = (String) v.getTag();

        switch (v.getId()) {
            case R.id.btn_trophy:
                intent = new Intent(this, ViewCalculActivity.class);
                bouton = btnTrophy;
                pathBtnNormal = R.drawable.btn_trophee;
                pathBtnPush = R.drawable.btn_trophee1;
                break;
            case R.id.btn_caca:
                intent = new Intent(this, Cacalculatrice.class);
                bouton = btnCaca;
                pathBtnNormal = R.drawable.btn_caca;
                pathBtnPush = R.drawable.btn_caca_push;
                break;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            magique.stop();
            magique.play(btnVal, context);
            bouton.setBackgroundResource(pathBtnNormal);

        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            bouton.setBackgroundResource(pathBtnPush);
            startActivity(intent);
        }
        return true;
    }

    ///////////////////////////////
    //Bouton listener pour nombres
    ///////////////////////////////
    protected View.OnTouchListener numberButtonListener = new View.OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {
            Log.i("ON EST DANS : ", "||||numberButtonListener|||");

            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();

            switch (btnVal) {
                case "0":
                    bouton = btn0;
                    pathBtnNormal = R.drawable.btn_0;
                    pathBtnPush = R.drawable.btn_0_push;
                    break;
                case "1":
                    bouton = btn1;
                    pathBtnNormal = R.drawable.btn_1;
                    pathBtnPush = R.drawable.btn_1_push;
                    break;
                case "2":
                    bouton = btn2;
                    pathBtnNormal = R.drawable.btn_2;
                    pathBtnPush = R.drawable.btn_2_push;
                    break;
                case "3":
                    bouton = btn3;
                    pathBtnNormal = R.drawable.btn_3;
                    pathBtnPush = R.drawable.btn_3_push;
                    break;
                case "4":
                    bouton = btn4;
                    pathBtnNormal = R.drawable.btn_4;
                    pathBtnPush = R.drawable.btn_4_push;
                    break;
                case "5":
                    bouton = btn5;
                    pathBtnNormal = R.drawable.btn_5;
                    pathBtnPush = R.drawable.btn_5_push;
                    break;
                case "6":
                    bouton = btn6;
                    pathBtnNormal = R.drawable.btn_6;
                    pathBtnPush = R.drawable.btn_6_push;
                    break;
                case "7":
                    bouton = btn7;
                    pathBtnNormal = R.drawable.btn_7;
                    pathBtnPush = R.drawable.btn_7_push;
                    break;
                case "8":
                    bouton = btn8;
                    pathBtnNormal = R.drawable.btn_8;
                    pathBtnPush = R.drawable.btn_8_push;
                    break;
                case "9":
                    bouton = btn9;
                    pathBtnNormal = R.drawable.btn_9;
                    pathBtnPush = R.drawable.btn_9_push;
                    break;
            }
            Log.i(TAG, "|||||||||||||| Number listener aAfficherLigne2 " + aAfficherLigne2);
            if (event.getAction() == MotionEvent.ACTION_UP) {
                bouton.setBackgroundResource(pathBtnNormal);

            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                bouton.setBackgroundResource(pathBtnPush);
                magique.stop();
                magique.play(btnVal, context);

                if (numLigne2Gardey == true) {
                    aAfficherLigne2 = newVal("0", btnVal);
                } else {
                    aAfficherLigne2 = newVal(aAfficherLigne2, btnVal);
                }
            }
            displayResult(aAfficherLigne2, 2);
            Log.i(TAG, " Number listener aAfficherLigne2 " + aAfficherLigne2);
            numLigne2Gardey = false;
            return true;
        }
    };

    ///////////////////////////////
    //Bouton listener pour virgule
    //////////////////////////////
    protected View.OnTouchListener dotButtonListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            Log.i("ON EST DANS : ", "||||dotButtonListener|||");
            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();
            bouton = btnDot;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                bouton.setBackgroundResource(R.drawable.btn_point);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                magique.stop();
                magique.play(btnVal, context);
                bouton.setBackgroundResource(R.drawable.btn_point_push);

                aAfficherLigne2 = newVal(aAfficherLigne2, btnVal);
                displayResult(aAfficherLigne2, 2);
                Log.i(TAG, "|||||||||||||||||||||| Number listener aAfficherLigne2 " + aAfficherLigne2);

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
            Log.i("ON EST DANS : ", "||||operatorButtonListener|||");
            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();

            switch (btnVal) {
                case "+":
                    bouton = btnPlus;
                    pathBtnNormal = R.drawable.btn_plus;
                    pathBtnPush = R.drawable.btn_plus_push;
                    break;
                case "-":
                    bouton = btnMinus;
                    pathBtnNormal = R.drawable.btn_moins;
                    pathBtnPush = R.drawable.btn_moins_push;
                    break;
                case "x":
                    bouton = btnMult;
                    pathBtnNormal = R.drawable.btn_mult;
                    pathBtnPush = R.drawable.btn_mult_push;
                    break;
                case "/":
                    bouton = btnDiv;
                    pathBtnNormal = R.drawable.btn_div;
                    pathBtnPush = R.drawable.btn_div_push;
                    break;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                bouton.setBackgroundResource(pathBtnNormal);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                magique.stop();
                magique.play(btnVal, context);
                bouton.setBackgroundResource(pathBtnPush);

                Log.i("____aAfficherLigne1___", aAfficherLigne1);
                Log.i("____aAfficherLigne2___", aAfficherLigne2);

                operateur = btnVal;

                int length1 = aAfficherLigne1.length();
                Log.i("____length1___", String.valueOf(length1));
                int length2 = aAfficherLigne2.length();
                Log.i("____length2___", String.valueOf(length2));
                try {
                    dernierCaractereAAfficher1 = aAfficherLigne1.substring(length1 - 1, length1);
                    Log.i("LastCharAAfficher1 = ", dernierCaractereAAfficher1);
                }catch (IndexOutOfBoundsException e){
                    Log.i("IndexOutBoundException ", String.valueOf(e));
                    dernierCaractereAAfficher1 = "";
                }
                try {
                    dernierCaractereAAfficher2 = aAfficherLigne2.substring(length2 - 1, length2);
                    Log.i("LastCharAAfficher2 = ", dernierCaractereAAfficher2);
                }catch (IndexOutOfBoundsException e){
                    Log.i("IndexOutBoundException ", " [2] "+String.valueOf(e));
                    dernierCaractereAAfficher2 = "";
                }


                Log.i("____Avant if______ ", "aAfficherLigne1");

                if (aAfficherLigne1.equals("") && dernierCaractereAAfficher2.equals(".")) {
                    Log.i("___rentre dans if___ ", "equals '' && lastchar equals '.'");

                    aAfficherLigne2 = aAfficherLigne2.substring(0, length2 - 1);
                    aAfficherLigne1 = aAfficherLigne2 + operateur;
                    total = aAfficherLigne2;
                    displayResult(aAfficherLigne2, 2);
                    displayResult(aAfficherLigne1, 1);
                    numLigne2Gardey = true;
                    Log.i("__on a display on sort_", " de if");
                }else if (aAfficherLigne1.equals("")) {
                    Log.i("___rentre dans if___ ", "equals '' ");
                    aAfficherLigne1 = aAfficherLigne2 + operateur;
                    total = aAfficherLigne2;
                    numLigne2Gardey = true;
                    displayResult(aAfficherLigne1, 1);
                    Log.i("__on a display on sort_", " de if");
                } else if (((dernierCaractereAAfficher1.equals("+")) || (dernierCaractereAAfficher1.equals("x")) || (dernierCaractereAAfficher1.equals("/")) || (dernierCaractereAAfficher1.equals("-"))) && numLigne2Gardey == true) {
                    Log.i("_rentre dans else if _", " equals + x / - && TRUE");
                    Log.i("_______avant_____", aAfficherLigne1);
                    aAfficherLigne1 = aAfficherLigne1.substring(0, length1 - 1) + operateur;
                    Log.i("_______après_____", aAfficherLigne1);
                    displayResult(total, 2);
                    displayResult(aAfficherLigne1, 1);
                    Log.i("_on a display on sort_", " equals + x / - && TRUE");
                } else if (((dernierCaractereAAfficher1.equals("+")) || (dernierCaractereAAfficher1.equals("x")) || (dernierCaractereAAfficher1.equals("/")) || (dernierCaractereAAfficher1.equals("-"))) && numLigne2Gardey == false) {
                    Log.i("_rentre dans else if _", " equals + x / -   && FALSE");
                    Log.i("_______avant_____", aAfficherLigne1);
                    aAfficherLigne1 = aAfficherLigne1 + aAfficherLigne2 + operateur;
                    Log.i("_______après_____", aAfficherLigne1);

                    displayResult(aAfficherLigne1, 1);
                    Log.i("_on a display on sort_", " equals + x / - && FALSE");
                }else if (dernierCaractereAAfficher2.equals(".")){
                    Log.i("_rentre dans else if ", " equals '.' ");
                    Log.i("________________", "BRAVO CHAMPION§§§!!!");
                } else {
                    //s'il y a déjà du monde dans aAfficher1
                    Log.i("________________", "5");

                }

                //aAfficherLigne1.substring(aAfficherLigne1.length() - 1);
                Log.i("________________", "6");
                displayResult(aAfficherLigne1, 1);
                return true;
            }
            Log.i("________________", "7");
            return true;
        }

    };

    ///////////////////////////
    //Bouton listener pour égal
    ///////////////////////////
    protected View.OnTouchListener equalButtonListener = new View.OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {
            Log.i("ON EST DANS : ", "||||equalButtonListener|||");
            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();
            bouton = btnEqual;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                bouton.setBackgroundResource(R.drawable.btn_egal);

            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                bouton.setBackgroundResource(R.drawable.btn_egal_push);
                magique.stop();
                magique.play(btnVal, context);

                total = calc(total, operateur, aAfficherLigne2);
                Log.i("_____Equal_____TOTAL = ", total);
                displayResult(total, 2);
                displayResult(" ", 1);
                String newCalcul = aAfficherLigne1 + aAfficherLigne2;
                insertCalcul(newCalcul, total, 0);
                aAfficherLigne2 = "0";
                aAfficherLigne1 = "";
                total = "";

                //       Log.i("______aAfficherLigne2_____", aAfficherLigne2);
                //     Log.i("______aAfficherLigne1_____", aAfficherLigne1);
                //     Log.i("_________total________", total);
            }
            return true;
        }
    };

    protected void insertCalcul(String calc, String result, long mode) {
        Log.i("ON EST DANS : ", "||||protected void insertCalcul|||");
        Calcul calcul = new Calcul(calc, result, 0);
        calcul.insert(this);
    }

    //////////////////////////////
    //Bouton listener pour effacer
    //////////////////////////////
    protected View.OnTouchListener cancelButtonListener = new View.OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {
            Log.i("ON EST DANS : ", "||||cancelButtonListener|||");
            Context context = getApplicationContext();
            String btnVal = (String) v.getTag();
            bouton = btnCancel;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                magique.stop();
                magique.play(btnVal, context);
                //       displayResult("", 2);
                //       displayResult("", 1);
                isFloat = false;
                aAfficherLigne2 = "0";
                aAfficherLigne1 = "";
                total = "";
                displayResult(aAfficherLigne1, 1);
                displayResult(aAfficherLigne2, 2);
                bouton.setBackgroundResource(R.drawable.btn_cancel);

            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                bouton.setBackgroundResource(R.drawable.btn_cancel_push);
            }
            return true;
        }
    };

    //////////////////////////////////////////////////////////////////////////
    //Attribution à une ligne d'une image "number" en fonction de "lignNumber"
    //////////////////////////////////////////////////////////////////////////
    protected void displayResult(String number, int lignNumber) {
        Log.i("ON EST DANS : ", "||||protected void displayResult|||");
        if (lignNumber == 1) {
            addImg(number, ligne1);
            //ligne1.setText(number);
        } else if (lignNumber == 2) {
            addImg(number, ligne2);
            //ligne2.setText(number);
        }
        scroll();
    }

    //////////////////////////////
    //Affichage du "text" en image
    //////////////////////////////
    protected void addImg(String text, LinearLayout lign) {
        Log.i("ON EST DANS : ", "||||protected void addImg|||");
        lign.removeAllViews();
        Integer ilyadejaunpoint = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            switch (ch) {
                case ' ':
                    lign.removeAllViewsInLayout();
                    break;
                case '0':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_0, null));
                    break;
                case '1':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_1, null));
                    break;
                case '2':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_2, null));
                    break;
                case '3':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_3, null));
                    break;
                case '4':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_4, null));
                    break;
                case '5':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_5, null));
                    break;
                case '6':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_6, null));
                    break;
                case '7':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_7, null));
                    break;
                case '8':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_8, null));
                    break;
                case '9':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_9, null));
                    break;
                case '/':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_div, null));
                    break;
                case 'E':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_expo, null));
                    break;
                case '-':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_moins, null));
                    break;
                case 'x':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_mult, null));
                    break;
                case '+':
                    lign.addView(getLayoutInflater().inflate(R.layout.screen_plus, null));
                    break;
                case '.':
                    ilyadejaunpoint++;
                    if (ilyadejaunpoint < 2) {
                        lign.addView(getLayoutInflater().inflate(R.layout.screen_point, null));
                    }
                    break;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    // if it's a firstNumber of a new number : init with newVal else concat oldVal + newVal
    /////////////////////////////////////////////////////////////////////////////////////////////
    protected String newVal(String oldVal, String newVal) {
        Log.i("ON EST DANS : ", "||||protected String newVal|||");
        Log.i("|||||||||| OLDVAL = ", oldVal);
        Log.i("||||||||NEWVAL = ", newVal);


        Boolean ilyadejaunpoint = false;
        Boolean newValIsANumber = false;
        String dot = ".";
        char recherche = dot.charAt(0);
        Log.i("|||||CHAR RECHERCHE = ", String.valueOf(recherche));
        for (int i = 0; i < oldVal.length(); i++) {
            if (oldVal.charAt(i) == recherche) {
                ilyadejaunpoint = true;
            }
        }

        try {
            Integer foo = Integer.parseInt(newVal);
            newValIsANumber = true;
        } catch (NumberFormatException e) {
            Log.i("|||||||   1 ", "CATCH!");
            //DO rien du tout
        }

        Log.i("|||||newValIsANumber ", String.valueOf(newValIsANumber));

        if (oldVal.equals("0") && newVal.equals(dot)) {
                Log.i("|||||||   1 ", newVal);
                Log.i("||||||||   1 ", dot);
                Log.i("||||||| ", "  2   ");
                retourNewVal = oldVal + newVal;
        } else if (ilyadejaunpoint == true && newVal.equals(dot)) {
            Log.i("||||| ", "   4   ");
            retourNewVal = oldVal;
        }else if (oldVal.equals("0") && newValIsANumber == true){
            Log.i("||||| ", "   5   ");
            retourNewVal = newVal;
        } else {
            Log.i("|||||||| ", "6");
            retourNewVal = oldVal + newVal; // add newVal to the right
        }

        Log.i("||ilyadejaunpoint = ", String.valueOf(ilyadejaunpoint));
        Log.i("|||||||||| OLDVAL = ", oldVal);
        Log.i("||||||||NEWVAL = ", newVal);
        Log.i("|||||||||||RETOUR = ", retourNewVal);

        return retourNewVal;
    }

    ///////////////////////////////////////////////////////////////////////////
    //Calcul du résultat en fonction d'op1 et op2
    //  /!\ RAJOUTER DE QUOI CONVERTIR LE RESULTAT EN INT SI C'EST PAS UN FLOAT
    // Prends en paramètre TOTAL, OPERATEUR, LIGNRESULT
    ///////////////////////////////////////////////////////////////////////////

    protected String calc(String subtotal, String operateur, String operande) {
        Log.i("ON EST DANS : ", "||||protected String calc|||");
        Log.i("_____CALCUL EN COURS___", "CALCULLLLLLLL");
        if ((operateur != null) || (operateur != "")) Log.i("_________OP__", operateur);
        if ((subtotal != null) || (subtotal != "")) Log.i("_________OP1__", subtotal);
        if ((operande != null) || (operande != "")) Log.i("_________OP2__", operande);

        Log.i("OP1", subtotal);
        Log.i("OP", operateur);
        Log.i("OP2", operande);
        Log.i("__________op_______", operateur);
        if ((subtotal.equals("")) && (operande != "")) {
            if (subtotal.equals("")) {
                Log.i("__________________", "Passé");
                subtotal = "0";
                return operande;
            }

        }

        BigDecimal operande1 = new BigDecimal(subtotal) ;
        BigDecimal operande2 = new BigDecimal(operande);
        BigDecimal zero = new BigDecimal("0.0");

        switch (operateur) {
            case "+":
                resultatBD = operande1.add(operande2);
                break;
            case "-":
                resultatBD = operande1.subtract(operande2);
                break;
            case "x":
                resultatBD = operande1.multiply(operande2);
                break;
            case "/":
                Integer i = operande2.compareTo(zero);
                if (i == 0) {
                    break;
                } else {
                    resultatBD = operande1.divide(operande2, 15, RoundingMode.HALF_UP);
                    break;
                }
        }
        Log.i("bd to string", resultatBD.toString());
      //  Log.i("string value of", String.valueOf(resultatBD.setScale(15)));
     //   Log.i("HALF_UP value of", String.valueOf(resultatBD.setScale(15, RoundingMode.HALF_UP)));
      //  Log.i("UP value of", String.valueOf(resultatBD.setScale(15, RoundingMode.UP)));
     //   Log.i("DOWN value of", String.valueOf(resultatBD.setScale(15, RoundingMode.DOWN)));
     //   Log.i("CEILING value of", String.valueOf(resultatBD.setScale(15, RoundingMode.CEILING)));
     //   Log.i("FLOOR value of", String.valueOf(resultatBD.setScale(15, RoundingMode.FLOOR)));
     //   Log.i("HALF_DOWN value of", String.valueOf(resultatBD.setScale(15, RoundingMode.HALF_DOWN)));
     //   Log.i("HALF_EVEN value of", String.valueOf(resultatBD.setScale(15, RoundingMode.HALF_EVEN)));
     //   Log.i("UNNECESSARY value of", String.valueOf(resultatBD.setScale(15, RoundingMode.UNNECESSARY)));

        return String.valueOf(resultatBD);
    }
//////////////////////////////////////////////////////////////////////////////////////////////
/// Fonction permettant de scoll automatiquement les deux HorizontalScrollView vers la droite
//    Obligé de faire un run pour mettre un petit délais permettant au scroll de se faire
/////////////////////////////////////////////////////////////////////////////////////////////

    public void scroll() {
        final HorizontalScrollView svTop = (HorizontalScrollView) findViewById(R.id.scrollTop);
        final HorizontalScrollView svDown = (HorizontalScrollView) findViewById(R.id.scrollDown);

        svTop.post(new Runnable() {
            public void run() {
                svTop.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });

        svDown.post(new Runnable() {
            public void run() {
                svDown.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });
    }
}

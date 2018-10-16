package com.corp.tajine.la_cacalculatrice;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by regis on 10/04/2018.
 */

public class ViewCalculActivity extends AppCompatActivity {

    ListView calculListView;

    /*private String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcalcul);

        calculListView = (ListView) findViewById(R.id.calcul_List);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        ArrayList<Calcul> calculList = Calcul.getCalcList(this);
        if (!calculList.isEmpty()) {
            CalculAdapter calculAdapter = new CalculAdapter(this, calculList);
            calculListView.setAdapter(calculAdapter);
        }
    }

}

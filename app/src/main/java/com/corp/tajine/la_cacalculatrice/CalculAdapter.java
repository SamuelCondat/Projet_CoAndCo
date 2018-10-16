package com.corp.tajine.la_cacalculatrice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by regis on 10/04/2018.
 */

public class CalculAdapter extends ArrayAdapter<Calcul> {

    Context context;

    public CalculAdapter(Context context, List<Calcul> obj){
        super(context, -1, obj);
        this.context = context;
    }
    ///////////////////////////////////
    //Affichage historique des calculs
    //////////////////////////////////
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        Log.i("getView","entry__________________________________________");
        View view = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_calcul, null);
        } else {
            view = convertView;
        }

        Log.i("pos",(String.valueOf(pos))+"________________________");

        Calcul calcul = Calcul.getCalcul(context, pos+1);

        TextView dateCalc = (TextView)view.findViewById(R.id.listItemCalcul_date);
        TextView lignCalc = (TextView)view.findViewById(R.id.listItemCalcul_calcul);

        dateCalc.setText(calcul.getCalcDate().toString());
        lignCalc.setText(calcul.getCalc() + " = " + calcul.getResult());

        return view;
    }
}

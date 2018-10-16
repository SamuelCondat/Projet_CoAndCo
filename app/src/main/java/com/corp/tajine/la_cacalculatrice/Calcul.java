package com.corp.tajine.la_cacalculatrice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by regis on 08/04/2018.
 */

public class Calcul {
    private long id;
    private long calcDate;
    private String calc;
    private String result;
    private long mode;

    public  Calcul() {}

    public Calcul(String calc, String result, long mode){
        this.setCalc(calc);
        this.setCalcDate();
        this.setResult(result);
        this.setMode(mode);
    }

    private Calcul(Cursor cursor){
        id = cursor.getLong(cursor.getColumnIndex("id"));
        calcDate = cursor.getLong(cursor.getColumnIndex("calcDate"));
        calc = cursor.getString(cursor.getColumnIndex("calc"));
        result = cursor.getString(cursor.getColumnIndex("result"));
        mode = cursor.getLong(cursor.getColumnIndex("mode"));
    }

    public long getId () { return id; }
    public void setId (long id) { this.id = id; }

    public Date getCalcDate() {
        Log.i("getCalcDate","entry");
        long longDate = this.calcDate;
        Date calcDate = new Date(longDate);
        return calcDate;
    }

    private void setCalcDate() {
        Date currentDate = Calendar.getInstance().getTime();
        long longDate = currentDate.getTime();
        this.calcDate = longDate;
    }

    public String getCalc() { return calc; }
    public void setCalc(String calc) { this.calc = calc; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public long getMode (){ return mode; }
    public void setMode (long mode) { this.mode = mode; }

    public static ArrayList<Calcul> getCalcList(Context context){
        Log.i("getCalcList","entry");

        ArrayList<Calcul> listCalc = new ArrayList<>();

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true, "Calcul", new String[]{"id", "calcDate", "calc", "result", "mode"}, null, null, null,null,"calcDate", null);

        while (cursor.moveToNext()){
            listCalc.add(new Calcul(cursor));
        }

        cursor.close();
        db.close();

        Log.i("getCalcList", "exit");

        return listCalc;
    }



    public static Calcul getCalcul(Context context, long id){
        //Log.i("getCalcul","entry");
        Calcul calcul = null;

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = "id = " + String.valueOf(id);
        Cursor cursor = db.query(true, "Calcul", new String[]{"id", "calcDate", "calc", "result", "mode"}, where, null, null, null, "calcDate", null);

        if (cursor.moveToFirst()) calcul = new Calcul(cursor);

        cursor.close();
        db.close();
        //Log.i("getCalcul","exit");
        return calcul;
    }

    public void insert (Context context){

        ContentValues values = new ContentValues();
        values.put("calcDate", this.calcDate);
        values.put("calc", this.calc);
        values.put("result", this.result);
        values.put("mode", this.mode);

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        this.id = db.insert("Calcul", null, values);

        db.close();
        Log.i("insert","new id = " + this.id);
    }

    public void delete (Context context){
        String where = "id = ?" ;
        String[] args = new String[1];
        args[0] = String.valueOf(this.id);

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("Calcul", where, args);
        db.close();
    }

        /*public static ArrayList<String> getCalcListView(Context context){
        ArrayList<String> listCalc = new ArrayList<>();

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true, "Calcul", new String[]{"id", "calcDate", "calc", "result", "mode"}, null, null, null,null,"calcDate", null);

        while (cursor.moveToNext()){
            Calcul c = new Calcul(cursor);
            listCalc.add(c.calc + " = " + c.result);
        }

        cursor.close();
        db.close();

        return listCalc;
    }*/
}

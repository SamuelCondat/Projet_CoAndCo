package com.corp.tajine.la_cacalculatrice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by regis on 07/04/2018.
 */

public class Trophy {
    long id;
    String name;
    boolean validated;
    int actualValue;
    int limitValue;

    private Trophy (Cursor cursor){
        id = cursor.getLong(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        validated = cursor.getInt(cursor.getColumnIndex("validated")) != 0;
        actualValue = cursor.getInt(cursor.getColumnIndex("actualValue"));
        limitValue = cursor.getInt(cursor.getColumnIndex("limitValue"));
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isValidated() { return validated; }
    public void setValidated(boolean validated) { this.validated = validated; }

    public int getActualValue() { return actualValue; }
    public void setActualValue(int actualValue) { this.actualValue = actualValue; }

    public int getLimitValue() { return limitValue; }
    public void setLimitValue(int limitValue) { this.limitValue = limitValue; }

    public static ArrayList<Trophy> getTrophyList(Context context){
        ArrayList<Trophy> listTrophy = new ArrayList<>();

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "Trophy", new String[]{"id","name", "validated", "actualValue", "limitValue"},null,null,null,null,"id",null);

        while (cursor.moveToNext()) {
            listTrophy.add(new Trophy(cursor));
        }
        cursor.close();
        db.close();

        return listTrophy;
    }

    public static Trophy getTrophy(Context context, long id){
        Trophy trophy = null;

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = "id = " + String.valueOf(id);
        Cursor cursor = db.query(true,"Trophy", new String[]{"id","name", "validated", "actualValue", "limitValue"}, where, null, null, null, "id",null);

        if (cursor.moveToFirst()) trophy = new Trophy(cursor);

        cursor.close();
        db.close();

        return trophy;
    }

    public void update (Context context){
        int intValidated = this.validated ? 1 : 0;
        ContentValues values = new ContentValues();
        values.put("name", this.name);
        values.put("validated",intValidated);
        values.put("actualValue",this.actualValue);
        values.put("limitValue",this.limitValue);

        String where = "id = " + String.valueOf(this.id);

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.update("Trophy", values, where, null);
        db.close();
    }
}

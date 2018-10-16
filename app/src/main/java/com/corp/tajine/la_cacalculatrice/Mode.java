package com.corp.tajine.la_cacalculatrice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by regis on 09/04/2018.
 */

public class Mode {
    long id;
    String name;
    boolean activated;

    private Mode(Cursor cursor){
        id = cursor.getLong(cursor.getColumnIndex("id"));
        name = cursor.getString(cursor.getColumnIndex("name"));
        activated = cursor.getInt(cursor.getColumnIndex("activated")) != 0;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActivated() { return activated; }
    public void setActivated(boolean active) { this.activated = active; }

    public static ArrayList<Mode> getModeList(Context context){
        ArrayList<Mode> listMode = new ArrayList<>();

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "Mode", new String[]{"id","name", "activated"},null,null,null,null,"id",null);

        while (cursor.moveToNext()) {
            listMode.add(new Mode(cursor));
        }
        cursor.close();
        db.close();

        return listMode;
    }

    public static Mode getMode(Context context, long id){
        Mode mode = null;

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = "id = " + String.valueOf(id);
        Cursor cursor = db.query(true,"Mode", new String[]{"id","name", "activated"}, where, null, null, null, "id",null);

        if (cursor.moveToFirst()) mode = new Mode(cursor);

        cursor.close();
        db.close();

        return mode;
    }

    public void update (Context context){
        int intActivated = this.activated ? 1 : 0;
        ContentValues values = new ContentValues();
        values.put("name", this.name);
        values.put("activated",intActivated);

        String where = "id = " + String.valueOf(this.id);

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.update("Mode", values, where, null);
        db.close();
    }
}

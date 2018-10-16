package com.corp.tajine.la_cacalculatrice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by regis on 07/04/2018.
 */

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME = "ptit_caca.db";
    static int DB_VERSION = 1;

    public LocalSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFilTable;

        sqlFilTable = "CREATE TABLE Trophy(id INTEGER PRIMARY KEY," +
                "name TEXT, validated INTEGER, actualValue INTEGER, limitValue INTEGER);";
        Log.i("db",sqlFilTable);
        db.execSQL(sqlFilTable);

        sqlFilTable = "CREATE TABLE Calcul(id INTEGER PRIMARY KEY," +
                "calcDate INTEGER, calc TEXT, result TEXT, mode INTEGER);";
        db.execSQL(sqlFilTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++){
            int versionToUpdate = i + 1;
            switch (versionToUpdate){
                case 2:
                    // code de la v2
                    break;
            }
        }
    }
}

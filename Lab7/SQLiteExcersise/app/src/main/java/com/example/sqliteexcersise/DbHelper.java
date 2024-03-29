package com.example.sqliteexcersise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LabReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LabReaderContract.LabEntry.TABLE_NAME + " (" +
                    LabReaderContract.LabEntry._ID + " INTEGER PRIMARY KEY," +
                    LabReaderContract.LabEntry.COLUMN_NAME_USERNAME + " TEXT UNIQUE," +
                    LabReaderContract.LabEntry.COLUMN_NAME_NAME + " TEXT,"+
                    LabReaderContract.LabEntry.COLUMN_NAME_PASS + " TEXT,"+
                    LabReaderContract.LabEntry.COLUMN_NAME_EMAIL + " TEXT,"+
                    LabReaderContract.LabEntry.COLUMN_NAME_PHONE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LabReaderContract.LabEntry.TABLE_NAME;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void update(SQLiteDatabase db, String userName, String newPass) {
        db.execSQL("UPDATE "+LabReaderContract.LabEntry.TABLE_NAME+" SET "+LabReaderContract.LabEntry.COLUMN_NAME_PASS
        + "='" + newPass + "' WHERE "+LabReaderContract.LabEntry.COLUMN_NAME_USERNAME+" = '"+userName+"';");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

package com.example.madsqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LabReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ReaderContract.TableEntry.TABLE_NAME + " (" +
                    ReaderContract.TableEntry._ID + " INTEGER PRIMARY KEY," +
                    ReaderContract.TableEntry.COLUMN_NAME_PID + " TEXT UNIQUE," +
                    ReaderContract.TableEntry.COLUMN_NAME_PNAME + " TEXT,"+
                    ReaderContract.TableEntry.COLUMN_NAME_PRICE + " TEXT,"+
                    ReaderContract.TableEntry.COLUMN_NAME_MRP + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ReaderContract.TableEntry.TABLE_NAME;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void update(SQLiteDatabase db, String pid, String pname, String price, String mrp) {
        db.execSQL("UPDATE "+ReaderContract.TableEntry.TABLE_NAME+" SET "+ReaderContract.TableEntry.COLUMN_NAME_PNAME
                + "='" + pname + "' WHERE "+ReaderContract.TableEntry.COLUMN_NAME_PID+" = '"+pid+"';");
        db.execSQL("UPDATE "+ReaderContract.TableEntry.TABLE_NAME+" SET "+ReaderContract.TableEntry.COLUMN_NAME_PRICE
                + "='" + price + "' WHERE "+ReaderContract.TableEntry.COLUMN_NAME_PID+" = '"+pid+"';");
        db.execSQL("UPDATE "+ReaderContract.TableEntry.TABLE_NAME+" SET "+ReaderContract.TableEntry.COLUMN_NAME_MRP
                + "='" + mrp + "' WHERE "+ReaderContract.TableEntry.COLUMN_NAME_PID+" = '"+pid+"';");
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
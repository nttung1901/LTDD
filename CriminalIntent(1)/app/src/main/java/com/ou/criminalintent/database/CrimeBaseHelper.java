package com.ou.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ou.criminalintent.database.CrimeDbScheme.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME ="crimedb.db";

    public CrimeBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ CrimeTable.NAME+ "(" +
                "_id interger primary key autoincrement, "+
                CrimeTable.Cols.UUID+","+
                CrimeTable.Cols.TITLE+","+
                CrimeTable.Cols.DATE+","+
                CrimeTable.Cols.SOLVED+
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
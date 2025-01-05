package ou.nttung.criminalintent.database;

import static ou.nttung.criminalintent.database.CrimeDbSchema.CrimeTable.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION =1;
    private static final String DATABASE_NAME = "crimeDB.db";

    public CrimeBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + "("+
                "_id integer primary key autoincrement, "+
                Cols.UUID + "," +
                Cols.TITLE + "," +
                Cols.DATE + "," +
                Cols.SOlVED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

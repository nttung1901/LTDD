package ou.nttung.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ou.nttung.criminalintent.Models.Crime;
import ou.nttung.criminalintent.database.CrimeBaseHelper;
import static ou.nttung.criminalintent.database.CrimeDbSchema.CrimeTable.*;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<Crime>();
        for(int i =0;i<5;i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i%2==0);
            mCrimes.add(crime);
        }

        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID,crime.getId().toString());
        contentValues.put(Cols.TITLE,crime.getTitle());
        contentValues.put(Cols.DATE,crime.getDate().toString());
        contentValues.put(Cols.SOlVED,crime.getSolved() ? 1 : 0);

        return contentValues;
    }

    public void addCrime(Crime crime){
//        mCrimes.add(crime);
        mDatabase.insert(NAME,null, getContentValues(crime));
    }

    public List<Crime> getmCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes)
            if(crime.getId().equals(id))
                return crime;
        return null;
    }
}

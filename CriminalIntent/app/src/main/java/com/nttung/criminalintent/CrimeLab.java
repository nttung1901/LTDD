package com.nttung.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab{
    private static CrimeLab sCrimeLab;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    private List<Crime> mCrimes;
    private CrimeLab(Context context){
        mCrimes = new ArrayList<Crime>();
        for(int i =0;i<100;i++){
            Crime crime = new Crime();
            crime.setmTitle("Crime #"+i);
            crime.setmSolved(i%2==0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes)
            if(crime.getmId() == id )
                return crime;
        return null;
    }

}

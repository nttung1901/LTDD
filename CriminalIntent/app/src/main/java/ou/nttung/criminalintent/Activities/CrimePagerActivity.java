package ou.nttung.criminalintent.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.UUID;

import ou.nttung.criminalintent.CrimeLab;
import ou.nttung.criminalintent.Fragments.CrimeFragment;
import ou.nttung.criminalintent.Models.Crime;
import ou.nttung.criminalintent.R;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "crime_id";
    List<Crime> crimes;
    private ViewPager2 mViewPager;
    private UUID crimeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = findViewById(R.id.crime_viewpager);
        crimes = CrimeLab.get(this).getmCrimes();
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this);
        mViewPager.setAdapter(myPagerAdapter);

        for (int i=0;i<crimes.size();i++){
            if(crimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    private class MyPagerAdapter extends FragmentStateAdapter{

        public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return CrimeFragment.newInstance(crimes.get(position).getId());
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }
    }
}
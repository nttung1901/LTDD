package ou.nttung.criminalintent.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ou.nttung.criminalintent.Activities.CrimePagerActivity;
import ou.nttung.criminalintent.Adapters.CrimeAdapter;
import ou.nttung.criminalintent.CrimeLab;
import ou.nttung.criminalintent.Models.Crime;
import ou.nttung.criminalintent.R;


public class CrimeListFragment extends Fragment {

    private static final String KEY_SUBTITLE = "subtitle";

    private RecyclerView mRecyclerViewCrime;
    private CrimeAdapter crimeAdapter;

    private boolean mSubtitleVisible;

    @Override
    public void onResume() {
        super.onResume();


        updateUi();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        if(savedInstanceState!=null){
            mSubtitleVisible = savedInstanceState.getBoolean(KEY_SUBTITLE,false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecyclerViewCrime = view.findViewById(R.id.recyclerView_crime);
        mRecyclerViewCrime.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false) );
        updateUi();


        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_SUBTITLE,mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubtitleVisible){
            menuItem.setTitle(R.string.hide_subtitle);
        }
        else{
            menuItem.setTitle(R.string.show_subtitle);
        }


        getActivity().invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_item_new_item){
            Crime crime = new Crime();
            CrimeLab.get(getActivity()).addCrime(crime);
            Intent intent = CrimePagerActivity.newIntent(getContext(), crime.getId());
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.menu_item_show_subtitle){
            mSubtitleVisible = !mSubtitleVisible;
            getActivity().invalidateOptionsMenu();

           updateSubtitle();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    private void updateSubtitle(){

        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getmCrimes().size();
        String subtitle = getString(R.string.subtitle_format,crimeCount);
        if(!mSubtitleVisible)
            subtitle = null;

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUi(){
        List<Crime> crimes = CrimeLab.get(getActivity()).getmCrimes();
        if(crimeAdapter==null){
            crimeAdapter = new CrimeAdapter(crimes);
            mRecyclerViewCrime.setAdapter(crimeAdapter);
        }else
            crimeAdapter.notifyDataSetChanged();

        updateSubtitle();
    }
}
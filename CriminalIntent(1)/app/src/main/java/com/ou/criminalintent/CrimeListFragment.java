package com.ou.criminalintent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrimeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrimeListFragment extends Fragment {
    private RecyclerView mRecyclerviewCrime;
    private CrimeAdapter crimeAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_new_crime) {
            Crime crime = new Crime();
            CrimeLab.get(getActivity()).addCrime(crime);
            Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getmId());
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menu_item_show_subtitle) {
            updatesubtitle();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecyclerviewCrime = view.findViewById(R.id.recyclerview_crime);
        mRecyclerviewCrime.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(crimeAdapter ==null){
            crimeAdapter = new CrimeAdapter(crimes);
            mRecyclerviewCrime.setAdapter(crimeAdapter);
        }else{
            crimeAdapter.notifyDataSetChanged();
        }

        updatesubtitle();
    }

    private void updatesubtitle(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);

        AppCompatActivity act = (AppCompatActivity) getActivity();
        act.getSupportActionBar().setSubtitle(subtitle);

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CheckBox mCheckBoxListItemSolved;
        private TextView mTextViewListItemTitle;
        private TextView mTextViewListItemDate;
        private Crime mCrime;

        public void bindCrime(Crime crime){
            mCrime = crime;
            mCheckBoxListItemSolved.setChecked(mCrime.ismSolved());
            mTextViewListItemTitle.setText(mCrime.getmTitle());
            mTextViewListItemDate.setText(mCrime.getmDate().toString());
        }

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mTextViewListItemTitle = itemView.findViewById(R.id.textView_listItemCrime_title);
            mTextViewListItemDate = itemView.findViewById(R.id.textView_listItemCrime_date);
            mCheckBoxListItemSolved = itemView.findViewById(R.id.checkBox_listItemCrime_solved);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = MainActivity.newIntent(getActivity(),mCrime.getmId());
//            startActivity(intent);
            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getmId());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.list_item_crime,parent,false);

            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    public CrimeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrimeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrimeListFragment newInstance(String param1, String param2) {
        CrimeListFragment fragment = new CrimeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


}
package com.nttung.criminalintent.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nttung.criminalintent.Crime;
import com.nttung.criminalintent.CrimeLab;
import com.nttung.criminalintent.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrimeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrimeListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView mRecycleviewCrime;
    private CrimeAdapter mCrimeAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecycleviewCrime = view.findViewById(R.id.recycleview_crime);
        mRecycleviewCrime.setLayoutManager(new LinearLayoutManager(getActivity()));

        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mCrimeAdapter = new CrimeAdapter(crimes);
        mRecycleviewCrime.setAdapter(mCrimeAdapter);

        return view;
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private CheckBox mCheckBoxSolved;
        private Crime mCrime;

        public void bindCrime(Crime crime){
            mCrime = crime;
            mTextViewTitle.setText(mCrime.getmTitle());
            mTextViewDate.setText(mCrime.getmDate().toString());
            mCheckBoxSolved.setChecked(mCrime.ismSolved());

        }

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mTextViewTitle = itemView.findViewById(R.id.textview_listItemCrime_title);
            mTextViewDate = itemView.findViewById(R.id.textview_listItemCrime_date);
            mCheckBoxSolved = itemView.findViewById(R.id.checkbox_listItemCrime_solved);
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public  CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }
        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


}
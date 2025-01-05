package ou.nttung.criminalintent.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ou.nttung.criminalintent.Activities.CrimePagerActivity;
import ou.nttung.criminalintent.Activities.MainActivity;
import ou.nttung.criminalintent.Models.Crime;
import ou.nttung.criminalintent.R;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder> {
    private List<Crime> items;
    private Context context;

    public CrimeAdapter(List<Crime> items){
        this.items = items;
    }

    public List<Crime> getItems() {
        return items;
    }

    public void setItems(List<Crime> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CrimeAdapter.CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_crime_list,parent,false);
        return new CrimeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeAdapter.CrimeViewHolder holder, int position) {
        holder.mTitleTextView.setText(items.get(position).getTitle());
        holder.mDateTextView.setText(items.get(position).getDate().toString());
        holder.mSolvedCheckBox.setChecked(items.get(position).getSolved());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = MainActivity.newIntent(context, items.get(position).getId());
                Intent intent = CrimePagerActivity.newIntent(context, items.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CrimeViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.title_textView);
            mDateTextView = itemView.findViewById(R.id.date_textView);
            mSolvedCheckBox= itemView.findViewById(R.id.solved_checkBox);
        }

    }
}

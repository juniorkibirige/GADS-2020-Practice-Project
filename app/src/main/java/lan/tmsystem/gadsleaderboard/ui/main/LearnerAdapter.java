package lan.tmsystem.gadsleaderboard.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lan.tmsystem.gadsleaderboard.R;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.ViewHolder> {
    ArrayList items;
    private final Context mContext;
    private static LayoutInflater mLayoutInflater;

    public LearnerAdapter(ArrayList items, Context context) {
        this.items = items;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = (String) items.get(position);
        String data = name.concat(" Data");
        holder.bind(name, data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView learnerName;
        private final TextView learnerData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.learner_name);
            learnerData = itemView.findViewById(R.id.learner_data);
        }

        public void bind(final String name, final String data) {
            learnerName.setText(name);
            learnerData.setText(data);
        }
    }
}

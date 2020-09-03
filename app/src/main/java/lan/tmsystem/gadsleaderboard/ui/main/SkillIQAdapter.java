package lan.tmsystem.gadsleaderboard.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import lan.tmsystem.gadsleaderboard.R;

public class SkillIQAdapter extends ArrayAdapter {
    private ArrayList dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView learnerName;
        TextView learnerData;
    }

    public SkillIQAdapter(ArrayList data, Context context) {
        super(context, R.layout.skillid_row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return (String) dataSet.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.skillid_row_item, parent, false);
            viewHolder.learnerName = (TextView) convertView.findViewById(R.id.learner_name);
            viewHolder.learnerData = (TextView) convertView.findViewById(R.id.learner_data);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.learnerName.setText(getItem(position));
        viewHolder.learnerData.setText(getItem(position).concat(" Data"));

        return convertView;
    }
}

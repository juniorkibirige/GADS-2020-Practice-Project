package lan.tmsystem.gadsleaderboard.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import lan.tmsystem.gadsleaderboard.CustomVolleyRequest;
import lan.tmsystem.gadsleaderboard.R;
import lan.tmsystem.gadsleaderboard.models.SkillIq;

public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.ViewHolder> {
    List<SkillIq> skills;
    private static LayoutInflater mLayoutInflater;
    private static ImageLoader mImageLoader;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.skillid_row_item, parent, false);
        mImageLoader = CustomVolleyRequest.getInstance(parent.getContext()).getImageLoader();
        return new SkillIQAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = (String) skills.get(position).getNames();
        String score = skills.get(position).getScore();
        String country = skills.get(position).getCountry();
        String badgeUrl = skills.get(position).getBadgeUrl();
        holder.bind(name, score, country, badgeUrl);
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView learnerName;
        TextView learnerData;
        NetworkImageView mNetworkImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.learner_name);
            learnerData = itemView.findViewById(R.id.learner_data);
            mNetworkImageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(String name, String score, String country, String badgeUrl) {
            mImageLoader.get(badgeUrl, ImageLoader.getImageListener(mNetworkImageView, R.drawable.skill_iq_trimmed, R.drawable.skill_iq_trimmed));
            learnerName.setText(name);
            learnerData.setText(score.concat(" skill IQ Score, ").concat(country).concat("."));
            mNetworkImageView.setImageUrl(badgeUrl, mImageLoader);
        }
    }

    public SkillIQAdapter(List<SkillIq> data, Context context) {
        this.skills = data;
        mLayoutInflater = LayoutInflater.from(context);
    }
}

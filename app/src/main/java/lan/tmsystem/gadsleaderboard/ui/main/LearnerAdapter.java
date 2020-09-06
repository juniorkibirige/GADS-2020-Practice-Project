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
import lan.tmsystem.gadsleaderboard.models.Hours;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.ViewHolder> {
    List<Hours> items;
    private static LayoutInflater mLayoutInflater;
    private static ImageLoader mImageLoader;

    public LearnerAdapter(List<Hours> items, Context context) {
        this.items = items;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_item, parent, false);
        mImageLoader = CustomVolleyRequest.getInstance(parent.getContext()).getImageLoader();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = (String) items.get(position).getNames();
        String hours = items.get(position).getHours();
        String country = items.get(position).getCountry();
        String badgeUrl = items.get(position).getBadgeUrl();
        holder.bind(name, hours, country, badgeUrl);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView learnerName;
        private final TextView learnerData;
        private final NetworkImageView mNetworkImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.learner_name);
            learnerData = itemView.findViewById(R.id.learner_data);
            mNetworkImageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final String name, final String data, final String country, final String badgeUrl) {
            mImageLoader.get(badgeUrl,
                    ImageLoader.getImageListener(mNetworkImageView, R.drawable.top_learner, R.drawable.top_learner));
            learnerName.setText(name);
            learnerData.setText(data.concat(" learning hours, ").concat(country).concat("."));
            mNetworkImageView.setImageUrl(badgeUrl, mImageLoader);
        }
    }
}

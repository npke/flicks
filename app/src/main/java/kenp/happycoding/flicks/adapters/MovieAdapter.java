package kenp.happycoding.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kenp.happycoding.flicks.R;
import kenp.happycoding.flicks.models.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private int mResource;

    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);

            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Movie movie = getItem(position);

        bindViews(movie, viewHolder);

        return convertView;
    }

    private void bindViews(Movie movie, ViewHolder viewHolder) {
        int orientation = getContext().getResources().getConfiguration().orientation;

        if (orientation != Configuration.ORIENTATION_PORTRAIT) {

            Glide.with(getContext())
                    .load(movie.getLandPosterUrl())
                    .placeholder(R.drawable.movie_placeholder_land)
                    .into(viewHolder.ivPoster);
        } else {
            Glide.with(getContext())
                    .load(movie.getPosterUrl())
                    .placeholder(R.drawable.movie_placeholder)
                    .into(viewHolder.ivPoster);
        }

        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
    }

    public class ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
    }
}


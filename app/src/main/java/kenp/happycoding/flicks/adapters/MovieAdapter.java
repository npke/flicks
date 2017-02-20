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

import org.w3c.dom.Text;

import java.util.List;

import kenp.happycoding.flicks.R;
import kenp.happycoding.flicks.models.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private final float MINIMUM_POPULAR_RATING = 7.0f;
    private final int NORMAL_MOVIE = 0;
    private final int POPULAR_MOVIE = 1;

    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
    }

    public boolean isPopularMovie(Movie movie) {
        return movie.getRating() >= MINIMUM_POPULAR_RATING;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPopularMovie(getItem(position)))
            return POPULAR_MOVIE;
        return NORMAL_MOVIE;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        if (getItemViewType(position) == NORMAL_MOVIE) {
            NormalMovieViewHolder viewHolder;

            if (convertView != null && convertView.getTag() instanceof NormalMovieViewHolder) {
                viewHolder = (NormalMovieViewHolder) convertView.getTag();
            } else {
                viewHolder = new NormalMovieViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);

                viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
                viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

                convertView.setTag(viewHolder);
            }

            bindViews(movie, viewHolder);
        } else {
            PopularMovieViewHolder viewHolder;

            if (convertView != null && convertView.getTag() instanceof PopularMovieViewHolder) {
                viewHolder = (PopularMovieViewHolder) convertView.getTag();
            } else {
                viewHolder = new PopularMovieViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, parent, false);

                viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

                convertView.setTag(viewHolder);
            }

            bindViews(movie, viewHolder);
        }

        return convertView;
    }

    private void bindViews(Movie movie, ViewHolder viewHolder) {
        int orientation = getContext().getResources().getConfiguration().orientation;

        if (isPopularMovie(movie)) {
            bindWithPopularView(movie, (PopularMovieViewHolder) viewHolder);

            return;
        }

        bindWithNormalView(movie, (NormalMovieViewHolder) viewHolder, orientation);
    }

    private void bindWithNormalView(Movie movie, NormalMovieViewHolder viewHolder, int orientation) {
        NormalMovieViewHolder normalMovieViewHolder = viewHolder;
        if (orientation != Configuration.ORIENTATION_PORTRAIT) {

            Glide.with(getContext())
                    .load(movie.getLandPosterUrl())
                    .placeholder(R.drawable.movie_placeholder_land)
                    .into(normalMovieViewHolder.ivPoster);
        } else {
            Glide.with(getContext())
                    .load(movie.getPosterUrl())
                    .placeholder(R.drawable.movie_placeholder)
                    .into(normalMovieViewHolder.ivPoster);
        }

        normalMovieViewHolder.tvTitle.setText(movie.getTitle());
        normalMovieViewHolder.tvOverview.setText(movie.getOverview());
    }

    private void bindWithPopularView(Movie movie, PopularMovieViewHolder viewHolder) {
        PopularMovieViewHolder popularMovieViewHolder = viewHolder;

        Glide.with(getContext())
                .load(movie.getLandPosterUrl())
                .placeholder(R.drawable.movie_placeholder_land)
                .into(popularMovieViewHolder.ivPoster);

        viewHolder.tvTitle.setText(movie.getTitle());
    }

    public class ViewHolder {

    }

    public class PopularMovieViewHolder extends ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
    }

    public class NormalMovieViewHolder extends ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
    }

}


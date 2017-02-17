package kenp.happycoding.flicks.adapters;

import android.content.Context;
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
    private Context mContext;
    private int mResource;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mMovies = objects;
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

        Glide.with(getContext())
                .load(movie.getPosterUrl())
                .into(viewHolder.ivPoster);

        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    public class ViewHolder  {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
    }
}


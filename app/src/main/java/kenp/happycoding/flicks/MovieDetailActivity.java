package kenp.happycoding.flicks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import kenp.happycoding.flicks.models.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tvMovieTitle;
    private TextView tvMovieOverview;
    private TextView tvMovieReleaseDate;
    private RatingBar rbMovieRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");

        tvMovieOverview = (TextView) findViewById(R.id.tvMovieOverview);
        tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        tvMovieReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        rbMovieRating = (RatingBar) findViewById(R.id.rbMovieRating);

        if (movie == null) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        } else {
            tvMovieTitle.setText(movie.getTitle());
            tvMovieOverview.setText(movie.getOverview());
            tvMovieReleaseDate.setText(movie.getReleaseDate());
            rbMovieRating.setRating(movie.getRating()/2);
        }
    }
}

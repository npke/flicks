package kenp.happycoding.flicks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import kenp.happycoding.flicks.R;
import kenp.happycoding.flicks.api.TheMovieDbService;
import kenp.happycoding.flicks.api.TrailerResponse;
import kenp.happycoding.flicks.models.Movie;
import kenp.happycoding.flicks.models.YouTubeVideo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends YouTubeBaseActivity {

    private TextView tvMovieTitle;
    private TextView tvMovieOverview;
    private TextView tvMovieReleaseDate;
    private RatingBar rbMovieRating;
    private YouTubePlayerView youTubePlayerView;

    private TheMovieDbService theMovieDbService;

    public static final String YOUTUBE_API_KEY = "AIzaSyDkVyvL9qwdsBvxL1fa63Ns3TQAzU4eP_o";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        findViews();

        getPassingMovieData();
    }

    private void findViews() {
        tvMovieOverview = (TextView) findViewById(R.id.tvMovieOverview);
        tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        tvMovieReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        rbMovieRating = (RatingBar) findViewById(R.id.rbMovieRating);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youTubePlayer);
    }

    private void getPassingMovieData() {
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");

        if (movie == null) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        } else {
            tvMovieTitle.setText(movie.getTitle());
            tvMovieOverview.setText(movie.getOverview());
            tvMovieReleaseDate.setText(movie.getReleaseDate());
            rbMovieRating.setRating(movie.getRating() / 2);


            getTrailerData(movie);
        }
    }

    private void getTrailerData(Movie movie) {
        theMovieDbService = TheMovieDbService.Creator.getService();
        theMovieDbService.getMovieTrailers(movie.getId())
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        showMovieTrailer(response.body().getVideos());
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {

                    }
                });
    }

    private void showMovieTrailer(final List<YouTubeVideo> videos) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(videos.get(0).getSource());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                youTubeInitializationResult.getErrorDialog(MovieDetailActivity.this, 1);
            }
        });
    }
}

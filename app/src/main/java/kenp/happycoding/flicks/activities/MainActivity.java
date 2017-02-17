package kenp.happycoding.flicks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kenp.happycoding.flicks.R;
import kenp.happycoding.flicks.adapters.MovieAdapter;
import kenp.happycoding.flicks.api.MovieResponse;
import kenp.happycoding.flicks.api.TheMovieDbService;
import kenp.happycoding.flicks.models.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMovies = (ListView) findViewById(R.id.lvMovies);
        final MovieAdapter adapter = new MovieAdapter(this, R.layout.item_movie, new ArrayList<Movie>());
        lvMovies.setAdapter(adapter);

        TheMovieDbService service = TheMovieDbService.Creator.getService();
        service.getNowPlayingMovies("a07e22bc18f5cb106bfe4cc1f83ad8ed").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                response.body();
                adapter.addAll(response.body().getResults());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}

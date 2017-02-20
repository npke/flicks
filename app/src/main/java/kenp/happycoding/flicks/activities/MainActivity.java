package kenp.happycoding.flicks.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import kenp.happycoding.flicks.R;
import kenp.happycoding.flicks.adapters.MovieAdapter;
import kenp.happycoding.flicks.api.MovieResponse;
import kenp.happycoding.flicks.api.TheMovieDbService;
import kenp.happycoding.flicks.models.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovies;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        setupRefreshLayout();

        setupListViewAndAdapter();

        getMovieList(adapter);
    }

    private void setupRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                getMovieList(adapter);
            }
        });
    }

    private void setupListViewAndAdapter() {
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        adapter = new MovieAdapter(this, R.layout.item_movie, new ArrayList<Movie>());
        lvMovies.setAdapter(adapter);

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = adapter.getItem(i);

                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("movie", movie);

                startActivity(intent);
            }
        });
    }

    private void getMovieList(final MovieAdapter adapter) {
        TheMovieDbService service = TheMovieDbService.Creator.getService();
        service.getNowPlayingMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBar.setVisibility(View.GONE);

                adapter.addAll(response.body().getResults());
                adapter.notifyDataSetChanged();

                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

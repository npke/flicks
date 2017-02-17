package kenp.happycoding.flicks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

import kenp.happycoding.flicks.R;
import kenp.happycoding.flicks.adapters.MovieAdapter;
import kenp.happycoding.flicks.models.Movie;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMovies = (ListView) findViewById(R.id.lvMovies);
        MovieAdapter adapter = new MovieAdapter(this, R.layout.item_movie, Movie.getList());
        lvMovies.setAdapter(adapter);
    }
}

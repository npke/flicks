package kenp.happycoding.flicks.api;

import java.util.List;

import kenp.happycoding.flicks.models.Movie;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDbService {
    @GET("now_playing")
    Call<List<Movie>> getNowPlayingMovies(@Query("api_key") String apiKey);

    public static class Creator {

        private static final String BASE_URL = "https://api.themoviedb.org/3/";

        private static Retrofit retrofit;
        private static TheMovieDbService service;

        TheMovieDbService getService() {

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .build();
            }

            if (service == null) {
                service = retrofit.create(TheMovieDbService.class);
            }

            return service;
        }
    }
}

package kenp.happycoding.flicks.api;

import java.util.List;

import kenp.happycoding.flicks.models.Movie;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDbService {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    public static class Creator {

        private static final String BASE_URL = "https://api.themoviedb.org/3/";

        private static Retrofit retrofit;
        private static TheMovieDbService service;

        public static TheMovieDbService getService() {

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            if (service == null) {
                service = retrofit.create(TheMovieDbService.class);
            }

            return service;
        }
    }
}

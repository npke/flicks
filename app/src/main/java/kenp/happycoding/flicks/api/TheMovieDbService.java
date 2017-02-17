package kenp.happycoding.flicks.api;

import java.io.IOException;
import java.util.List;

import kenp.happycoding.flicks.models.Movie;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDbService {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies();

    public static class Creator {

        private static final String BASE_URL = "https://api.themoviedb.org/3/";
        private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

        private static Retrofit retrofit;
        private static TheMovieDbService service;

        public static TheMovieDbService getService() {

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(getClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            if (service == null) {
                service = retrofit.create(TheMovieDbService.class);
            }

            return service;
        }

        public static OkHttpClient getClient() {
            return new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor())
                    .build();
        }

        public static Interceptor getInterceptor() {
            return new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    HttpUrl url = request.url()
                            .newBuilder()
                            .addQueryParameter("api_key", API_KEY)
                            .build();

                    request = request.newBuilder()
                            .url(url)
                            .build();

                    return chain.proceed(request);
                }
            };
        }
    }
}

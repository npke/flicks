package kenp.happycoding.flicks.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kenp.happycoding.flicks.models.Movie;

public class MovieResponse {
    @SerializedName("page")
    private int mPage;

    @SerializedName("results")
    private List<Movie> mResults;

    public int getPage() {
        return mPage;
    }

    public List<Movie> getResults() {
        return mResults;
    }
}

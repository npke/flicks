package kenp.happycoding.flicks.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    public static final String IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w500";

    private String mPosterUrl;
    private String mTitle;
    private String mOverview;

    public Movie(String mPosterUrl, String mTitle, String mOverview) {
        this.mPosterUrl = mPosterUrl;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
    }

    public String getPosterUrl() {
        return IMAGE_URL_BASE + mPosterUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public static List<Movie> getList() {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < 100; i ++) {
            movies.add(new Movie("/qAwFbszz0kRyTuXmMeKQZCX3Q2O.jpg", "Title movie " + i, "The tender, heartbreaking story of a young man’s struggle to find himself, told across three defining chapters in his life as he experiences the ecstasy, pain, and beauty of falling in love, while grappling with his own sexuality."));
        }

        return movies;
    }
}

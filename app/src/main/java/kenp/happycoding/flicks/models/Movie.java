package kenp.happycoding.flicks.models;

import com.google.gson.annotations.SerializedName;

public class Movie {

    public static final String IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w500";

    @SerializedName("poster_path")
    private String mPosterUrl;

    @SerializedName("backdrop_path")
    private String mLandPosterUrl;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("vote_average")
    private float mRating;

    public Movie(String mPosterUrl, String mTitle, String mOverview, float mRating) {
        this.mPosterUrl = mPosterUrl;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mRating = mRating;
    }

    public String getPosterUrl() {
        return IMAGE_URL_BASE + mPosterUrl;
    }

    public String getLandPosterUrl() {
        return IMAGE_URL_BASE + mLandPosterUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public float getRating() {
        return mRating;
    }
}

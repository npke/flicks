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


    public Movie(String mPosterUrl, String mTitle, String mOverview) {
        this.mPosterUrl = mPosterUrl;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
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
}

package kenp.happycoding.flicks.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    public static final String IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w500";

    @SerializedName("id")
    private int mId;

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

    @SerializedName("release_date")
    private String mReleaseDate;

    public Movie(String mPosterUrl, String mTitle, String mOverview, String mReleaseDate, float mRating) {
        this.mPosterUrl = mPosterUrl;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mRating = mRating;
    }

    protected Movie(Parcel in) {
        mId = in.readInt();
        mPosterUrl = in.readString();
        mLandPosterUrl = in.readString();
        mTitle = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mRating = in.readFloat();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return mId;
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

    public String getReleaseDate() {
        return mReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mPosterUrl);
        parcel.writeString(mLandPosterUrl);
        parcel.writeString(mTitle);
        parcel.writeString(mOverview);
        parcel.writeString(mReleaseDate);
        parcel.writeFloat(mRating);
    }
}

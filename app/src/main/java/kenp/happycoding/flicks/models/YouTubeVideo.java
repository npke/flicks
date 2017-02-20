package kenp.happycoding.flicks.models;

import com.google.gson.annotations.SerializedName;

public class YouTubeVideo {
    @SerializedName("name")
    private String mName;

    @SerializedName("size")
    private String mSize;

    @SerializedName("source")
    private String mSource;

    @SerializedName("type")
    private String mType;

    public String getName() {
        return mName;
    }

    public String getSize() {
        return mSize;
    }

    public String getSource() {
        return mSource;
    }

    public String getType() {
        return mType;
    }
}

package kenp.happycoding.flicks.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kenp.happycoding.flicks.models.YouTubeVideo;

import static android.R.attr.id;

public class TrailerResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("youtube")
    private List<YouTubeVideo> videos;

    public List<YouTubeVideo> getVideos() {
        return videos;
    }
}

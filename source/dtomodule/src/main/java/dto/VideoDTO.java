package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize
public class VideoDTO implements Serializable {

    public Long id;
    public String name;
    public String url;
    private String videoId;
    private String playlistId;

    @JsonIgnore
    public ChannelDTO channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public ChannelDTO getChannel() {
        return channel;
    }

    @JsonIgnore
    public void setChannel(ChannelDTO channel) {
        this.channel = channel;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}

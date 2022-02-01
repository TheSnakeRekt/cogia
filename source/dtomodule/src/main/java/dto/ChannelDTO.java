package dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class ChannelDTO implements Serializable {

    private static final String BASE_URL = "https://www.youtube.com/channel/";
    public String id;
    public String name;
    public String url;

    @JsonIgnore
    public ParseTaskDTO task;
    public List<VideoDTO> videos = new ArrayList();


    public ChannelDTO() {

    }

    public ChannelDTO(String youtubeChannelId) {
        this.setId(youtubeChannelId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.setUrl(id);
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

    public void setUrl(String channelId) {
        this.url = BASE_URL + channelId;
    }

    public List<VideoDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDTO> videos) {
        this.videos = videos;
    }

    public ParseTaskDTO getTask() {
        return task;
    }

    public void setTask(ParseTaskDTO task) {
        this.task = task;
    }
}

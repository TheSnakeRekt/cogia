package com.interview.backend.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String channelId;

    @OneToOne
    private TaskParse task;

    @OneToMany(fetch = FetchType.LAZY, targetEntity=Video.class, cascade=ALL,
            mappedBy="channel")
    private Set<Video> videos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
        if(videos != null){
            this.videos.forEach(vid -> vid.setChannel(this));
        }
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskParse getTask() {
        return task;
    }

    public void setTask(TaskParse task) {
        this.task = task;
    }
}

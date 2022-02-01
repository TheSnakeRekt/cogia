package com.interview.backend.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ChannelTest {


    Channel channel;

    @BeforeEach
    void setUp() {
        channel = new Channel();
        assertNotNull(channel);
    }

    @Test
    void getId() {
        channel.setId(4L);
        assertEquals(4L, channel.getId());
    }

    @Test
    void getVideos() {
        channel.setVideos(mockVideos());
        assertNotNull(channel.getVideos());
    }

    @Test
    void getChannelId() {
        channel.setChannelId("youtubeTestChannelId");
        assertEquals("youtubeTestChannelId", channel.getChannelId());
    }

    @Test
    void getName() {
        channel.setName("ChannelTestName");
        assertEquals("ChannelTestName", channel.getName());
    }

    @Test
    void getTask() {
        TaskParse task = new TaskParse();
        task.setId(1L);
        channel.setTask(task);
        assertNotNull(channel.getTask());
        assertEquals(1L, channel.getTask().getId());
    }

    private static Set<Video> mockVideos(){
        Set<Video> videos = new HashSet<>();
        for(int i = 0; i < 4; i++){
            Video video = new Video();
            video.setId((long) i);
            videos.add(video);
        }
        return videos;
    }
}
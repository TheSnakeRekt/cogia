package com.interview.backend.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    Video video ;

    @BeforeEach
    void setUp(){
        video = new Video();
        assertNotNull(video);
    }

    @Test
    void getId() {
        video.setId(9L);
        assertEquals(9L, video.getId());
    }

    @Test
    void getName() {
        video.setName("videoTilteTest");
        assertEquals("videoTilteTest", video.getName());
    }

    @Test
    void getUrl() {
        video.setUrl("http://url.test");
        assertEquals("http://url.test", video.getUrl());
    }

    @Test
    void getChannel() {
        Channel channel = new Channel();
        channel.setId(78L);
        video.setChannel(channel);

        assertNotNull(video.getChannel());
        assertEquals(78L, video.getChannel().getId());
    }

    @Test
    void getVideoId() {
        video.setVideoId("videoID789879");
        assertEquals("videoID789879",video.getVideoId());
    }

    @Test
    void getPlaylistId() {
        video.setPlaylistId("playlistID789879");
        assertEquals("playlistID789879",video.getPlaylistId());
    }
}
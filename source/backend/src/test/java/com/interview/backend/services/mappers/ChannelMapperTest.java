package com.interview.backend.services.mappers;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import com.interview.backend.domain.Video;
import com.interview.backend.domain.enums.ETaskStatus;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import dto.VideoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ChannelMapperTest {
    ChannelMapper channelMapper;

    @Mock
    VideoMapper videoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        channelMapper = new ChannelMapper(videoMapper);
        assertNotNull(channelMapper);
    }

    @Test
    void fromDto() {
        Channel channel = channelMapper.fromDto(new ChannelDTO());
        channel.getVideos().add(new Video());

        assertNotNull(channel);
    }

    @Test
    void toDto() {
        ChannelDTO channel = channelMapper.toDto(new Channel());
        channel.getVideos().add(new VideoDTO());

        assertNotNull(channel);
    }

    @Test
    void fromDtoNull() {
        Channel channel = channelMapper.fromDto(null);

        assertNotNull(channel);
    }

    @Test
    void toDtoNull() {
        ChannelDTO channel = channelMapper.toDto(null);

        assertNotNull(channel);
    }
}
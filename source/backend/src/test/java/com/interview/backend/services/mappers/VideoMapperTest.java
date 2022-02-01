package com.interview.backend.services.mappers;

import com.interview.backend.domain.Video;
import dto.VideoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VideoMapperTest {
    VideoMapper videoMapper;

    @BeforeEach
    void setUp() {
        videoMapper = new VideoMapper();
        assertNotNull(videoMapper);
    }

    @Test
    void fromDto() {
        Video video = videoMapper.fromDto(new VideoDTO());
        assertNotNull(video);
    }

    @Test
    void fromDtoNull() {
        Video video = videoMapper.fromDto(null);
        assertNotNull(video);
    }

    @Test
    void toDto() {
        VideoDTO dto = videoMapper.toDto(new Video());
        assertNotNull(dto);
    }

    @Test
    void toDtoNull() {
        VideoDTO dto = videoMapper.toDto(null);
        assertNotNull(dto);
    }
}
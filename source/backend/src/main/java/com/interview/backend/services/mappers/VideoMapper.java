package com.interview.backend.services.mappers;

import com.interview.backend.domain.Video;
import dto.VideoDTO;
import org.springframework.stereotype.Service;

@Service
public class VideoMapper implements IMapper<Video, VideoDTO> {

    @Override
    public Video fromDto(VideoDTO videoDTO) {
        Video video = new Video();

        if(videoDTO != null) {
            video.setId(videoDTO.getId());
            video.setVideoId(videoDTO.getVideoId());
            video.setPlaylistId(videoDTO.getPlaylistId());
            video.setName(videoDTO.getName());
            video.setUrl(videoDTO.getUrl());
        }

        return video;
    }

    @Override
    public VideoDTO toDto(Video video) {
        VideoDTO videoDTO = new VideoDTO();
        if(video != null) {
            videoDTO.setId(video.getId());
            videoDTO.setVideoId(video.getVideoId());
            videoDTO.setPlaylistId(video.getPlaylistId());
            videoDTO.setName(video.getName());
            videoDTO.setUrl(video.getUrl());
        }
        return videoDTO;
    }
}

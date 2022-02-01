package com.cogia.taskrunner.services.mapper;

import com.cogia.taskrunner.domain.dto.youtube.YoutubeResponseDTO;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import dto.VideoDTO;
import org.springframework.stereotype.Service;

@Service
public class YoutubeResponseMapper implements IYoutubeResponseMapper {

    private static final String BASE_URL = "https://www.youtube.com/watch?";
    private static final String VIDEO_PREFIX = "v=";
    private static final String PLAYLIST_PREFIX = "p=";

    @Override
    public ChannelDTO fromYoutubeResponse(YoutubeResponseDTO youtubeResponseDTO, ParseTaskDTO taskDTO){
        ChannelDTO channelDTO = taskDTO.getChannel();

        youtubeResponseDTO.items.forEach(item -> {
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setChannel(channelDTO);
            videoDTO.setName(item.snippet.title);
            videoDTO.setPlaylistId(item.id.playlistId);
            videoDTO.setVideoId(item.id.videoId);

             if(videoDTO.getVideoId() == null){
                 videoDTO.setUrl(BASE_URL+PLAYLIST_PREFIX+item.id.playlistId);
             }else{
                 videoDTO.setUrl(BASE_URL+VIDEO_PREFIX+item.id.videoId);
             }

            channelDTO.getVideos().add(videoDTO);
        });

        channelDTO.setTask(taskDTO);
        channelDTO.setName(youtubeResponseDTO.items.get(0).snippet.channelTitle);
        return channelDTO;
    }
}

package com.cogia.taskrunner.services.mapper;

import com.cogia.taskrunner.domain.dto.youtube.YoutubeResponseDTO;
import dto.ChannelDTO;
import dto.ParseTaskDTO;

public interface IYoutubeResponseMapper {
    ChannelDTO fromYoutubeResponse(YoutubeResponseDTO youtubeResponseDTO, ParseTaskDTO taskDTO);
}

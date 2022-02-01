package com.cogia.taskrunner.services.gateway.webclient;

import com.cogia.taskrunner.domain.dto.youtube.YoutubeResponseDTO;
import dto.ParseTaskDTO;

public interface IYoutubeClient {
    YoutubeResponseDTO fetchAllVideos(ParseTaskDTO parseTaskDTO);
}

package com.cogia.taskrunner.services.business;

import com.cogia.taskrunner.domain.dto.youtube.YoutubeResponseDTO;
import com.cogia.taskrunner.services.gateway.messaging.IMessageBroker;
import com.cogia.taskrunner.services.gateway.webclient.IYoutubeClient;
import com.cogia.taskrunner.services.mapper.IYoutubeResponseMapper;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskControllerBusiness implements ITaskControllerBusiness{

    private final IYoutubeClient youtubeClient;
    private final IYoutubeResponseMapper youtubeResponseMapper;
    private final IMessageBroker messageBroker;

    public TaskControllerBusiness(IYoutubeClient youtubeClient, IYoutubeResponseMapper youtubeResponseMapper, IMessageBroker messageBroker) {
        this.youtubeClient = youtubeClient;
        this.youtubeResponseMapper = youtubeResponseMapper;
        this.messageBroker = messageBroker;
    }


    public void executeTask(ParseTaskDTO taskDTO){
        YoutubeResponseDTO youtubeResponse = this.youtubeClient.fetchAllVideos(taskDTO);
        
        if(youtubeResponse != null){
            ChannelDTO channelDTO = this.youtubeResponseMapper
                    .fromYoutubeResponse(youtubeResponse, taskDTO);

            this.messageBroker.pushMessage(channelDTO);
        }
    }
}

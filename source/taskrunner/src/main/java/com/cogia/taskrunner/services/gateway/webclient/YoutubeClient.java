package com.cogia.taskrunner.services.gateway.webclient;

import com.cogia.taskrunner.cmd.FetchVideos;
import com.cogia.taskrunner.domain.dto.youtube.YoutubeResponseDTO;
import com.cogia.taskrunner.services.gateway.messaging.IMessageBroker;
import dto.ParseTaskDTO;
import dto.TaskStatusDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class YoutubeClient implements IYoutubeClient {

    private final WebClient webClient;
    private final IMessageBroker messageBroker;

    private String query = "/search?&fields=nextPageToken,items(id/*,snippet/title, snippet/channelTitle)&part=snippet&channelId=%s&key=%s&maxResults=50";


    @Value("${api.key}")
    private String api_key;

    public YoutubeClient(IMessageBroker messageBroker) {
        this.messageBroker = messageBroker;
        this.webClient = WebClient.builder().baseUrl("https://youtube.googleapis.com/youtube/v3").build();
    }

    @Override
    public YoutubeResponseDTO fetchAllVideos(ParseTaskDTO taskDTO){
        String uri = String.format(query, taskDTO.getChannel().getId(), api_key);

        FetchVideos action = new FetchVideos(this.webClient, uri);
        Thread taskExecution = new Thread(action);
        taskExecution.start();

        TaskStatusDTO taskStatusDTO = new TaskStatusDTO();

        taskStatusDTO.setId(taskDTO.getTaskId());
        taskStatusDTO.setStatus(action.status);
        taskDTO.setStatus(action.status);

        this.messageBroker.pushMessage(taskStatusDTO);

        YoutubeResponseDTO dto = null;

        try{
            taskExecution.join();
            dto = action.getFinalYoutubeResponse();

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        taskStatusDTO.setStatus(action.status);
        taskDTO.setStatus(action.status);
        this.messageBroker.pushMessage(taskStatusDTO);

        return dto;
    }


}

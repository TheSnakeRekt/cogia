package com.cogia.taskrunner.cmd;

import com.cogia.taskrunner.domain.dto.youtube.ItemDto;
import com.cogia.taskrunner.domain.dto.youtube.YoutubeResponseDTO;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

public class FetchVideos implements Runnable {

    public volatile String status;
    private volatile YoutubeResponseDTO finalYoutubeResponse;
    private final WebClient webClient;
    private String uri;
    private String uriWithToken;
    private volatile ArrayList<ItemDto> tmpItems = new ArrayList<>();

    public FetchVideos(WebClient webClient, String uri){

        this.webClient = webClient;
        this.uri = uri;
        this.webClient.get()
                .uri(this.uri);
        this.uriWithToken = uri;
        this.status = "ONGOING";
    }

    @Override
    public void run() {
        YoutubeResponseDTO tmp = this.webClient.get().uri(this.uriWithToken)
                .retrieve().bodyToMono(YoutubeResponseDTO.class).
                doOnError(err->{
                    this.status = "ERROR";
                }).block();

        if(tmp != null && tmp.nextPageToken != null){
            this.uriWithToken = this.uri + "&pageToken="+tmp.nextPageToken;
            this.tmpItems.addAll(tmp.items);
            this.run();
        }else{
            setFinalYoutubeResponse(tmp);
        }
    }

    public void setFinalYoutubeResponse(YoutubeResponseDTO youtubeResponseDTO){
        if(youtubeResponseDTO == null){
            this.finalYoutubeResponse = null ;
            return;
        }

        this.tmpItems.addAll(youtubeResponseDTO.items);

        youtubeResponseDTO.items = new ArrayList<>();
        youtubeResponseDTO.items.addAll(this.tmpItems);

        this.status = "DONE";
        this.finalYoutubeResponse = youtubeResponseDTO;
    }

    public YoutubeResponseDTO getFinalYoutubeResponse() {
        return this.finalYoutubeResponse;
    }
}

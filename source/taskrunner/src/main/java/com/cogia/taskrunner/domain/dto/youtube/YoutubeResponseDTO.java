package com.cogia.taskrunner.domain.dto.youtube;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;



@JsonSerialize
public class YoutubeResponseDTO implements Serializable {
    public String nextPageToken ;
    public ArrayList<ItemDto> items = new ArrayList<>();
}

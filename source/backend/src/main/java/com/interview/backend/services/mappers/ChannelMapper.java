package com.interview.backend.services.mappers;

import com.interview.backend.domain.Channel;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import dto.VideoDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class ChannelMapper implements IMapper<Channel, ChannelDTO> {

    private final VideoMapper videoMapper;

    public ChannelMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public Channel fromDto(ChannelDTO channelDTO) {
        Channel channel = new Channel();

        if(channelDTO != null){
            channel.setChannelId(channelDTO.getId());
            channel.setName(channelDTO.name);
            channel.setVideos(channelDTO.videos
                    .stream().map(this.videoMapper::fromDto)
                    .collect(Collectors.toSet()
                    )
            );
        }

        return channel;
    }

    @Override
    public ChannelDTO toDto(Channel channel) {
        ChannelDTO dto = new ChannelDTO();

        if (channel != null) {
            dto.setId(channel.getChannelId());
            dto.setName(channel.getName());
            dto.setVideos(
                    channel.getVideos()
                            .stream().map(this.videoMapper::toDto)
                            .sorted(Comparator.comparingLong(VideoDTO::getId))
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}

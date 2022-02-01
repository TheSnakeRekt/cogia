package com.interview.backend.services.business.factory;

import com.interview.backend.domain.Channel;
import com.interview.backend.services.mappers.ChannelMapper;
import dto.ChannelDTO;
import org.springframework.stereotype.Service;

@Service
public class ChannelFactory extends Factory<Channel, ChannelDTO> {

    private final ChannelMapper channelMapper;

    public ChannelFactory(ChannelMapper channelMapper) {
        this.channelMapper = channelMapper;
    }

    @Override
    public Channel create(ChannelDTO channelDTO) {
        return this.channelMapper.fromDto(channelDTO);
    }
}

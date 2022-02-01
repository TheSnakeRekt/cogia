package com.interview.backend.services.business.factory;

import com.interview.backend.domain.Channel;
import com.interview.backend.services.mappers.ChannelMapper;
import dto.ChannelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChannelFactoryTest {


    ChannelFactory channelFactory;

    @Mock
    ChannelMapper channelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        channelFactory = new ChannelFactory(channelMapper);
    }

    @Test
    void create() {
        ChannelDTO mockDto = new ChannelDTO();
        when(channelMapper.fromDto(mockDto)).thenReturn(mockChannel());

        Channel testChannel = channelFactory.create(mockDto);

        verify(channelMapper, times(1)).fromDto(mockDto);
        assertNotNull(testChannel);
        assertEquals(testChannel.getChannelId(), mockChannel().getChannelId());
        assertEquals(testChannel.getId(), mockChannel().getId());
        assertEquals(testChannel.getName(), mockChannel().getName());
    }

    private static Channel mockChannel(){
        Channel channel = new Channel();
        channel.setId(1L);
        channel.setChannelId("channelMockId");
        channel.setName("TestChannelName");
        return channel;
    }
}
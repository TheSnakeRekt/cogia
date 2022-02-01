package com.interview.backend.services.business.factory;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import com.interview.backend.services.gateway.IMessageBroker;
import com.interview.backend.services.mappers.TaskMapper;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskFactoryTest {

    TaskFactory<TaskParse> taskFactoryTaskParse;
    TaskFactory<Channel> taskFactoryChannel;
    TaskFactory<ParseTaskDTO> taskFactoryParseTaskDTO;
    TaskFactory<ChannelDTO> taskFactoryChannelDTO;


    @Mock
    TaskMapper taskMapper;
    @Mock
    ChannelFactory channelFactory;
    @Mock
    IMessageBroker messageBroker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        taskFactoryChannelDTO = new TaskFactory<>(taskMapper, channelFactory, messageBroker);
        taskFactoryParseTaskDTO = new TaskFactory<>(taskMapper, channelFactory, messageBroker);
        taskFactoryTaskParse = new TaskFactory<>(taskMapper, channelFactory, messageBroker);
        taskFactoryChannel = new TaskFactory<>(taskMapper, channelFactory, messageBroker);
    }

    @Test
    void create() {
        ChannelDTO channelDTOMock = new ChannelDTO();

        when(channelFactory.create(channelDTOMock)).thenReturn(mockChannel());

        TaskParse taskParseMock = taskFactoryChannelDTO.create(channelDTOMock);
        taskParseMock.setId(74L);

        when(taskMapper.toDto(taskParseMock)).thenReturn(mockParseTaskDto(taskParseMock));


        ParseTaskDTO dtoMock = taskMapper.toDto(taskParseMock);

        when(taskMapper.fromDto(dtoMock)).thenReturn(taskParseMock);

        assertEquals(dtoMock.getTaskId(), taskParseMock.getId());
        assertEquals(mockChannel().getChannelId(), dtoMock.getChannelId());

        assertNotNull(dtoMock);


        TaskParse expectedTaskParse = taskFactoryTaskParse.create(taskParseMock);

        assertEquals(expectedTaskParse, taskParseMock);
        TaskParse expectedParseTaskDTO  = taskFactoryParseTaskDTO.create(dtoMock);

        assertEquals(expectedParseTaskDTO.getId(), dtoMock.getTaskId());
        assertEquals(expectedParseTaskDTO.getChannel().getChannelId(), dtoMock.getChannelId());
        assertEquals(expectedParseTaskDTO.getStatus().toString(), dtoMock.getStatus());
        assertEquals(expectedParseTaskDTO.getChannel().getChannelId(), dtoMock.getChannelId());

        TaskParse expectedChannel = taskFactoryChannel.create(mockChannel());

        assertEquals(expectedTaskParse.getChannel().getChannelId(), expectedChannel.getChannel().getChannelId());
        assertEquals(expectedTaskParse.getChannel().getId(), expectedChannel.getChannel().getId());
        assertEquals(expectedTaskParse.getChannel().getName(), expectedChannel.getChannel().getName());

        verify(messageBroker,times(2)).pushMessage(taskMapper.toDto(taskParseMock));
    }

    private static  ParseTaskDTO mockParseTaskDto(TaskParse mockTask){
        ParseTaskDTO parseTaskDTOMock = new ParseTaskDTO();
        parseTaskDTOMock.setTaskId(mockTask.getId());
        parseTaskDTOMock.setStatus(mockTask.getStatus().toString());
        parseTaskDTOMock.setChannelName("ChannelTaskTeste");
        parseTaskDTOMock.setChannelId(mockTask.getChannel().getChannelId());
        return parseTaskDTOMock;
    }

    private static Channel mockChannel(){
        Channel channelMock = new Channel();
        TaskParse taskParseMock = new TaskParse();

        channelMock.setChannelId("youtubeTestChannelID");
        channelMock.setTask(taskParseMock);
        channelMock.setName("ChannelTaskTeste");
        channelMock.setId(55L);

        channelMock.setVideos(new HashSet<>());
        return channelMock;
    }

}
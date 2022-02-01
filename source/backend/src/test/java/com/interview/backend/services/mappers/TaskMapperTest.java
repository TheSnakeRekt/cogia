package com.interview.backend.services.mappers;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import com.interview.backend.domain.enums.ETaskStatus;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TaskMapperTest {

    TaskMapper taskMapper;

    @Mock
    ChannelMapper channelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        taskMapper = new TaskMapper(channelMapper);
        assertNotNull(taskMapper);
    }
    @Test
    void fromDto() {
        TaskParse taskParse = taskMapper.fromDto(new ParseTaskDTO());
        taskParse.setChannel(new Channel());

        assertNotNull(taskParse);

        ParseTaskDTO dtoWithStatus = new ParseTaskDTO();
        dtoWithStatus.setStatus(ETaskStatus.DONE.toString());

        taskMapper.fromDto(dtoWithStatus);

        verify(channelMapper, times(1)).fromDto(any());
    }

    @Test
    void toDto() {
        ParseTaskDTO dto = taskMapper.toDto(new TaskParse());
        dto.setChannel(new ChannelDTO());

        assertNotNull(dto);

        TaskParse taskParseWithStatus = new TaskParse();
        taskParseWithStatus.setStatus(ETaskStatus.DONE);

        taskMapper.toDto(taskParseWithStatus);

        verify(channelMapper, times(1)).toDto(any());
    }
}
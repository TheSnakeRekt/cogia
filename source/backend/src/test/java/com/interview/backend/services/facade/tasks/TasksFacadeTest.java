package com.interview.backend.services.facade.tasks;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import com.interview.backend.domain.enums.ETaskStatus;
import com.interview.backend.services.business.tasks.ITasksBusiness;
import com.interview.backend.services.mappers.ChannelMapper;
import com.interview.backend.services.mappers.TaskMapper;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import dto.TaskStatusDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasksFacadeTest {

    TasksFacade tasksFacade;

    @Mock
    ITasksBusiness tasksBusiness;
    @Mock
    TaskMapper taskMapper;
    @Mock
    ChannelMapper channelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        tasksFacade = new TasksFacade(tasksBusiness, taskMapper, channelMapper);
    }

    @Test
    void insertTask() {
        when(tasksBusiness.insertTask("testYtId")).thenReturn(8L);

        Long result = tasksFacade.insertTask("testYtId");

        assertEquals(8L, result);
        verify(tasksBusiness,times(1)).insertTask("testYtId");
    }

    @Test
    void getAllTasks() {
        ParseTaskDTO parseTaskDTO = new ParseTaskDTO();
        parseTaskDTO.setTaskId(1);

        when(tasksBusiness.getAllTasks()).thenReturn(mockTasks());
        when(taskMapper.toDto(any()))
                .thenReturn(parseTaskDTO);

        List<ParseTaskDTO> result = tasksFacade.getAllTasks();

        assertNotNull(result);
        assertEquals(10, result.size());

        verify(taskMapper,times(10)).toDto(any());
        verify(tasksBusiness,times(1)).getAllTasks();
    }

    @Test
    void getChannelByTaskId() {
        Channel channel = new Channel();
        ChannelDTO channelDTO = new ChannelDTO();
        ParseTaskDTO parseTaskDTO = new ParseTaskDTO();
        channel.setId(2L);
        channel.setChannelId("channelTestID");
        parseTaskDTO.setTaskId(87L);
        channelDTO.setTask(parseTaskDTO);

        when(tasksBusiness.getChannelByTaskId(channel.getId())).thenReturn(channel);
        when(channelMapper.toDto(any())).thenReturn(channelDTO);

        ChannelDTO result = tasksFacade.getChannelByTaskId(87L);
        assertEquals(channelDTO.getTask().getTaskId(), result.getTask().getTaskId());

        verify(tasksBusiness, times(1)).getChannelByTaskId(any());
        verify(channelMapper, times(1)).toDto(any());
    }

    @Test
    void updateTask() {
        TaskStatusDTO dto = new TaskStatusDTO();
        dto.setId(1L);
        dto.setStatus(ETaskStatus.DONE.toString());

        tasksFacade.updateTask(dto);

        verify(tasksBusiness, times(1)).updateTaskStatus(dto);
    }

    @Test
    void updateChannel() {
        ChannelDTO dto = new ChannelDTO();
        dto.setTask(new ParseTaskDTO());
        dto.getTask().setTaskId(87L);

        when(channelMapper.fromDto(any())).thenReturn(new Channel());

        tasksFacade.updateChannel(dto);
        verify(channelMapper, times(1)).fromDto(any());
        verify(tasksBusiness, times(1)).updateChannel(any(), eq(dto.getTask().getTaskId()));
    }

    private static Set<TaskParse> mockTasks(){
        Set<TaskParse> tasks = new HashSet<>();

        for(int i = 0; i < 10; i++){
            TaskParse task = new TaskParse();
            task.setId((long) i);
            task.setStatus(ETaskStatus.DONE);
            task.setChannel(new Channel());
            tasks.add(task);
        }

        return tasks;
    }
}
package com.interview.backend.services.business.tasks;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import com.interview.backend.domain.enums.ETaskStatus;
import com.interview.backend.services.business.factory.TaskFactory;
import com.interview.backend.services.repository.IRepository;
import dto.ChannelDTO;
import dto.TaskStatusDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasksBusinessTest {
    TasksBusiness tasksBusiness;

    @Mock
    IRepository repository;
    @Mock
    TaskFactory<ChannelDTO> factoryChannel;
    @Mock
    TaskFactory<TaskParse> taskFactory;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

        tasksBusiness = new TasksBusiness(repository, factoryChannel, taskFactory);
        assertNotNull(tasksBusiness);
    }

    @Test
    void insertTask() {
        ChannelDTO mockDto = new ChannelDTO();
        TaskParse taskParseMock = new TaskParse();

        taskParseMock.setId(1L);

        when(factoryChannel.create(mockDto)).thenReturn(taskParseMock);
        when(repository.saveTask(taskParseMock)).thenReturn(taskParseMock);
        when(taskFactory.create(any())).thenReturn(taskParseMock);

        Long result = tasksBusiness.insertTask("mockDtoID");

        assertEquals(1L, result);

        verify(factoryChannel, times(1)).create(any());
        verify(repository, times(1)).saveTask(any());
        verify(taskFactory, times(1)).create(any());
    }

    @Test
    void getAllTasks() {
        when(repository.findAllTasks()).thenReturn(mockTasks());

        Set<TaskParse> result = tasksBusiness.getAllTasks();

        assertNotNull(result);
        assertEquals(10, result.size());

        verify(repository, times(1)).findAllTasks();
    }

    @Test
    void getChannelByTaskId() {
        when(repository.findTaskById(45L)).thenReturn(mockTask(45L, ETaskStatus.DONE));

        Channel result = tasksBusiness.getChannelByTaskId(45L);

        assertNotNull(result);
        assertEquals(45L, result.getTask().getId());
        assertEquals("testChannelID", result.getChannelId());

        when(repository.findTaskById(45L)).thenReturn(mockTask(45L, ETaskStatus.PENDING));
        assertNull(tasksBusiness.getChannelByTaskId(45L));

        verify(repository, times(2)).findTaskById(45L);
    }

    @Test
    void updateTaskStatus() {
        TaskStatusDTO taskStatusDTO = new TaskStatusDTO();
        taskStatusDTO.setStatus(ETaskStatus.ERROR.toString());
        taskStatusDTO.setId(45L);

        Optional<TaskParse> mocktaskOpt = mockTask(taskStatusDTO.getId(), ETaskStatus.ERROR);

        when(repository.findTaskById(taskStatusDTO.getId())).thenReturn(mocktaskOpt);

        tasksBusiness.updateTaskStatus(taskStatusDTO);

        assertNotNull(mocktaskOpt.get());
        verify(repository,times(1)).findTaskById(taskStatusDTO.getId());
        verify(repository,times(1)).saveTaskStatus(mocktaskOpt.get());
    }

    @Test
    void updateChannel() {
        Optional<TaskParse> mocktaskOpt = mockTask(45L, ETaskStatus.DONE);

        when(repository.findTaskById(mocktaskOpt.get().getId())).thenReturn(mocktaskOpt);

        tasksBusiness.updateChannel(mocktaskOpt.get().getChannel(), mocktaskOpt.get().getId());

        assertNotNull(mocktaskOpt.get());
        mocktaskOpt.get().getChannel().setId(null);
        tasksBusiness.updateChannel(mocktaskOpt.get().getChannel(), mocktaskOpt.get().getId());

        verify(repository,times(2)).findTaskById(mocktaskOpt.get().getId());
        verify(repository,times(2)).saveTask(mocktaskOpt.get());
    }

    private static Set<TaskParse> mockTasks(){
        Set<TaskParse> tasks = new HashSet<>();

        for(int i = 0; i < 10; i++){
            TaskParse task = new TaskParse();
            task.setId((long) i);
            task.setStatus(ETaskStatus.DONE);
            tasks.add(task);
        }

        return tasks;
    }

    private static Optional<TaskParse> mockTask(long id, ETaskStatus status){
        TaskParse task = new TaskParse();
        Channel mockChannel = new Channel();

        task.setId(id);
        mockChannel.setChannelId("testChannelID");
        mockChannel.setTask(task);
        task.setChannel(mockChannel);
        task.setStatus(status);
        return Optional.of(task);
    }
}
package com.interview.backend.services.repository;

import com.interview.backend.domain.TaskParse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RepositoryTest {

    Repository repository;

    @Mock
    ITasksRepository tasksRepository;
    @Mock
    IChannelRepository channelRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

        repository = new Repository(tasksRepository, channelRepository);
    }

    @Test
    void findTaskById() {
        when(tasksRepository.findById(47L)).thenReturn(any());

        repository.findTaskById(47L);
        verify(tasksRepository, times(1)).findById(47L);
    }

    @Test
    void findAllTasks() {
        repository.findAllTasks();
        verify(tasksRepository, times(1)).findAll();
    }

    @Test
    void saveTask() {
        repository.saveTask(new TaskParse());
        verify(tasksRepository, times(1)).save(any());
    }

    @Test
    void saveTaskStatus() {
        repository.saveTaskStatus(new TaskParse());
        verify(tasksRepository, times(1)).save(any());
    }
}
package com.interview.backend.controller;

import com.interview.backend.domain.enums.ETaskStatus;
import com.interview.backend.services.facade.tasks.ITasksFacade;
import com.interview.backend.services.facade.tasks.TasksFacade;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppControllerTest {

    AppController appController;

    @Mock
    ITasksFacade tasksFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        appController = new AppController(tasksFacade);

        assertNotNull(appController);
    }

    @Test
    void insertTask() {
        when(tasksFacade.insertTask("youtubeChannelIdTest")).thenReturn(1L);

        Long expected = appController.insertTask("youtubeChannelIdTest");
        assertEquals(expected, 1L);
        verify(tasksFacade, times(1)).insertTask("youtubeChannelIdTest");
    }

    @Test
    void getAllTasks() {
        when(tasksFacade.getAllTasks()).thenReturn(mockTasks());

        List<ParseTaskDTO> expectedList = appController.getAllTasks();

        assertNotNull(expectedList);
        assertEquals(expectedList.size(), mockTasks().size());

        verify(tasksFacade, times(1)).getAllTasks();
    }

    @Test
    void getTaskById() {
        ParseTaskDTO dtoTask = new ParseTaskDTO();
        dtoTask.setTaskId(65L);

        when(tasksFacade.getChannelByTaskId(65L)).thenReturn(mockChannelDto(dtoTask));

        ChannelDTO expectedChannel = appController.getTaskById(dtoTask.getTaskId());

        assertNotNull(expectedChannel);
        assertEquals(expectedChannel.getTask().getTaskId(), dtoTask.getTaskId());

        verify(tasksFacade, times(1)).getChannelByTaskId(dtoTask.getTaskId());
    }

    private static List<ParseTaskDTO> mockTasks(){
        List<ParseTaskDTO> tasks = new ArrayList<>();

        for(int i = 0; i < 6; i++){
            ParseTaskDTO dto = new ParseTaskDTO();
            dto.setTaskId(i);
            dto.setStatus(ETaskStatus.ONGOING.toString());

            tasks.add(dto);
        }

        return tasks;
    }

    private static ChannelDTO mockChannelDto(ParseTaskDTO dtoTask){
        ChannelDTO dto = new ChannelDTO();
        dto.setId("youtubeChannelIDTest");
        dto.setName("youtubeChannelNameTest");
        dto.setTask(dtoTask);

        return  dto;
    }
}
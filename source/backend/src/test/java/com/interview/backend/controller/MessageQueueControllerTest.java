package com.interview.backend.controller;

import com.interview.backend.services.facade.tasks.ITasksFacade;
import dto.ChannelDTO;
import dto.TaskStatusDTO;
import dto.VideoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageQueueControllerTest {

    MessageQueueController messageQueueController;

    @Mock
    ITasksFacade tasksFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        messageQueueController = new MessageQueueController(tasksFacade);
    }

    @Test
    void onMessage() throws IOException {
        ChannelDTO dto = new ChannelDTO();

        messageQueueController.onMessage(dto);
        verify(tasksFacade,times(1)).updateChannel(dto);
    }

    @Test
    void testOnMessage() throws IOException {
        TaskStatusDTO dto = new TaskStatusDTO();

        messageQueueController.onMessage(dto);
        verify(tasksFacade,times(1)).updateTask(dto);

    }
}
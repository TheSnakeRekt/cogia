package com.interview.backend.domain;

import com.interview.backend.domain.enums.ETaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskParseTest {

    TaskParse taskParse;

    @BeforeEach
    void setUp() {
        taskParse = new TaskParse();
        assertNotNull(taskParse);
    }

    @Test
    void getId() {
        taskParse.setId(8L);
        assertEquals(8L, taskParse.getId());
    }

    @Test
    void getStatus() {
        taskParse.setStatus(ETaskStatus.PENDING);
        assertNotNull(taskParse.getStatus());
        assertEquals(ETaskStatus.PENDING, taskParse.getStatus());
    }

    @Test
    void getChannel() {
        Channel channel = new Channel();
        channel.setId(56L);
        taskParse.setChannel(channel);
        assertNotNull(taskParse.getChannel());
        assertEquals(56L, taskParse.getChannel().getId());
    }
}
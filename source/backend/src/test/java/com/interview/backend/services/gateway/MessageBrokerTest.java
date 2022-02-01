package com.interview.backend.services.gateway;

import dto.ParseTaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MessageBrokerTest {

    MessageBroker messageBroker;

    @Mock
    RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        messageBroker = new MessageBroker(rabbitTemplate);
        assertNotNull(messageBroker);
    }

    @Test
    void pushMessage() {
        ParseTaskDTO dto = new ParseTaskDTO();

        messageBroker.pushMessage(dto);

        verify(rabbitTemplate,times(1)).convertAndSend(any(String.class), any(Object.class));
    }
}
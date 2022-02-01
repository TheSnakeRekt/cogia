package com.interview.backend.controller;

import com.interview.backend.services.facade.tasks.ITasksFacade;
import dto.ChannelDTO;
import dto.TaskStatusDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.interview.backend.services.gateway.MessageBrokerConfig.*;

@Component
public class MessageQueueController {

    private final ITasksFacade tasksFacade;

    public MessageQueueController(ITasksFacade tasksFacade) {
        this.tasksFacade = tasksFacade;
    }

    @RabbitListener(queues = {STATUS_TASK_QUEUE})
    public void onMessage(@Payload TaskStatusDTO payload) throws IOException {
        this.tasksFacade.updateTask(payload);
    }

    @RabbitListener(queues = {CHANNEL_RESPONSE_QUEUE})
    public void onMessage(@Payload ChannelDTO payload) throws IOException {
        this.tasksFacade.updateChannel(payload);
    }

}

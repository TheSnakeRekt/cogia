package com.cogia.taskrunner.controller;

import com.cogia.taskrunner.services.facade.ITaskControllerFacade;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import dto.ParseTaskDTO;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.cogia.taskrunner.config.MessageBrokerConfig.NEW_TASK_QUEUE;

@Component
public class TaskController {

    private final ITaskControllerFacade taskControllerFacade;

    public TaskController(ITaskControllerFacade taskControllerFacade) {
        this.taskControllerFacade = taskControllerFacade;
    }

    @RabbitListener(queues = {NEW_TASK_QUEUE})
    public void onMessage(@Payload ParseTaskDTO taskDTO) throws IOException {
        this.taskControllerFacade.fetchAllVideos(taskDTO);
    }
}

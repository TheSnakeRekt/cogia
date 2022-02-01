package com.cogia.taskrunner.services.gateway.messaging;

import dto.ChannelDTO;
import dto.TaskStatusDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.cogia.taskrunner.config.MessageBrokerConfig.CHANNEL_RESPONSE_QUEUE;
import static com.cogia.taskrunner.config.MessageBrokerConfig.STATUS_TASK_QUEUE;


@Service
public class MessageBroker implements IMessageBroker {

    private final RabbitTemplate rabbitTemplate;

    MessageBroker(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void pushMessage(ChannelDTO msg) {
        this.rabbitTemplate.convertAndSend(CHANNEL_RESPONSE_QUEUE, msg);
    }

    @Override
    public void pushMessage(TaskStatusDTO msg) {
        this.rabbitTemplate.convertAndSend(STATUS_TASK_QUEUE, msg);
    }
}
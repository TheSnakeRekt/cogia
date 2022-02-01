package com.interview.backend.services.gateway;

import dto.ParseTaskDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.interview.backend.services.gateway.MessageBrokerConfig.NEW_TASK_QUEUE;

@Service
public class MessageBroker implements IMessageBroker {

    private final RabbitTemplate rabbitTemplate;

    MessageBroker(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void pushMessage(ParseTaskDTO msg) {
        this.rabbitTemplate.convertAndSend(NEW_TASK_QUEUE, msg);
    }
}

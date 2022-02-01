package com.cogia.taskrunner.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfig {

    public static final String NEW_TASK_QUEUE = "QUEUE.TASK.NEW";
    public static final String STATUS_TASK_QUEUE = "QUEUE.TASK.STATUS";
    public static final String CHANNEL_RESPONSE_QUEUE = "QUEUE.CHANNEL.RESPONSE";

    @Bean
    Queue taskStatusQueueResponse() {
        return new Queue(STATUS_TASK_QUEUE);
    }

    @Bean
    Queue channelQueueResponse() {
        return new Queue(CHANNEL_RESPONSE_QUEUE);
    }
}
package com.cogia.taskrunner.services.gateway.messaging;

import dto.ChannelDTO;
import dto.TaskStatusDTO;

public interface IMessageBroker {
    void pushMessage(TaskStatusDTO msg);
    void pushMessage(ChannelDTO msg);
}

package com.interview.backend.services.gateway;

import dto.ParseTaskDTO;

public interface IMessageBroker {
    void pushMessage(ParseTaskDTO msg);
}

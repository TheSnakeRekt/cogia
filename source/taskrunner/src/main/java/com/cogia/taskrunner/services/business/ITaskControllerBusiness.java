package com.cogia.taskrunner.services.business;

import dto.ParseTaskDTO;

public interface ITaskControllerBusiness {
    void executeTask(ParseTaskDTO taskDTO);
}

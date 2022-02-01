package com.cogia.taskrunner.services.facade;

import com.cogia.taskrunner.services.business.ITaskControllerBusiness;
import dto.ParseTaskDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskControllerFacade implements ITaskControllerFacade {

    private final ITaskControllerBusiness taskControllerBusiness;

    public TaskControllerFacade(ITaskControllerBusiness taskControllerBusiness) {
        this.taskControllerBusiness = taskControllerBusiness;
    }

    public void fetchAllVideos(ParseTaskDTO taskDTO){
        if(taskDTO != null){
           this.taskControllerBusiness.executeTask(taskDTO);
        }
    }
}

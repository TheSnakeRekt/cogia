package com.interview.backend.services.facade.tasks;

import dto.ChannelDTO;
import dto.ParseTaskDTO;
import dto.TaskStatusDTO;

import java.util.List;


public interface ITasksFacade {
    long insertTask(String youtubeChannelId);

    List<ParseTaskDTO> getAllTasks();

    ChannelDTO getChannelByTaskId(Long taskId);

    void updateTask(TaskStatusDTO taskStatusDTO);
    void updateChannel(ChannelDTO channelDTO);
}

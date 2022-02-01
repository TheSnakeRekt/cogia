package com.interview.backend.services.business.tasks;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import dto.TaskStatusDTO;

import java.util.Set;


public interface ITasksBusiness {
    Long insertTask(String channelId);

    Set<TaskParse> getAllTasks();

    Channel getChannelByTaskId(Long taskId);

    void updateTaskStatus(TaskStatusDTO task);

     void updateChannel(Channel channel, Long taskId);
}

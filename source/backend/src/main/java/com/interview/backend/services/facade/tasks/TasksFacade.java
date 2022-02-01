package com.interview.backend.services.facade.tasks;

import com.interview.backend.services.business.tasks.ITasksBusiness;
import com.interview.backend.services.mappers.ChannelMapper;
import com.interview.backend.services.mappers.TaskMapper;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import dto.TaskStatusDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksFacade implements ITasksFacade {

    private final ITasksBusiness tasksBusiness;
    private final TaskMapper taskMapper;
    private final ChannelMapper channelMapper;

    public TasksFacade(ITasksBusiness tasksBusiness, TaskMapper taskMapper, ChannelMapper channelMapper) {
        this.tasksBusiness = tasksBusiness;
        this.taskMapper = taskMapper;
        this.channelMapper = channelMapper;
    }

    @Override
    public long insertTask(String youtubeChannelId) {
        return this.tasksBusiness.insertTask(youtubeChannelId);
    }

    @Override
    public List<ParseTaskDTO> getAllTasks() {
        return this.tasksBusiness.getAllTasks()
                .stream().map(this.taskMapper::toDto)
                .sorted(Comparator.comparingLong(ParseTaskDTO::getTaskId))
                .collect(Collectors.toList());
    }

    @Override
    public ChannelDTO getChannelByTaskId(Long taskId) {
        return this.channelMapper.toDto(this.tasksBusiness.getChannelByTaskId(taskId));
    }

    @Override
    public void updateTask(TaskStatusDTO task) {
        if(task.getStatus() != null){
            this.tasksBusiness.updateTaskStatus(task);
        }
    }

    @Override
    public void updateChannel(ChannelDTO channelDTO) {
        this.tasksBusiness.updateChannel(this.channelMapper.fromDto(channelDTO),
                channelDTO.getTask().taskId);
    }
}

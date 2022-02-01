package com.interview.backend.services.business.tasks;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import com.interview.backend.domain.enums.ETaskStatus;
import com.interview.backend.services.business.factory.TaskFactory;
import com.interview.backend.services.repository.IRepository;
import dto.ChannelDTO;
import dto.TaskStatusDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TasksBusiness implements ITasksBusiness {

    private final IRepository repository;
    private final TaskFactory<ChannelDTO> factoryChannel;
    private final TaskFactory<TaskParse> taskFactory;

    TasksBusiness(IRepository repository, TaskFactory<ChannelDTO> factoryChannel,
                  TaskFactory<TaskParse> taskFactory) {
        this.repository = repository;
        this.factoryChannel = factoryChannel;
        this.taskFactory = taskFactory;
    }

    @Override
    public Long insertTask(String channelId) {
        ChannelDTO channelDTO = new ChannelDTO(channelId);
        return this.taskFactory.create(
                this.repository.saveTask(this.factoryChannel.create(channelDTO))
        ).getId();
    }

    @Override
    public Set<TaskParse> getAllTasks() {
        return this.repository.findAllTasks();
    }

    @Override
    public Channel getChannelByTaskId(Long taskId) {
        Optional<TaskParse> taskParseEntity = this.repository.findTaskById(taskId);

        if (taskParseEntity.isPresent() && taskParseEntity.get().getStatus().equals(ETaskStatus.DONE)) {
            return taskParseEntity.get().getChannel();
        }
        return null;
    }

    @Override
    public void updateTaskStatus(TaskStatusDTO task) {
        Optional<TaskParse> taskParseEntity = this.repository.findTaskById(task.getId());

        if (taskParseEntity.isPresent()) {
            taskParseEntity.get().setStatus(ETaskStatus.valueOf(task.getStatus()));
            this.repository.saveTaskStatus(taskParseEntity.get());
        }
    }

    @Override
    public void updateChannel(Channel channel, Long taskId) {
        Optional<TaskParse> taskParseEntity = this.repository.findTaskById(taskId);

        if (taskParseEntity.isPresent()) {
            if(channel.getId() == null){
                channel.setId(taskParseEntity.get().getChannel().getId());
            }

            taskParseEntity.get().setStatus(ETaskStatus.DONE);
            channel.setTask(taskParseEntity.get());
            taskParseEntity.get().setChannel(channel);
            this.repository.saveTask(taskParseEntity.get());
        }
    }
}

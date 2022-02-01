package com.interview.backend.services.business.factory;

import com.interview.backend.domain.Channel;
import com.interview.backend.domain.TaskParse;
import com.interview.backend.domain.enums.ETaskStatus;
import com.interview.backend.services.gateway.IMessageBroker;
import com.interview.backend.services.mappers.TaskMapper;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskFactory<T> extends Factory<TaskParse, T> {

    private final TaskMapper taskMapper;
    private final ChannelFactory channelFactory;
    private final IMessageBroker messageBroker;

    public TaskFactory(TaskMapper taskMapper, ChannelFactory channelFactory, IMessageBroker messageBroker) {
        this.taskMapper = taskMapper;
        this.channelFactory = channelFactory;
        this.messageBroker = messageBroker;
    }

    public TaskParse create(T t) {
        Channel channel = null;

        if (t instanceof TaskParse) {
            this.messageBroker.pushMessage(taskMapper.toDto((TaskParse) t));
            return (TaskParse) t;
        }

        if (t instanceof ParseTaskDTO) {
            this.messageBroker.pushMessage((ParseTaskDTO) t);
            return taskMapper.fromDto((ParseTaskDTO) t);
        }

        if (t instanceof ChannelDTO) {
            channel = channelFactory.create((ChannelDTO) t);
        } else if (t instanceof Channel) {
            channel = (Channel) t;
        }

        TaskParse taskParse = new TaskParse();
        channel.setTask(taskParse);
        taskParse.setChannel(channel);
        taskParse.setStatus(ETaskStatus.PENDING);


        return taskParse;
    }
}

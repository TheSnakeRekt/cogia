package com.interview.backend.services.mappers;

import com.interview.backend.domain.enums.ETaskStatus;
import com.interview.backend.domain.TaskParse;
import dto.ParseTaskDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper implements IMapper<TaskParse, ParseTaskDTO> {

    private final ChannelMapper channelMapper;

    public TaskMapper(ChannelMapper channelMapper) {
        this.channelMapper = channelMapper;
    }

    @Override
    public TaskParse fromDto(ParseTaskDTO task) {
        TaskParse taskParse = new TaskParse();
        if (task != null && task.getStatus() != null){
            taskParse.setChannel(this.channelMapper.fromDto(task.getChannel()));
            taskParse.setId(task.getTaskId());
            taskParse.setStatus(ETaskStatus.valueOf(task.getStatus()));
        }
        return taskParse;
    }

    @Override
    public ParseTaskDTO toDto(TaskParse task) {
        ParseTaskDTO parseTaskDTO = new ParseTaskDTO();

        if(task != null && task.getStatus() != null) {
            parseTaskDTO.setTaskId(task.getId());
            parseTaskDTO.setChannel(this.channelMapper.toDto(task.getChannel()));
            parseTaskDTO.setStatus(task.getStatus().toString());
        }
        return parseTaskDTO;
    }
}

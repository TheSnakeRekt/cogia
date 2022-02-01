package com.interview.backend.services.repository;


import com.interview.backend.domain.TaskParse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class Repository implements IRepository {

    private final ITasksRepository tasksRepository;
    private final IChannelRepository channelRepository;

    public Repository(ITasksRepository tasksRepository, IChannelRepository channelRepository) {
        this.tasksRepository = tasksRepository;
        this.channelRepository = channelRepository;
    }

    public Optional<TaskParse> findTaskById(Long id) {
        return this.tasksRepository.findById(id);
    }

    public Set<TaskParse> findAllTasks() {
        return StreamSupport.stream(this.tasksRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    public TaskParse saveTask(TaskParse taskParse) {
        this.channelRepository.save(taskParse.getChannel());
        return this.tasksRepository.save(taskParse);
    }

    public TaskParse saveTaskStatus(TaskParse taskParse){
        return this.tasksRepository.save(taskParse);
    }
}
